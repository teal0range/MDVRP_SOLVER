package Constraints.TwoOpt;

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

import static org.junit.Assert.*;

public class SoftCostConstraintImplTest {

    public void singleOperate(Solution solution, OperationContext context) {
        context.mainRoute.twoOpt(context.operatePos[0]+1,context.operatePos[1]);
    }

    @Test
    public void fulfilled() throws IOException {
        HardConstraintManager hardConstraintManager = HardConstraintManager.getInstance("TwoOpt");
        SoftConstraintManager softConstraintManager = SoftConstraintManager.getInstance("TwoOpt");
        Problem[] problems = CourdeauInstanceReader.getReader().readData();
        Problem problem = problems[0];
        Solution solution = new GreedyGenerator(problems[0]).build();
        OperationContext context = new OperationContext.Builder(problem, OperationContext.operatorType.TwoOpt).
                setOperatePos(new Integer[2]).build();
        for (Route mainRoute:solution.getRoutes()) {
            context.setMainRoute(mainRoute);
            mainRoute.shuffle();
            for (int i = 0; i < mainRoute.length() - 2; i++) {
                context.setOperatePos(0, i);
                for (int j = i + 2; j < mainRoute.length(); j++) {
                    context.setOperatePos(1, j);
                    HardConstraint.ConsStatus status = hardConstraintManager.fulfilled(context);
                    double costChg = softConstraintManager.fulfilled(context);
                    double costBefore = solution.getDistance();
                    if (status == HardConstraint.ConsStatus.FULFILLED && costChg < 0) {
                        singleOperate(solution, context);
                        Assert.assertEquals(costBefore+costChg,solution.getDistance(),0.001);
                    }
                }
            }
        }
    }
}