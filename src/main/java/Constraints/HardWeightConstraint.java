package Constraints;

import Operators.OperateContext;

public abstract class HardWeightConstraint implements HardConstraint {
    public double weightLimit;

    public HardWeightConstraint(double weightLimit) {
        this.weightLimit = weightLimit;
    }

    @Override
    public abstract ConsStatus fulfilled(OperateContext context);
}
