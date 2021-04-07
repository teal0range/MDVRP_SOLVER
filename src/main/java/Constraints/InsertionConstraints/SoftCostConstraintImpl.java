package Constraints.InsertionConstraints;

import Common.Node.Node;
import Constraints.SoftCostConstraint;
import Operators.OperationContext;

public class SoftCostConstraintImpl extends SoftCostConstraint {
    /**
     * return change of distance after an insertion
     * @param context param mainRoute, operateNodes and operatePos is used, with operateNode[0] representing
     *                the node to operate and operatePos[0] representing the pos to insert;
     * @return change of cost
     */
    @Override
    public double fulfilled(OperationContext context) {
        int pos = context.operatePos[0];
        Node prev = context.mainRoute.getNode(pos-1);
        Node next = context.mainRoute.getNode(pos);
        return context.problem.getDistance(context.operateNodes[0],prev)+
                context.problem.getDistance(context.operateNodes[0],next)-
                context.problem.getDistance(prev,next);
    }
}
