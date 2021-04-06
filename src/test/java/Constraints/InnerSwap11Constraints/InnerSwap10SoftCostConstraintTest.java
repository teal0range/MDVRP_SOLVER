package Constraints.InnerSwap11Constraints;

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

public class InnerSwap10SoftCostConstraintTest {
    @Test
    public void fulfilled() throws IOException {
        HardConstraintManager hardConstraintManager = HardConstraintManager.getInstance("InnerSwap11");
        SoftConstraintManager softConstraintManager = SoftConstraintManager.getInstance("InnerSwap11");
        Problem[] problems = CourdeauInstanceReader.getReader().readData();
        Problem problem = problems[0];
        Solution solution = new GreedyGenerator(problems[0]).build();
        OperationContext context = new OperationContext.Builder(problem, OperationContext.operatorType.InnerSwap10).
                setOperatePos(new Integer[2]).build();
        for (Route route:solution.getRoutes()) {
            context.setMainRoute(route);
            route.shuffle();
            for (int i = 0; i < route.length(); i++) {
                context.setOperatePos(0,i);
                for (int j = 0; j < route.length(); j++) {
                    if (j==i-1||j==i || j==i+1) continue;
                    context.setOperatePos(1,j);
                    HardConstraint.ConsStatus status = hardConstraintManager.fulfilled(context);
                    double costChg = softConstraintManager.fulfilled(context);
                    double costBefore = solution.getDistance();
                    if (status == HardConstraint.ConsStatus.FULFILLED && costChg < 0){
                        context.mainRoute.innerSwap10(context.operatePos[0], context.operatePos[1]);
                        Assert.assertEquals(costBefore+costChg, solution.getDistance(),0.001);
                    }
                }
            }
        }
    }
}