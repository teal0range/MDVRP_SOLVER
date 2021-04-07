package Constraints.Shift20;

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
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class SoftCostConstraintImplTest {

    public void singleOperate(Solution solution, OperationContext context) {
        if (context.mainRoute == context.sideRoute||context.sideRoute==null){
            context.mainRoute.innerShift20(context.operatePos[0],context.operatePos[1]);
        }else {
            context.mainRoute.shift20(context.sideRoute,context.operatePos[0],context.operatePos[1]);
        }
    }

    @Test
    public void fulfilled() throws IOException {
        HardConstraintManager hardConstraintManager = HardConstraintManager.getInstance("Shift20");
        SoftConstraintManager softConstraintManager = SoftConstraintManager.getInstance("Shift20");
        Problem[] problems = CourdeauInstanceReader.getReader().readData();
        Problem problem = problems[0];
        Solution solution = new GreedyGenerator(problems[0]).build();
        OperationContext context = new OperationContext.Builder(problem, OperationContext.operatorType.Shift20).
                setOperatePos(new Integer[2]).build();
        for (Route mainRoute:solution.getRoutes()) {
            context.setMainRoute(mainRoute);
            mainRoute.shuffle();
            for (Route sideRoute:solution.getRoutes()) {
                context.setSideRoute(sideRoute);
                sideRoute.shuffle();
                for (int i = 0; i < mainRoute.length() - 1; i++) {
                    context.setOperatePos(0, i);
                    for (int j = -1; j < sideRoute.length(); j++) { //插入在指定节点之后
                        if (mainRoute==sideRoute&&Math.abs(j-i)<=1)continue;
                        context.setOperatePos(1, j);
                        HardConstraint.ConsStatus status = hardConstraintManager.fulfilled(context);
                        double costChg = softConstraintManager.fulfilled(context);
                        double costBefore = solution.getDistance();
                        if (status == HardConstraint.ConsStatus.FULFILLED && costChg < 0) {
                            singleOperate(solution, context);
                            Assert.assertEquals(costBefore + costChg, solution.getDistance(), 0.001);
                            if (i >= mainRoute.length() - 1) break; // shift 结点后，路径可能变短
                        }
                    }
                }
            }
        }
    }
}