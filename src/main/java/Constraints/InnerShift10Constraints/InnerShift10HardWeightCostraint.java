package Constraints.InnerShift10Constraints;

import Constraints.HardWeightConstraint;
import Operators.OperationContext;

public class InnerShift10HardWeightCostraint extends HardWeightConstraint {
    @Override
    public ConsStatus fulfilled(OperationContext context) {
        return ConsStatus.FULFILLED;
    }
}
