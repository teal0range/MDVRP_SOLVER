package Constraints;

import Operators.OperateContext;

public interface SoftConstraint extends Constraint{
    double fulfilled(OperateContext context);
}
