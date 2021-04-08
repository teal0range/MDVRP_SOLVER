package Constraints.Swap22;

import Common.Node.Customer;
import Common.Node.Depot;
import Operators.OperationContext;

public class HardTimeConstraintImpl extends Constraints.HardTimeConstraint {
    @Override
    public ConsStatus fulfilled(OperationContext context) {
        Customer mainNode1 = (Customer) context.mainRoute.getNode(context.operatePos[0]);
        Customer mainNode2 = (Customer) context.mainRoute.getNode(context.operatePos[0] + 1);
        Customer sideNode1 = (Customer) context.sideRoute.getNode(context.operatePos[1]);
        Customer sideNode2 = (Customer) context.sideRoute.getNode(context.operatePos[1] + 1);
        int timeChg = mainNode1.duration + mainNode2.duration - sideNode1.duration - sideNode2.duration;
        if (context.mainRoute.getTimeCost() - timeChg > ((Depot) context.mainRoute.start).maxDuration ||
                context.sideRoute.getTimeCost() + timeChg > ((Depot) context.sideRoute.start).maxDuration)
            return ConsStatus.NOT_FULFILLED_BREAK;
        return ConsStatus.FULFILLED;
    }
}
