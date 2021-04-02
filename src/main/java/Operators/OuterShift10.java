package Operators;

import Common.Problem;
import Common.Solution;
import Constraints.HardConstraintManager;
import Constraints.SoftConstraint;
import Constraints.SoftConstraintManager;

public class OuterShift10 extends BaseOperator{
    public OuterShift10(Problem problem) {
        super(problem);
    }

    @Override
    public SoftConstraintManager getSoftConstraintManager() {
        return SoftConstraintManager.getInstance(this.getClass());
    }

    @Override
    public HardConstraintManager getHardConstraintManager() {
        return HardConstraintManager.getInstance(this.getClass());
    }

    @Override
    public void singleOperate(Solution solution, OperationContext context) {

    }

    @Override
    public void doOperateAll(Solution solution) {

    }

    @Override
    public void doOperateBest(Solution solution) {

    }
}
