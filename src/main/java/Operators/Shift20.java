package Operators;

import Common.Problem;
import Common.Route;
import Common.Solution;
import Constraints.HardConstraint;

public class Shift20 extends BaseOperator {
    public Shift20(Problem problem) {
        super(problem);
    }

    @Override
    public void singleOperate(Solution solution, OperationContext context) {
        if (context.mainRoute == context.sideRoute || context.sideRoute == null) {
            context.mainRoute.innerShift20(context.operatePos[0], context.operatePos[1]);
        } else {
            context.mainRoute.shift20(context.sideRoute, context.operatePos[0], context.operatePos[1]);
        }
    }

    @Override
    public void doOperateAll(Solution solution) {
        OperationContext context = new OperationContext.Builder(problem, OperationContext.operatorType.Shift10).
                setOperatePos(new Integer[2]).build();
        for (Route mainRoute : solution.getRoutes()) {
            context.setMainRoute(mainRoute);
            for (Route sideRoute : solution.getRoutes()) {
                context.setSideRoute(sideRoute);
                for (int i = 0; i < mainRoute.length() - 1; i++) {
                    context.setOperatePos(0, i);
                    for (int j = -1; j < sideRoute.length() - 1; j++) { //插入在指定节点之后
                        if (mainRoute == sideRoute && Math.abs(j - i) <= 1) continue;
                        context.setOperatePos(1, j);
                        HardConstraint.ConsStatus status = hardConstraintManager.fulfilled(context);
                        double costChg = softConstraintManager.fulfilled(context);
                        if (status == HardConstraint.ConsStatus.FULFILLED && costChg < 0) {
                            singleOperate(solution, context);
                            if (i >= mainRoute.length() - 1) break; // shift 结点后，路径可能变短
                        }
                    }
                }
            }
        }
    }

    @Override
    public void doOperateBest(Solution solution) {

    }

    @Override
    public void doOperateRandom(Solution solution, double threshold) {

    }
}
