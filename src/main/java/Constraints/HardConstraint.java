package Constraints;

import Operators.OperateContext;

public interface HardConstraint {
    enum ConsStatus{
        NOT_FULFILLED_BREAK, NOT_FULFILLED, FULFILLED,
    }

    ConsStatus fulfilled(OperateContext context);
}
