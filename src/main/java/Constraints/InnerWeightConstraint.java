package Constraints;

import Common.Node.Node;
import Operators.operateContext;

public abstract class InnerWeightConstraint implements InnerConstraint {
    public double weightLimit;

    public InnerWeightConstraint(double weightLimit) {
        this.weightLimit = weightLimit;
    }

    @Override
    public abstract ConsStatus fulfilled(operateContext context, Node []nodes);
}
