package Constraints;

import Operators.OperationContext;

public interface HardConstraint extends Constraint {
    ConsStatus fulfilled(OperationContext context);

    enum ConsStatus {
        NOT_FULFILLED_BREAK, NOT_FULFILLED, FULFILLED,
    }
}
