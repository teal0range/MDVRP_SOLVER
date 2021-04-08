package Operators;

import Common.Node.Customer;
import Common.Problem;
import Common.Route;
import Common.Solution;
import Constraints.HardConstraint;

import java.util.HashMap;

public class TwoOptStar2 extends BaseOperator {
    public TwoOptStar2(Problem problem) {
        super(problem);
    }

    @Override
    public void singleOperate(Solution solution, OperationContext context) {
        context.mainRoute.twoOptStar2(context.sideRoute, context.operatePos[0], context.operatePos[1]);
    }

    @Override
    public void doOperateAll(Solution solution) {
        OperationContext context = new OperationContext.Builder(problem, OperationContext.operatorType.TwoOptStar1).
                setOperatePos(new Integer[2]).setOperateVal(new HashMap<>()).build();
        int[] time = new int[]{0, 0};
        int[] weight = new int[]{0, 0};
        context.setOperateVal("Time", time);
        context.setOperateVal("Weight", weight);
        for (Route mainRoute : solution.getRoutes()) {
            context.setMainRoute(mainRoute);
            for (Route sideRoute : solution.getRoutes()) {
                if (mainRoute == sideRoute || mainRoute.start != sideRoute.start) continue;
                context.setSideRoute(sideRoute);
                for (int i = 0; i < mainRoute.length() - 1; i++) {
                    context.setOperatePos(0, i);
                    weight[0] += ((Customer) mainRoute.getNode(i)).need;
                    time[0] += mainRoute.getNode(i).duration;
                    for (int j = 0; j < sideRoute.length() - 1; j++) {
                        weight[1] += ((Customer) sideRoute.getNode(j)).need;
                        time[1] += sideRoute.getNode(j).duration;
                        context.setOperatePos(1, j);
                        HardConstraint.ConsStatus status = hardConstraintManager.fulfilled(context);
                        double costChg = softConstraintManager.fulfilled(context);
                        if (status == HardConstraint.ConsStatus.FULFILLED && costChg < 0) {
                            singleOperate(solution, context);
                            if (i >= mainRoute.length() - 1 || j >= sideRoute.length() - 1) break;
                            refreshOperateVal(context);
                        }
                    }
                    weight[1] = 0;
                    time[1] = 0;
                }
                weight[0] = 0;
                time[0] = 0;
            }
        }
    }

    private void refreshOperateVal(OperationContext context) {
        Route sideRoute = context.sideRoute;
        int cumWeight = 0, cumTime = 0;
        for (int i = 0; i <= context.operatePos[1]; i++) {
            cumWeight += ((Customer) sideRoute.getNode(i)).need;
            cumTime += sideRoute.getNode(i).duration;
        }
        int[] times = (int[]) context.operateVal.get("Time");
        int[] weights = (int[]) context.operateVal.get("Weight");
        times[1] = cumTime;
        weights[1] = cumWeight;
    }

    @Override
    public void doOperateBest(Solution solution) {

    }

    @Override
    public void doOperateRandom(Solution solution, double threshold) {

    }
}
