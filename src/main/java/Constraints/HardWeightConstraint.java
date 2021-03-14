package Constraints;

import Common.Node.Node;
import Operators.operateContext;

public abstract class HardWeightConstraint implements HardConstraint {
    public double weightLimit;

    public HardWeightConstraint(double weightLimit) {
        this.weightLimit = weightLimit;
    }

    @Override
    public abstract ConsStatus fulfilled(operateContext context);
}
