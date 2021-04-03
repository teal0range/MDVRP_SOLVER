package Constraints.InnerSwap10Constraints;

import Constraints.HardWeightConstraint;
import Operators.OperationContext;

public class InnerSwap10HardWeightConstraint extends HardWeightConstraint {
    @Override
    public ConsStatus fulfilled(OperationContext context) {
        return ConsStatus.FULFILLED;
    }
}
