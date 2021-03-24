package Constraints;

import Operators.OperateContext;

public interface SoftConstraint extends Constraint{
    /**
     *
     * @param context
     * @return
     */
    double fulfilled(OperateContext context);
}
