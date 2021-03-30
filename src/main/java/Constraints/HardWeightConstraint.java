package Constraints;

import Operators.OperationContext;

public abstract class HardWeightConstraint implements HardConstraint {
    @Override
    public abstract ConsStatus fulfilled(OperationContext context);
}
