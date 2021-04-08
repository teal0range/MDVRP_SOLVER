package Constraints.TwoOpt;

import Operators.OperationContext;

public class HardTimeConstraintImpl extends Constraints.HardTimeConstraint {
    @Override
    public ConsStatus fulfilled(OperationContext context) {
        return ConsStatus.FULFILLED;
    }
}
