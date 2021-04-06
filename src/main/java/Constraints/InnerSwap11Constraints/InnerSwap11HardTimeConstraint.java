package Constraints.InnerSwap11Constraints;

import Constraints.HardTimeConstraint;
import Operators.OperationContext;

public class InnerSwap11HardTimeConstraint extends HardTimeConstraint {
    @Override
    public ConsStatus fulfilled(OperationContext context) {
        return ConsStatus.FULFILLED;
    }
}
