package Constraints.OuterShift10Constraints;

import Common.Node.Customer;
import Common.Node.Depot;
import Common.Node.Node;
import Constraints.HardWeightConstraint;
import Operators.OperationContext;

public class OuterShift10HardWeightConstraint extends HardWeightConstraint {
    @Override
    public ConsStatus fulfilled(OperationContext context) {
        Customer prev = (Customer) context.mainRoute.getNode(context.operatePos[0]);
        if (prev.need + context.sideRoute.getWeight() > ((Depot)context.sideRoute.start).maxVehicleLoad)return ConsStatus.NOT_FULFILLED_BREAK;
        return ConsStatus.FULFILLED;
    }
}
