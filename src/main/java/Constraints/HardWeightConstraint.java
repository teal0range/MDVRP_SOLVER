package Constraints;

import Operators.OperateContext;

public abstract class HardWeightConstraint implements HardConstraint {
    @Override
    public abstract ConsStatus fulfilled(OperateContext context);
}
