package Constraints.Shift10;

import Common.Customer;
import Common.Depot;
import Constraints.HardTimeConstraint;
import Operators.OperationContext;

public class HardTimeConstraintImpl extends HardTimeConstraint {
    @Override
    public ConsStatus fulfilled(OperationContext context) {
        Customer prev = (Customer) context.mainRoute.getNode(context.operatePos[0]);
        if (prev.duration + context.sideRoute.getTimeCost() > ((Depot) context.sideRoute.start).maxDuration)
            return ConsStatus.NOT_FULFILLED_BREAK;
        return ConsStatus.FULFILLED;
    }
}
