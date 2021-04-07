package Operators;

import Common.Problem;
import Common.Route;
import Common.Solution;
import Constraints.HardConstraint;
import Constraints.HardConstraintManager;
import Constraints.SoftConstraintManager;

public class Shift10 extends BaseOperator{
    public Shift10(Problem problem) {
        super(problem);
    }

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
        context.mainRoute.outerShift10(context.sideRoute, context.operatePos[0], context.operatePos[1]);
    }

    @Override
    public void doOperateAll(Solution solution) {
        OperationContext context = new OperationContext.Builder(problem, OperationContext.operatorType.OuterShift10).
                setOperatePos(new Integer[2]).build();
        for (Route mainRoute:solution.getRoutes()) {
            context.setMainRoute(mainRoute);
            for (Route sideRoute:solution.getRoutes()) {
                context.setSideRoute(sideRoute);
                if (mainRoute==sideRoute){
                    for (int i = 0; i < mainRoute.length(); i++) {
                        context.setOperatePos(0,i);
                        for (int j = -1; j < mainRoute.length()-1; j++) {
                            if (j==i-1||j==i)continue;
                            context.setOperatePos(1,j);
                            HardConstraint.ConsStatus status = hardConstraintManager.fulfilled(context);
                            double costChg = softConstraintManager.fulfilled(context);
                            if (status == HardConstraint.ConsStatus.FULFILLED && costChg < 0){
                                singleOperate(solution, context);
                            }
                        }
                    }

                }else {
                    for (int i = 0; i < mainRoute.length(); i++) {
                        context.setOperatePos(0, i);
                        for (int j = -1; j < sideRoute.length() - 1; j++) { //插入在指定节点之后
                            context.setOperatePos(1, j);
                            HardConstraint.ConsStatus status = hardConstraintManager.fulfilled(context);
                            double costChg = softConstraintManager.fulfilled(context);
                            if (status == HardConstraint.ConsStatus.FULFILLED && costChg < 0) {
                                singleOperate(solution, context);
                                if (i >= mainRoute.length()) break; // shift 结点后，路径可能变短
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void doOperateBest(Solution solution) {
        OperationContext context = new OperationContext.Builder(problem, OperationContext.operatorType.OuterShift10).
                setOperatePos(new Integer[2]).build();
        OperationContext bestContext = null;
        double bestCostChg = Double.MAX_VALUE;
        for (Route mainRoute:solution.getRoutes()) {
            context.setMainRoute(mainRoute);
            for (Route sideRoute:solution.getRoutes()) {
                if (mainRoute==sideRoute)continue;
                context.setSideRoute(sideRoute);
                for (int i = 0; i < mainRoute.length(); i++) {
                    context.setOperatePos(0, i);
                    for (int j = -1; j < sideRoute.length(); j++) { //插入在指定节点之后
                        context.setOperatePos(1, j);
                        HardConstraint.ConsStatus status = hardConstraintManager.fulfilled(context);
                        double costChg = softConstraintManager.fulfilled(context);
                        if (status == HardConstraint.ConsStatus.FULFILLED && costChg < bestCostChg) {
                            bestCostChg = costChg;
                            bestContext = new OperationContext.Builder(context).build();
                        }
                    }
                }
            }
        }
        if (bestContext!=null){
            singleOperate(solution,bestContext);
        }
    }

    @Override
    public void doOperateRandom(Solution solution, double threshold) {

    }
}