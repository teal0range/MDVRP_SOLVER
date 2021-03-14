package Constraints.InnerShift10;

import Common.Node.Node;
import Constraints.HardWeightConstraint;
import Operators.operateContext;

public class HardShift10WeightCons extends HardWeightConstraint {
    public HardShift10WeightCons(double weightLimit) {
        super(weightLimit);
    }

    @Override
    public ConsStatus fulfilled(operateContext context) {
        return null;
    }
}
