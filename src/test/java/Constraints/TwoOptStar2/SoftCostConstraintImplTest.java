package Constraints.TwoOptStar2;

import Algorithm.GreedyGenerator;
import Common.Node.Customer;
import Common.Node.Node;
import Common.Problem;
import Common.Route;
import Common.Solution;
import Constraints.HardConstraint;
import Constraints.HardConstraintManager;
import Constraints.SoftConstraintManager;
import IO.CourdeauInstanceReader;
import Operators.OperationContext;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SoftCostConstraintImplTest {

    public void singleOperate(Solution solution, OperationContext context) {
        context.mainRoute.twoOptStar2(context.sideRoute, context.operatePos[0], context.operatePos[1]);
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

    @Test
    public void fulfilled() throws IOException {
        HardConstraintManager hardConstraintManager = HardConstraintManager.getInstance("TwoOptStar2");
        SoftConstraintManager softConstraintManager = SoftConstraintManager.getInstance("TwoOptStar2");
        Problem[] problems = CourdeauInstanceReader.getReader().readData();
        Problem problem = problems[0];
        Solution solution = new GreedyGenerator(problems[0]).build();
        OperationContext context = new OperationContext.Builder(problem, OperationContext.operatorType.TwoOptStar2).
                setOperatePos(new Integer[2]).setOperateVal(new HashMap<>()).build();
        int[] time = new int[]{0, 0};
        int[] weight = new int[]{0, 0};
        context.setOperateVal("Time", time);
        context.setOperateVal("Weight", weight);
        for (Route mainRoute : solution.getRoutes()) {
            context.setMainRoute(mainRoute);
            mainRoute.shuffle();
            for (Route sideRoute : solution.getRoutes()) {
                if (mainRoute == sideRoute || mainRoute.start != sideRoute.start) continue;
                sideRoute.shuffle();
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
                        double costBefore = solution.getDistance();
                        if (status == HardConstraint.ConsStatus.FULFILLED && costChg < 0) {
                            singleOperate(solution, context);
                            Assert.assertEquals(costBefore+costChg,solution.refreshDistance(),0.001);
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
}