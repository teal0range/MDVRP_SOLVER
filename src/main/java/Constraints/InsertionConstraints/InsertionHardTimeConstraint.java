package Constraints.InsertionConstraints;

import Common.Node.Depot;
import Constraints.HardWeightConstraint;
import Operators.OperateContext;

public class InsertionHardTimeConstraint extends HardWeightConstraint {
    @Override
    public ConsStatus fulfilled(OperateContext context) {
        int maxDuration = ((Depot)context.mainRoute.start).maxDuration;
        int currentTimeCost = context.mainRoute.getTimeCost();
        if (currentTimeCost+context.operateNodes[0].duration>maxDuration)return ConsStatus.NOT_FULFILLED_BREAK;
        else return ConsStatus.FULFILLED;
    }
}
