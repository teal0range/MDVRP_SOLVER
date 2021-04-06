package Constraints.InnerSwap11Constraints;

import Constraints.HardWeightConstraint;
import Operators.OperationContext;

public class InnerSwap11HardWeightConstraint extends HardWeightConstraint {
    @Override
    public ConsStatus fulfilled(OperationContext context) {
        return ConsStatus.FULFILLED;
    }
}
