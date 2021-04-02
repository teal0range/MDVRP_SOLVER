package Operators;

import Common.Problem;
import Common.Solution;
import Constraints.HardConstraintManager;
import Constraints.SoftConstraintManager;

public abstract class BaseOperator implements Operate {
    Problem problem;
    HardConstraintManager hardConstraintManager;
    SoftConstraintManager softConstraintManager;

    public BaseOperator(Problem problem) {
        this.problem = problem;
        softConstraintManager = getSoftConstraintManager();
        hardConstraintManager = getHardConstraintManager();
    }

    public abstract SoftConstraintManager getSoftConstraintManager();
    public abstract HardConstraintManager getHardConstraintManager();
    public abstract void singleOperate(Solution solution, OperationContext context);
}
