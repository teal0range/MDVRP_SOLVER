package Constraints.Shift10;

import Algorithm.GreedyGenerator;
import Common.Problem;
import Common.Route;
import Common.Solution;
import Constraints.HardConstraint;
import Constraints.HardConstraintManager;
import Constraints.SoftConstraintManager;
import IO.CourdeauInstanceReader;
import Operators.OperationContext;
import Utils.RandomController;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class SoftCostConstraintImplTest {
    @Test
    public void fulfilled() throws IOException {
        RandomController.setSeed(1);
        HardConstraintManager hardConstraintManager = HardConstraintManager.getInstance("Shift10");
        SoftConstraintManager softConstraintManager = SoftConstraintManager.getInstance("Shift10");
        Problem[] problems = CourdeauInstanceReader.getReader().readData();
        Problem problem = problems[0];
        Solution solution = new GreedyGenerator(problems[0]).build();
        OperationContext context = new OperationContext.Builder(problem, OperationContext.operatorType.Shift10).
                setOperatePos(new Integer[2]).build();
        for(Route mainRoute:solution.getRoutes()) {
            mainRoute.shuffle();
            context.setMainRoute(mainRoute);
            for (Route sideRoute : solution.getRoutes()) {
                sideRoute.shuffle();
                context.setSideRoute(sideRoute);
                if (sideRoute == mainRoute) {
                    for (int i = 0; i < mainRoute.length(); i++) {
                        context.setOperatePos(0,i);
                        for (int j = -1; j < mainRoute.length()-1; j++) {
                            if (j==i-1||j==i)continue;
                            context.setOperatePos(1,j);
                            if (i==0&&j==1&&mainRoute.getId()==2&&sideRoute.getId()==2){
                                System.out.println();
                            }
                            HardConstraint.ConsStatus status = hardConstraintManager.fulfilled(context);
                            double costChg = softConstraintManager.fulfilled(context);
                            double costBefore = solution.getDistance();
                            if (status == HardConstraint.ConsStatus.FULFILLED && costChg < 0){
                                context.mainRoute.innerShift10(context.operatePos[0],context.operatePos[1]);
                                Assert.assertEquals(costBefore + costChg, solution.getDistance(), 0.001);
                            }
                        }
                    }
                }else {
                    for (int i = 0; i < mainRoute.length(); ++i) {
                        context.setOperatePos(0, i);
                        for (int j = -1; j < sideRoute.length()-1; ++j) {
                            context.setOperatePos(1, j);
                            HardConstraint.ConsStatus status = hardConstraintManager.fulfilled(context);
                            double costChg = softConstraintManager.fulfilled(context);
                            double costBefore = solution.getDistance();
                            if (status == HardConstraint.ConsStatus.FULFILLED && costChg < 0) {
                                context.mainRoute.shift10(context.sideRoute, context.operatePos[0], context.operatePos[1]);
                                Assert.assertEquals(costBefore + costChg, solution.getDistance(), 0.001);
                                if (i >= mainRoute.length()) break;
                            }
                        }
                    }
                }
            }
        }
    }
}