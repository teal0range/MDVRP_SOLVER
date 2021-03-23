package Constraints;

import Operators.OperateContext;

public interface SoftConstraint {
    double fulfilled(OperateContext context);
}
