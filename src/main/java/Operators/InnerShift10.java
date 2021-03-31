package Operators;

import Common.Problem;
import Common.Route;
import Common.Solution;

public class InnerShift10 extends BaseOperator{
    public InnerShift10(Problem problem) {
        super(problem);
    }

    @Override
    public void singleOperate(Solution solution, OperationContext context) {

    }

    @Override
    public void doOperateAll(Solution solution) {
        for (Route route:solution.getRoutes()) {
            for (int i = 0; i < route.length(); i++) {

            }
        }
    }

    @Override
    public void doOperateBest(Solution solution) {

    }
}
