package Constraints.Swap21;

import Common.Node.Node;
import Constraints.SoftCostConstraint;
import Operators.OperationContext;

public class SoftCostConstraintImpl extends SoftCostConstraint {
    @Override
    public double fulfilled(OperationContext context) {
        Node mainPrev = context.mainRoute.getNode(context.operatePos[0] - 1);
        Node mainNode1 = context.mainRoute.getNode(context.operatePos[0]);
        Node mainNode2 = context.mainRoute.getNode(context.operatePos[0] + 1);
        Node mainNext = context.mainRoute.getNode(context.operatePos[0] + 2);

        Node sidePrev = context.sideRoute.getNode(context.operatePos[1] - 1);
        Node sideNode1 = context.sideRoute.getNode(context.operatePos[1]);
        Node sideNext = context.sideRoute.getNode(context.operatePos[1] + 1);

        return context.problem.getDistance(mainPrev, sideNode1) + context.problem.getDistance(sideNode1, mainNext) +
                context.problem.getDistance(mainNode1, sidePrev) + context.problem.getDistance(mainNode2, sideNext) -
                context.problem.getDistance(mainNode1, mainPrev) - context.problem.getDistance(mainNode2, mainNext) -
                context.problem.getDistance(sideNode1, sidePrev) - context.problem.getDistance(sideNode1, sideNext);
    }
}
