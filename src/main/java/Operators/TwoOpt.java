package Operators;

import Common.Problem;
import Common.Route;
import Common.Solution;
import Constraints.HardConstraint;

public class TwoOpt extends BaseOperator{
    public TwoOpt(Problem problem) {
        super(problem);
    }

    @Override
    public void singleOperate(Solution solution, OperationContext context) {
        context.mainRoute.twoOpt(context.operatePos[0]+1,context.operatePos[1]);
    }

    @Override
    public void doOperateAll(Solution solution) {
        OperationContext context = new OperationContext.Builder(problem, OperationContext.operatorType.TwoOpt).
                setOperatePos(new Integer[2]).build();
        for (Route mainRoute:solution.getRoutes()) {
            context.setMainRoute(mainRoute);
            for (int i = 0; i < mainRoute.length() - 2; i++) {
                context.setOperatePos(0, i);
                for (int j = i + 2; j < mainRoute.length(); j++) {
                    context.setOperatePos(1, j);
                    HardConstraint.ConsStatus status = hardConstraintManager.fulfilled(context);
                    double costChg = softConstraintManager.fulfilled(context);
                    if (status == HardConstraint.ConsStatus.FULFILLED && costChg < 0) {
                        singleOperate(solution, context);
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
