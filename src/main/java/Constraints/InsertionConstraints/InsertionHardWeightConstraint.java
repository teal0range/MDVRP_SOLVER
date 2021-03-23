package Constraints.InsertionConstraints;

import Common.Node.Customer;
import Common.Node.Depot;
import Constraints.HardWeightConstraint;
import Operators.OperateContext;

public class InsertionHardWeightConstraint extends HardWeightConstraint {

    @Override
    public ConsStatus fulfilled(OperateContext context) {
        int weightLimit = ((Depot)context.mainRoute.start).maxVehicleLoad;
        int currentWeight = context.mainRoute.getWeight();
        if (currentWeight+((Customer)context.operateNodes[0]).need > weightLimit)return ConsStatus.NOT_FULFILLED_BREAK;
        else return ConsStatus.FULFILLED;
    }
}
