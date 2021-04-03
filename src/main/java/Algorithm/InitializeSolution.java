package Algorithm;

import Common.Problem;
import Common.Solution;
import org.apache.log4j.Logger;

public class InitializeSolution {
    Logger logger = Logger.getLogger(InitializeSolution.class);
    Problem problem;
    Solution solution;
    Generator generator;

    public InitializeSolution(Problem problem) {
        this.problem = problem;
        this.generator = new GreedyGenerator(problem);
    }

    public void setGenerator(Generator generator) {
        this.generator = generator;
    }

    private void prepare(){
        solution = generator.build();
    }

    public Solution getSolution() {
        prepare();
        return solution;
    }



}
