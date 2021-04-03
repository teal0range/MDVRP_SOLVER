package Constraints.InnerSwap10Constraints;

import Constraints.HardTimeConstraint;
import Operators.OperationContext;

public class InnerSwap10HardTimeConstraint extends HardTimeConstraint {
    @Override
    public ConsStatus fulfilled(OperationContext context) {
        return ConsStatus.FULFILLED;
    }
}
