package Constraints.Swap11;

import Common.Customer;
import Common.Depot;
import Operators.OperationContext;

public class HardTimeConstraintImpl extends Constraints.HardTimeConstraint {
    @Override
    public ConsStatus fulfilled(OperationContext context) {
        Customer node1 = (Customer) context.mainRoute.getNode(context.operatePos[0]);
        Customer node2 = (Customer) context.sideRoute.getNode(context.operatePos[1]);
        if (node1.duration - node2.duration + context.sideRoute.getTimeCost() > ((Depot) context.sideRoute.start).maxDuration ||
                node2.duration - node1.duration + context.mainRoute.getTimeCost() > ((Depot) context.mainRoute.start).maxDuration)
            return ConsStatus.NOT_FULFILLED_BREAK;
        return ConsStatus.FULFILLED;
    }
}
