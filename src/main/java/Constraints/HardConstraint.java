package Constraints;

import Operators.InsertionContext;
import Common.Node;

public interface HardConstraint {
    enum ConsStatus{
        NOT_FULFILLED_BREAK, NOT_FULFILLED, FULFILLED,
    }

    ConsStatus fulfilled(InsertionContext context, Node prevNode, Node newNode, Node nextNode);
}
