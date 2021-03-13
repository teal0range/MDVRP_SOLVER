package Constraints;

import Common.Node;
import Operators.InsertionContext;

public class WeightConstraint implements HardConstraint{
    @Override
    public ConsStatus fulfilled(InsertionContext context, Node prevNode, Node newNode, Node nextNode) {
        return null;
    }
}
