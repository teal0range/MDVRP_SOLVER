package Operators;

import Common.Problem;
import Common.Solution;

import java.util.ArrayList;

public abstract class BaseOperator {
    Problem problem;

    public BaseOperator(Problem problem) {
        this.problem = problem;
    }

    public abstract void operate(Solution sol);
    public abstract void singleOperate(operateContext context);
}
