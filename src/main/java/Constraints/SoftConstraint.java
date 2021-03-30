package Constraints;

import Operators.OperationContext;

public interface SoftConstraint extends Constraint{
    /**
     *
     * @param context
     * @return
     */
    double fulfilled(OperationContext context);
}
