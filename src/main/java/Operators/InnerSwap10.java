package Operators;

import Common.Problem;
import Common.Route;
import Common.Solution;
import Constraints.HardConstraint;
import Constraints.HardConstraintManager;
import Constraints.SoftConstraintManager;

public class InnerSwap10 extends BaseOperator{
    public InnerSwap10(Problem problem) {super(problem);}

    @Override
    public SoftConstraintManager getSoftConstraintManager() {
        return SoftConstraintManager.getInstance(this.getClass());
    }

    @Override
    public HardConstraintManager getHardConstraintManager() {
        return HardConstraintManager.getInstance(this.getClass());
    }

    @Override
    public void singleOperate(Solution solution, OperationContext context) {
        context.mainRoute.innerSwap10(context.operatePos[0], context.operatePos[1]);
    }


    @Override
    public void doOperateAll(Solution solution) {
        OperationContext context = new OperationContext.Builder(problem, OperationContext.operatorType.InnerSwap10).
                setOperatePos(new Integer[2]).build();
        for (Route route:solution.getRoutes()) {
            context.setMainRoute(route);
            for (int i = 0; i < route.length(); i++) {
                context.setOperatePos(0,i);
                for (int j = 0; j < route.length(); j++) {
                    if (j==i-1 || j==i || j==i+1)
                        continue;
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

    @Override
    public void doOperateRandom(Solution solution, double threshold) {

    }
}
