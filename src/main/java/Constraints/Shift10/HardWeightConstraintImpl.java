package Constraints.Shift10;

import Common.Node.Customer;
import Common.Node.Depot;
import Constraints.HardWeightConstraint;
import Operators.OperationContext;

public class HardWeightConstraintImpl extends HardWeightConstraint {
    @Override
    public ConsStatus fulfilled(OperationContext context) {
        Customer prev = (Customer) context.mainRoute.getNode(context.operatePos[0]);
        if (prev.need + context.sideRoute.getWeight() > ((Depot)context.sideRoute.start).maxVehicleLoad)return ConsStatus.NOT_FULFILLED_BREAK;
        return ConsStatus.FULFILLED;
    }
}
