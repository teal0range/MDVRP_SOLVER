package Operators;

import Common.Solution;
import Constraints.HardConstraintManager;
import Constraints.SoftConstraintManager;

public interface ConstrainedOpt {
    default SoftConstraintManager getSoftConstraintManager() {
        return SoftConstraintManager.getInstance(this.getClass());
    }
    default HardConstraintManager getHardConstraintManager() {
        return HardConstraintManager.getInstance(this.getClass());
    }
    void singleOperate(Solution solution, OperationContext context);
}
