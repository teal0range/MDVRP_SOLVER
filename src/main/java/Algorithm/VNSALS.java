package Algorithm;

import Common.Problem;
import Common.Solution;
import IO.ConfigReader;
import Operators.IPerturbation;
import Operators.OperatorManager;
import Operators.RecreatePerturbation;
import Utils.TimeController;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
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
        RuntimeLogger runtimeLogger = new RuntimeLogger(solution, logger);
        runtimeLogger.start();
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
//                weightReset++;
            }
//            if (weightReset>threshold){
//                perturbation.reset();
//                weightReset = 0;
//            }
        }
        runtimeLogger.stop();
    }
    private class RuntimeLogger{
        public final ScheduledExecutorService service;
        public final ArrayList<Runnable> tasks = new ArrayList<>();
        public final Solution solution;
        public final Logger logger;

        public RuntimeLogger(Solution solution, Logger logger) {
            this.solution = solution;
            this.logger = logger;
            tasks.add(new Runnable() {
                @Override
                public void run() {
                    double currentDistance;
                    synchronized (RuntimeLogger.this.solution){
                        currentDistance = RuntimeLogger.this.solution.getDistance();
                    }
                    synchronized (this) {
                        logger.info(String.format("iter:[%06d] best:[%.2f] current:[%.2f]", iter, bestSolution.getDistance(), currentDistance));
                    }
                }
            });
            tasks.add(new Runnable() {
                @Override
                public void run() {
                    synchronized (this) {
                        logger.info(String.format("%s", operatorManager.getSuccessRecorder()));
                    }
                }
            });
            service = new ScheduledThreadPoolExecutor(tasks.size());
        }

        public void start(){
            service.scheduleWithFixedDelay(tasks.get(0),0,1,TimeUnit.SECONDS);
            service.scheduleWithFixedDelay(tasks.get(1),0,10,TimeUnit.SECONDS);
        }

        public void stop(){
            service.shutdown();
        }
    }
}
