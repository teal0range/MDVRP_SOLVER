package Operators;

import Common.Problem;
import Common.Route;
import Common.Solution;
import Constraints.HardConstraintManager;
import Constraints.HardConstraint;
import Constraints.SoftConstraintManager;

public class InnerShift10 extends BaseOperator{
    public InnerShift10(Problem problem) {
        super(problem);
    }

    @Override
    public SoftConstraintManager getSoftConstraintManager() {
        return SoftConstraintManager.getInstance(InnerShift10.class);
    }

    @Override
    public HardConstraintManager getHardConstraintManager() {
        return HardConstraintManager.getInstance(InnerShift10.class);
    }

    @Override
    public void singleOperate(Solution solution, OperationContext context) {
        context.mainRoute.innerShift10(context.operatePos[0],context.operatePos[1]);
    }

    @Override
    public void doOperateAll(Solution solution) {
        OperationContext context = new OperationContext.Builder(problem, OperationContext.operatorType.InnerShift10).
                setOperatePos(new Integer[2]).build();
        for (Route route:solution.getRoutes()) {
            context.setMainRoute(route);
            for (int i = 0; i < route.length(); i++) {
                context.setOperatePos(0,i);
                for (int j = 0; j < route.length(); j++) {
                    if (j==i-1||j==i)continue;
                    context.setOperatePos(1,j);
                    HardConstraint.ConsStatus status = hardConstraintManager.fulfilled(context);
                    double costChg = softConstraintManager.fulfilled(context);
                    if (status == HardConstraint.ConsStatus.FULFILLED && costChg < 0){
                        singleOperate(solution, context);
                    }
                }
            }
        }
    }

    @Override
    public void doOperateBest(Solution solution) {

    }
}
