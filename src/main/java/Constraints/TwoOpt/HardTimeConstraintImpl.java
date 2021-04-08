package Constraints.TwoOpt;

import Common.Node.Customer;
import Common.Node.Depot;
import Operators.OperationContext;

public class HardTimeConstraintImpl extends Constraints.HardTimeConstraint {
    @Override
    public ConsStatus fulfilled(OperationContext context) {
        return ConsStatus.FULFILLED;
    }
}
