package Constraints.InnerShift10;

import Common.Node.Node;
import Constraints.InnerWeightConstraint;
import Operators.operateContext;

public class InnerShift10WeightCons extends InnerWeightConstraint {
    public InnerShift10WeightCons(double weightLimit) {
        super(weightLimit);
    }

    @Override
    public ConsStatus fulfilled(operateContext context, Node[] nodes) {
        return null;
    }
}
