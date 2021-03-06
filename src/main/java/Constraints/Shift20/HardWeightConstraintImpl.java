package Constraints.Shift20;

import Common.Customer;
import Common.Depot;
import Constraints.HardWeightConstraint;
import Operators.OperationContext;

public class HardWeightConstraintImpl extends HardWeightConstraint {
    @Override
    public ConsStatus fulfilled(OperationContext context) {
        Customer cus1 = (Customer) context.mainRoute.getNode(context.operatePos[0]);
        Customer cus2 = (Customer) context.mainRoute.getNode(context.operatePos[0] + 1);
        int weightChg = cus1.need + cus2.need;
        if (context.sideRoute.getWeight() + weightChg > ((Depot) context.sideRoute.start).maxVehicleLoad)
            return ConsStatus.NOT_FULFILLED_BREAK;
        return ConsStatus.FULFILLED;
    }
}
