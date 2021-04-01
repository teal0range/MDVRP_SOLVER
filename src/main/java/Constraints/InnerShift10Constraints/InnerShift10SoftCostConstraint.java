package Constraints.InnerShift10Constraints;

import Common.Node.Node;
import Common.Route;
import Constraints.SoftCostConstraint;
import Operators.OperationContext;

public class InnerShift10SoftCostConstraint extends SoftCostConstraint {
    /**
     * 返回InnerShift10一次操作之后的成本增减
     * @param context 只涉及到mainRoute 以及 operatePos，其中operatePos[0]是原先点的位置，operatePos[1]是插入位置（原路径）
     * @return InnerShift10 成本变化
     */
    @Override
    public double fulfilled(OperationContext context) {
        int prevPos = context.operatePos[0];
        int currentPos = context.operatePos[1];
        Node operateNode = context.mainRoute.getNode(prevPos);
        Node prevPrevNode = context.mainRoute.getNode(prevPos - 1);
        Node nextPrevNode = context.mainRoute.getNode(prevPos + 1);
        Node prevNextNode = context.mainRoute.getNode(currentPos - 1);
        Node nextNextNode = context.mainRoute.getNode(currentPos);
        return context.problem.getDistance(prevPrevNode,nextPrevNode) -
                context.problem.getDistance(prevPrevNode,operateNode) -
                context.problem.getDistance(operateNode,nextPrevNode) +
                context.problem.getDistance(operateNode,nextNextNode) +
                context.problem.getDistance(operateNode,prevNextNode) -
                context.problem.getDistance(prevNextNode,nextNextNode);
    }
}
