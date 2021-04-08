package Constraints;

import Operators.OperationContext;

public abstract class HardTimeConstraint implements HardConstraint {
    @Override
    public abstract ConsStatus fulfilled(OperationContext context);
}
