package Constraints.Insertion;

import Common.Customer;
import Common.Depot;
import Constraints.HardWeightConstraint;
import Operators.OperationContext;

public class HardWeightConstraintImpl extends HardWeightConstraint {

    @Override
    public ConsStatus fulfilled(OperationContext context) {
        int weightLimit = ((Depot) context.mainRoute.start).maxVehicleLoad;
        int currentWeight = context.mainRoute.getWeight();
        if (currentWeight + ((Customer) context.operateNodes[0]).need > weightLimit)
            return ConsStatus.NOT_FULFILLED_BREAK;
        else return ConsStatus.FULFILLED;
    }
}
