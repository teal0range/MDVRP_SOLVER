package Constraints;

import Operators.operateContext;
import Common.Node.Node;

public interface HardConstraint {
    enum ConsStatus{
        NOT_FULFILLED_BREAK, NOT_FULFILLED, FULFILLED,
    }

    ConsStatus fulfilled(operateContext context);
}
