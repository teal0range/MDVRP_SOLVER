package Algorithm;

import Common.Problem;
import Common.Solution;
import IO.BasicConfig;
import IO.ConfigReader;
import Operators.IPerturbation;
import Operators.Operator;
import Operators.OperatorManager;

import java.util.Map;

public class VNSALS implements Runnable{
    Map<String,Object> parameters = ConfigReader.getInstance().readConfig().parameters;
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

    @Override
    public void run() {
        Solution solution = generator.build();
        bestSolution = new Solution(solution);
        int p=1,k=1,iter=0,iterAdaptive=0;
        int IterMax = (int) parameters.get("iterMax");
        double AlsTraining = (double) parameters.get("alsTraining");
        int MaxLevel = (int) parameters.get("maxLevel");
        int Nshake = (int) parameters.get("Nshake");
        double AlsReset = (double) parameters.get("alsReset");
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
