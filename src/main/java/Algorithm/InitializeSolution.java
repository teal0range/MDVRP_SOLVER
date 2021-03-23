package Algorithm;

import Common.Problem;
import Common.Solution;
import Operators.BaseOperator;

import java.util.ArrayList;

public class InitializeSolution {
    Problem problem;
    Solution solution;
    ArrayList<BaseOperator> operators;

    public InitializeSolution(Problem problem) {
        this.problem = problem;
    }

    private void prepare(){
    }

    public Solution getSolution() {
        prepare();
        return solution;
    }



}
