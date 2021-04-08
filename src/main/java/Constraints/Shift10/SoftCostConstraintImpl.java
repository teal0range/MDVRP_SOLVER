package Constraints.Shift10;

import Common.Node.Node;
import Constraints.SoftCostConstraint;
import Operators.OperationContext;

public class SoftCostConstraintImpl extends SoftCostConstraint {
    /**
     * 两条路径之间的shift
     *
     * @param context mainRoute:移除点的路径，sideRoute:插入点的路径 operatePos[0]
     *                移出路径中点的位置，operatePos[1]移入点路径位置之前的一个点的位置。
     *                注意sideRoute和mainRoute尽量不要相同
     * @return 两条路径之间的shift Cost的该变量
     */
    @Override
    public double fulfilled(OperationContext context) {
        int prevPos = context.operatePos[0];
        int currentPos = context.operatePos[1];
        Node operateNode = context.mainRoute.getNode(prevPos);
        Node prevPrevNode = context.mainRoute.getNode(prevPos - 1);
        Node nextPrevNode = context.mainRoute.getNode(prevPos + 1);
        Node prevNextNode = context.sideRoute.getNode(currentPos);
        Node nextNextNode = context.sideRoute.getNode(currentPos + 1);
        return context.problem.getDistance(prevPrevNode, nextPrevNode) -
                context.problem.getDistance(prevPrevNode, operateNode) -
                context.problem.getDistance(operateNode, nextPrevNode) +
                context.problem.getDistance(operateNode, nextNextNode) +
                context.problem.getDistance(operateNode, prevNextNode) -
                context.problem.getDistance(prevNextNode, nextNextNode);
    }
}
