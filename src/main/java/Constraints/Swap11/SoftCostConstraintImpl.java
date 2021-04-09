package Constraints.Swap11;

import Common.Node;
import Constraints.SoftCostConstraint;
import Operators.OperationContext;

public class SoftCostConstraintImpl extends SoftCostConstraint {
    @Override
    public double fulfilled(OperationContext context) {
        int pos1 = context.operatePos[0];
        int pos2 = context.operatePos[1];
        Node node1 = context.mainRoute.getNode(pos1);
        Node node2 = context.sideRoute.getNode(pos2);
        Node node1prev = context.mainRoute.getNode(pos1 - 1);
        Node node2prev = context.sideRoute.getNode(pos2 - 1);
        Node node1next = context.mainRoute.getNode(pos1 + 1);
        Node node2next = context.sideRoute.getNode(pos2 + 1);
        double prevCost = context.problem.getDistance(node1prev, node1) +
                context.problem.getDistance(node1, node1next) +
                context.problem.getDistance(node2prev, node2) +
                context.problem.getDistance(node2, node2next);
        double nextCost = context.problem.getDistance(node1prev, node2) +
                context.problem.getDistance(node2, node1next) +
                context.problem.getDistance(node2prev, node1) +
                context.problem.getDistance(node1, node2next);
        return nextCost - prevCost;
    }
}
