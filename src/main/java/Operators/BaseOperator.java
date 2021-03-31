package Operators;

import Common.Problem;
import Common.Solution;

public abstract class BaseOperator implements OperateAll, OperateBest{
    Problem problem;

    public BaseOperator(Problem problem) {
        this.problem = problem;
    }

    public abstract void singleOperate(Solution solution, OperationContext context);
}
