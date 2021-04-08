package Constraints.Shift20;

import Common.Node.Customer;
import Common.Node.Depot;
import Constraints.HardTimeConstraint;
import Operators.OperationContext;

public class HardTimeConstraintImpl extends HardTimeConstraint {
    @Override
    public ConsStatus fulfilled(OperationContext context) {
        Customer cus1 = (Customer) context.mainRoute.getNode(context.operatePos[0]);
        Customer cus2 = (Customer) context.mainRoute.getNode(context.operatePos[0]+1);
        int timeChg = cus1.duration + cus2.duration;
        if (context.sideRoute.getTimeCost() + timeChg > ((Depot) context.sideRoute.start).maxDuration)return ConsStatus.NOT_FULFILLED_BREAK;
        return ConsStatus.FULFILLED;
    }
}
