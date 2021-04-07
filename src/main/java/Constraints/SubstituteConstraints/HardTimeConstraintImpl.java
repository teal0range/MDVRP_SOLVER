package Constraints.SubstituteConstraints;

import Common.Node.Depot;
import Constraints.HardTimeConstraint;
import Operators.OperationContext;

public class HardTimeConstraintImpl extends HardTimeConstraint {
    @Override
    public ConsStatus fulfilled(OperationContext context) {
        int maxDuration = ((Depot)context.mainRoute.start).maxDuration;
        int currentTimeCost = context.mainRoute.getTimeCost();
        int nextTimeCost = currentTimeCost - context.mainRoute.getNode(context.operatePos[0]).duration +
                context.operateNodes[0].duration;
        if(nextTimeCost > maxDuration)
            return ConsStatus.NOT_FULFILLED_BREAK;
        else return ConsStatus.FULFILLED;
    }
}
