package Constraints.Swap22;

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

    public void singleOperate(Solution solution, OperationContext context) {
        context.mainRoute.swap22(context.sideRoute,context.operatePos[0],context.operatePos[1]);
    }

    @Test
    public void fulfilled() throws IOException {
        HardConstraintManager hardConstraintManager = HardConstraintManager.getInstance("Swap22");
        SoftConstraintManager softConstraintManager = SoftConstraintManager.getInstance("Swap22");
        Problem[] problems = CourdeauInstanceReader.getReader().readData();
        Problem problem = problems[0];
        Solution solution = new GreedyGenerator(problems[0]).build();
        OperationContext context = new OperationContext.Builder(problem, OperationContext.operatorType.Swap22).
                setOperatePos(new Integer[2]).build();
        for (Route mainRoute:solution.getRoutes()) {
            context.setMainRoute(mainRoute);
            mainRoute.shuffle();
            for (Route sideRoute:solution.getRoutes()) {
                context.setSideRoute(sideRoute);
                sideRoute.shuffle();
                solution.refreshDistance();
                if (mainRoute==sideRoute){
                    for (int i = 0; i < mainRoute.length()-1; i++) {
                        context.setOperatePos(0,i);
                        for (int j = i + 3; j < mainRoute.length() - 1; j++) {
                            context.setOperatePos(1,j);
                            HardConstraint.ConsStatus status = hardConstraintManager.fulfilled(context);
                            double costChg = softConstraintManager.fulfilled(context);
                            double costBefore = solution.getDistance();
                            if (status == HardConstraint.ConsStatus.FULFILLED && costChg < 0){
                                singleOperate(solution, context);
                                solution.updateDistance(costChg);
                                Assert.assertEquals(costBefore+costChg,solution.refreshDistance(),0.001);
                            }
                        }
                    }
                }else {
                    for (int i = 0; i < mainRoute.length()-1; i++) {
                        context.setOperatePos(0, i);
                        for (int j = 0; j < sideRoute.length()-1; j++) { //插入在指定节点之后
                            context.setOperatePos(1, j);
                            HardConstraint.ConsStatus status = hardConstraintManager.fulfilled(context);
                            double costChg = softConstraintManager.fulfilled(context);
                            double costBefore = solution.getDistance();
                            if (status == HardConstraint.ConsStatus.FULFILLED && costChg < 0) {
                                singleOperate(solution, context);
                                solution.updateDistance(costChg);
                                Assert.assertEquals(costBefore+costChg,solution.refreshDistance(),0.001);
                            }
                        }
                    }
                }
            }
        }
    }
}