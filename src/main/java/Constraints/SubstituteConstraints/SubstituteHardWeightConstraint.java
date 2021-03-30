package Constraints.SubstituteConstraints;

import Common.Node.Customer;
import Common.Node.Depot;
import Constraints.HardWeightConstraint;
import Operators.OperationContext;

public class SubstituteHardWeightConstraint extends HardWeightConstraint {
    @Override
    public ConsStatus fulfilled(OperationContext context) {
        int weightLimit = ((Depot)context.mainRoute.start).maxVehicleLoad;
        int currentWeight = context.mainRoute.getWeight();
        int nextWeight = currentWeight - ((Customer)context.mainRoute.getNode(context.operatePos[0])).need +
                ((Customer)context.operateNodes[0]).need;
        if(nextWeight>weightLimit)
            return ConsStatus.NOT_FULFILLED_BREAK;
        else return ConsStatus.FULFILLED;
    }
}
