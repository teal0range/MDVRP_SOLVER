package Constraints.InsertionConstraints;

import Common.Node.Depot;
import Constraints.HardTimeConstraint;
import Operators.OperationContext;

public class InsertionHardTimeConstraint extends HardTimeConstraint {
    @Override
    public ConsStatus fulfilled(OperationContext context) {
        int maxDuration = ((Depot)context.mainRoute.start).maxDuration;
        int currentTimeCost = context.mainRoute.getTimeCost();
        if (currentTimeCost+context.operateNodes[0].duration>maxDuration)return ConsStatus.NOT_FULFILLED_BREAK;
        else return ConsStatus.FULFILLED;
    }
}
