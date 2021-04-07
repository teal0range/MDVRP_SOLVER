package Constraints.Swap11;

import Common.Node.Customer;
import Common.Node.Depot;
import Constraints.HardWeightConstraint;
import Operators.OperationContext;

public class HardWeightConstraintImpl extends HardWeightConstraint {
    @Override
    public ConsStatus fulfilled(OperationContext context) {
        Customer node1 = (Customer) context.mainRoute.getNode(context.operatePos[0]);
        Customer node2 = (Customer) context.sideRoute.getNode(context.operatePos[1]);
        if (node1.need - node2.need + context.sideRoute.getWeight() > ((Depot)context.sideRoute.start).maxVehicleLoad ||
                node2.need - node1.need + context.mainRoute.getWeight() > ((Depot)context.mainRoute.start).maxVehicleLoad)
            return ConsStatus.NOT_FULFILLED_BREAK;
        return ConsStatus.FULFILLED;
    }
}
