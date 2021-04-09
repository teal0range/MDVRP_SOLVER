package Constraints.TwoOpt;

import Common.Node;
import Constraints.SoftCostConstraint;
import Operators.OperationContext;

public class SoftCostConstraintImpl extends SoftCostConstraint {
    /**
     * 2opt costChg
     *
     * @param context mainRoute:主路
     *                operatePos[0]:路径前对节点的第一个元素位置
     *                operatePos[1]:路径后对节点的第一个元素位置
     * @return 2opt costChg
     */
    @Override
    public double fulfilled(OperationContext context) {
        Node startNode1 = context.mainRoute.getNode(context.operatePos[0]); //前一对节点第一个元素
        Node endNode1 = context.mainRoute.getNode(context.operatePos[1]);
        double distanceBefore = context.problem.getDistance(startNode1, context.mainRoute.getNode(context.operatePos[0] + 1)) +
                context.problem.getDistance(endNode1, context.mainRoute.getNode(context.operatePos[1] + 1));
        double distanceAfter = context.problem.getDistance(startNode1, endNode1) +
                context.problem.getDistance(context.mainRoute.getNode(context.operatePos[0] + 1),
                        context.mainRoute.getNode(context.operatePos[1] + 1));
        return distanceAfter - distanceBefore;
    }
}
