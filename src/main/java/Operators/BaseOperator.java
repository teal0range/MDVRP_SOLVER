package Operators;

import Common.Problem;
import Common.Solution;

public abstract class BaseOperator {
    Problem problem;

    public BaseOperator(Problem problem) {
        this.problem = problem;
    }

    public abstract void operate(Solution sol);
    public abstract void singleOperate(Solution solution, OperationContext context);
}
