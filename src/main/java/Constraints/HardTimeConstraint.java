package Constraints;

import Operators.OperateContext;

public abstract class HardTimeConstraint implements HardConstraint{
    @Override
    public abstract ConsStatus fulfilled(OperateContext context);
}
