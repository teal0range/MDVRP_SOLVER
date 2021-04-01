package Constraints.InnerShift10Constraints;

import Constraints.HardTimeConstraint;
import Operators.OperationContext;

public class InnerShift10HardTimeConstraint extends HardTimeConstraint {
    @Override
    public ConsStatus fulfilled(OperationContext context) {
        return ConsStatus.FULFILLED;
    }
}
