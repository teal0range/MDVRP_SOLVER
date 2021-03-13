package Constraints.InnerSwap11;

import Common.Node.Node;
import Constraints.InnerWeightConstraint;
import Operators.operateContext;

public class InnerSwap11WeightCons extends InnerWeightConstraint {

    public InnerSwap11WeightCons(double weightLimit) {
        super(weightLimit);
    }

    @Override
    public ConsStatus fulfilled(operateContext context, Node[] nodes) {
        return null;
    }
}
