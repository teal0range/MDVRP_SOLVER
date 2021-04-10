package Operators;

import Common.Problem;
import Common.Route;
import Common.Solution;
import Constraints.HardConstraint;
import Utils.RandomController;

import java.util.ArrayList;

public class Shift10 extends Operator {
    public Shift10(Problem problem) {
        super(problem);
    }

    @Override
    public void singleOperate(Solution solution, OperationContext context) {
        if (context.mainRoute == context.sideRoute || context.sideRoute == null) {
            context.mainRoute.innerShift10(context.operatePos[0], context.operatePos[1]);
        } else {
            context.mainRoute.shift10(context.sideRoute, context.operatePos[0], context.operatePos[1]);
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
                for (int i = 0; i < mainRoute.length(); i++) {
                    context.setOperatePos(0, i);
                    for (int j = -1; j < sideRoute.length() - 1; j++) { //插入在指定节点之后
                        if (mainRoute == sideRoute && (j == i - 1 || j == i)) continue;
                        context.setOperatePos(1, j);
                        HardConstraint.ConsStatus status = hardConstraintManager.fulfilled(context);
                        double costChg = softConstraintManager.fulfilled(context);
                        if (status == HardConstraint.ConsStatus.FULFILLED && costChg < 0) {
                            singleOperate(solution, context);
                            if (i >= mainRoute.length()) break; // shift 结点后，路径可能变短
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
        OperationContext context = new OperationContext.Builder(problem, OperationContext.operatorType.Shift10).
                setOperatePos(new Integer[2]).build();
        for (Route mainRoute : solution.getRoutes()) {
            context.setMainRoute(mainRoute);
            ArrayList<Integer> mainRndIndex = RandomController.randIndex(mainRoute.length());
            for (Route sideRoute : solution.getRoutes()) {
                context.setSideRoute(sideRoute);
                ArrayList<Integer> sideRndIndex = RandomController.randIndex(sideRoute.length());
                for (int i : mainRndIndex) {
                    context.setOperatePos(0, i);
                    for (int j : sideRndIndex) { //插入在指定节点之后
                        if (mainRoute == sideRoute && (j == i - 1 || j == i)) continue;
                        context.setOperatePos(1, j - 1);
                        HardConstraint.ConsStatus status = hardConstraintManager.fulfilled(context);
                        double costChg = softConstraintManager.fulfilled(context);
                        if (status == HardConstraint.ConsStatus.FULFILLED && costChg < threshold) {
                            singleOperate(solution, context);
                            return;
                        }
                    }
                }
            }
        }
    }
}
