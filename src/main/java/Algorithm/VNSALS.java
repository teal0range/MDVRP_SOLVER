package Algorithm;

import Common.Problem;
import Common.Solution;
import Operators.IPerturbation;
import Operators.Operator;
import Operators.OperatorManager;

public class VNSALS{
    OperatorManager operatorManager;
    Problem problem;
    Generator generator;
    IPerturbation perturbation;
    Solution bestSolution;

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

    public void randomNeighborhood(Solution solution){
        Operator operator = operatorManager.randomOpt();
        double costBefore = solution.getDistance();
        operator.doOperateAll(solution);
        if (costBefore < solution.getDistance()){
            operatorManager.incrementRecorder(operator);
        }
    }
}
