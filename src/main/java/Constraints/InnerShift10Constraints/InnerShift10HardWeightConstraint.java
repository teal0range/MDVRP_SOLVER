package Constraints.InnerShift10Constraints;

import Constraints.HardWeightConstraint;
import Operators.OperationContext;

public class InnerShift10HardWeightConstraint extends HardWeightConstraint {
    @Override
    public ConsStatus fulfilled(OperationContext context) {
        return ConsStatus.FULFILLED;
    }
}
