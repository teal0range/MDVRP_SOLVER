package Constraints;

import Operators.OperationContext;

public interface HardConstraint extends Constraint{
    enum ConsStatus{
        NOT_FULFILLED_BREAK, NOT_FULFILLED, FULFILLED,
    }

    ConsStatus fulfilled(OperationContext context);
}
