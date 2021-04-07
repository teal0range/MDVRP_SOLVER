package Constraints.Swap11Constraints;

import Algorithm.GreedyGenerator;
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

public class SoftCostConstraintImplTest {
    @Test
    public void fulfilled() throws IOException {
        HardConstraintManager hardConstraintManager = HardConstraintManager.getInstance("OuterSwap11");
        SoftConstraintManager softConstraintManager = SoftConstraintManager.getInstance("OuterSwap11");
        Problem[] problems = CourdeauInstanceReader.getReader().readData();
        Problem problem = problems[0];
        Solution solution = new GreedyGenerator(problems[0]).build();
        OperationContext context = new OperationContext.Builder(problem, OperationContext.operatorType.OuterSwap10).
                setOperatePos(new Integer[2]).build();
        for(Route mainRoute:solution.getRoutes()) {
            mainRoute.shuffle();
            context.setMainRoute(mainRoute);
            for (Route sideRoute : solution.getRoutes()) {
                sideRoute.shuffle();
                context.setSideRoute(sideRoute);
                if (mainRoute==sideRoute){
                    for (int i = 0; i < mainRoute.length(); i++) {
                        context.setOperatePos(0,i);
                        for (int j = 0; j < mainRoute.length(); j++) {
                            if (j==i-1 || j==i || j==i+1)
                                continue;
                            context.setOperatePos(1,j);
                            HardConstraint.ConsStatus status = hardConstraintManager.fulfilled(context);
                            double costChg = softConstraintManager.fulfilled(context);
                            if (status == HardConstraint.ConsStatus.FULFILLED && costChg < 0){
                                context.mainRoute.outerSwap10(context.sideRoute, context.operatePos[0], context.operatePos[1]);
                            }
                        }
                    }
                }else
                    for (int i = 0; i < mainRoute.length(); ++i) {
                    context.setOperatePos(0, i);
                    for (int j = 0; j < sideRoute.length(); ++j) {
                        context.setOperatePos(1, j);
                        HardConstraint.ConsStatus status = hardConstraintManager.fulfilled(context);
                        double costChg = softConstraintManager.fulfilled(context);
                        double costBefore = solution.getDistance();
                        if (status == HardConstraint.ConsStatus.FULFILLED && costChg < 0) {
                            context.mainRoute.outerSwap10(context.sideRoute, context.operatePos[0], context.operatePos[1]);
                            Assert.assertEquals(costBefore + costChg, solution.getDistance(), 0.001);
                        }
                    }
                }
            }
        }
    }
}