package Constraints.InnerSwap11;

import Common.Node.Node;
import Constraints.HardWeightConstraint;
import Operators.operateContext;

public class HardSwap11WeightCons extends HardWeightConstraint {

    public HardSwap11WeightCons(double weightLimit) {
        super(weightLimit);
    }

    @Override
    public ConsStatus fulfilled(operateContext context) {
        return null;
    }
}
