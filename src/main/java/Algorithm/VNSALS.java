package Algorithm;

import Common.Problem;
import Common.Solution;
import Operators.OperatorManager;

public class VNSALS{
    OperatorManager operatorManager;
    Problem problem;
    Generator generator;

    public VNSALS(Problem problem) {
        this.operatorManager = OperatorManager.getInstance(problem);
        this.problem = problem;
        generator = new GreedyGenerator(problem);
    }


    private static class OptimizeJob implements Runnable{
        Solution solution;

        public OptimizeJob(Solution solution) {
            this.solution = solution;
        }

        @Override
        public void run() {

        }
    }
}
