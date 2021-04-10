package Algorithm;

import Common.Problem;
import Common.Solution;
import IO.ConfigReader;
import Operators.IPerturbation;
import Operators.OperatorManager;
import Operators.RecreatePerturbation;
import Utils.TimeController;
import org.apache.log4j.Logger;

import java.sql.Time;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class VNSALS {
    Map<String, Object> parameters = ConfigReader.getInstance().readConfig().parameters;
    OperatorManager operatorManager;
    Problem problem;
    Generator generator;
    IPerturbation perturbation;
    Solution bestSolution;
    Logger logger = Logger.getLogger(VNSALS.class);
    int iter;

    public VNSALS(Problem problem) {
        this.operatorManager = OperatorManager.getInstance(problem);
        this.problem = problem;
        generator = new GreedyGenerator(problem);
        perturbation = new RecreatePerturbation(problem);
    }

    public void run() {
        Solution solution = generator.build();
        bestSolution = new Solution(solution);
        TimeController.setTimeLimit(((Double) parameters.get("maxTime")).intValue());
        TimeController.reset();
        startLogging(solution);
        int noImprove = 0, weightReset=0;
        iter = 0;
        int IterMax = ((Double) parameters.get("iterMax")).intValue();
        int threshold = ((Double) parameters.get("threshold")).intValue();
        while (iter < IterMax & !TimeController.timeIsUp()) {
            operatorManager.sigmaLearn(solution);
            if (solution.getDistance() < bestSolution.getDistance()) {
                bestSolution = new Solution(solution);
                perturbation.reset();
                noImprove = 0;
                iter = 0;
            } else {
                noImprove++;
                iter++;
            }
            if (noImprove>threshold) {
                perturbation.perturb(solution, noImprove);
                noImprove = 0;
                weightReset++;
            }
            if (weightReset>threshold){
                perturbation.reset();
                weightReset = 0;
            }
        }
    }

    public void startLogging(Solution solution){
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        Thread distanceDaemon = new Thread(new Runnable() {
            @Override
            public void run() {
                service.scheduleWithFixedDelay(new Runnable() {
                    public void run() {
                        double currentDistance;
                        synchronized (solution){
                            currentDistance = solution.getDistance();
                        }
                        synchronized (this) {
                            logger.info(String.format("iter:[%06d] best:[%.2f] current:[%.2f]", iter, bestSolution.getDistance(), currentDistance));
                        }
                    }
                }, 0, 1, TimeUnit.SECONDS);
            }
        });
        distanceDaemon.setDaemon(true);

        Thread weightDaemon = new Thread(new Runnable() {
            @Override
            public void run() {
                service.scheduleWithFixedDelay(new Runnable() {
                    public void run() {
                        synchronized (this) {
                            logger.info(String.format("%s", operatorManager.getSuccessRecorder()));
                        }
                    }
                },0,10, TimeUnit.SECONDS);
            }
        });
       weightDaemon.setDaemon(true);

       distanceDaemon.start();
       weightDaemon.start();
    }
}
