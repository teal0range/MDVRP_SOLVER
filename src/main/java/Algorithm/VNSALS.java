package Algorithm;

import Common.Problem;
import Common.Solution;
import IO.ConfigReader;
import Operators.IPerturbation;
import Operators.OperatorManager;
import Operators.RecreatePerturbation;
import Utils.TimeController;
import org.apache.log4j.Logger;

import java.util.Map;

public class VNSALS {
    Map<String, Object> parameters = ConfigReader.getInstance().readConfig().parameters;
    OperatorManager operatorManager;
    Problem problem;
    Generator generator;
    IPerturbation perturbation;
    Solution bestSolution;
    Logger logger = Logger.getLogger(VNSALS.class);

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
        int p = 1, iter = 0;
        int IterMax = ((Double) parameters.get("iterMax")).intValue();
        int threshold = ((Double) parameters.get("threshold")).intValue();
        while (iter < IterMax & !TimeController.timeIsUp()) {
            operatorManager.sigmaLearn(solution);
            if (solution.getDistance() < bestSolution.getDistance()) {
                bestSolution = new Solution(solution);
                logger.info(bestSolution.getDistance());
                perturbation.reset();
                p = 1;
                iter = 0;
            } else {
                p++;
                iter++;
            }
            if (p>threshold) {
                perturbation.perturb(solution, p);
                p = 1;
            }
        }
    }
}
