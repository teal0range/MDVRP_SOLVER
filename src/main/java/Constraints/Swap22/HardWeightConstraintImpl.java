package Constraints.Swap22;

import Common.Customer;
import Common.Depot;
import Constraints.HardWeightConstraint;
import Operators.OperationContext;

public class HardWeightConstraintImpl extends HardWeightConstraint {
    @Override
    public ConsStatus fulfilled(OperationContext context) {
        Customer mainNode1 = (Customer) context.mainRoute.getNode(context.operatePos[0]);
        Customer mainNode2 = (Customer) context.mainRoute.getNode(context.operatePos[0] + 1);
        Customer sideNode1 = (Customer) context.sideRoute.getNode(context.operatePos[1]);
        Customer sideNode2 = (Customer) context.sideRoute.getNode(context.operatePos[1] + 1);
        int weightChg = mainNode1.need + mainNode2.need - sideNode1.need - sideNode2.need;
        if (context.mainRoute.getWeight() - weightChg > ((Depot) context.mainRoute.start).maxVehicleLoad ||
                context.sideRoute.getWeight() + weightChg > ((Depot) context.sideRoute.start).maxVehicleLoad)
            return ConsStatus.NOT_FULFILLED_BREAK;
        return ConsStatus.FULFILLED;
    }
}
