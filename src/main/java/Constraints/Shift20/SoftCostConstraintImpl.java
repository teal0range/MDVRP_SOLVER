package Constraints.Shift20;

import Common.Node.Customer;
import Common.Node.Node;
import Constraints.SoftCostConstraint;
import Operators.OperationContext;

public class SoftCostConstraintImpl extends SoftCostConstraint {
    /**
     * 计算Shift20前后的cost变化值
     * @param context mainRoute:移出的路径，sideRoute:移入的路径
     *                operatePos[0]:移出第一个节点的位置，operatePos[1]:移入位置的前一个节点的位置。
     * @return Shift20前后的cost变化值
     */
    @Override
    public double fulfilled(OperationContext context) {
        Node node1 = context.mainRoute.getNode(context.operatePos[0]);
        Node node2 = context.mainRoute.getNode(context.operatePos[0]+1);
        double frontDistanceBefore = context.problem.getDistance(node1,context.mainRoute.getNode(context.operatePos[0]-1));
        double rearDistanceBefore = context.problem.getDistance(node2,context.mainRoute.getNode(context.operatePos[0]+2));
        double breakDistanceAfter = context.problem.getDistance(context.mainRoute.getNode(context.operatePos[0]-1),
                context.mainRoute.getNode(context.operatePos[0]+2));
        double frontDistanceAfter = context.problem.getDistance(node1,context.sideRoute.getNode(context.operatePos[1]));
        double rearDistanceAfter = context.problem.getDistance(node2,context.sideRoute.getNode(context.operatePos[1]+1));
        double connectDistanceBefore = context.problem.getDistance(context.sideRoute.getNode(context.operatePos[1]),
                context.sideRoute.getNode(context.operatePos[1]+1));
        return breakDistanceAfter - frontDistanceBefore - rearDistanceBefore
                -connectDistanceBefore + frontDistanceAfter + rearDistanceAfter;
    }
}
