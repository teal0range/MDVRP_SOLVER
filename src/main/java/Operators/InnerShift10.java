package Operators;

import Common.Problem;
import Common.Route;
import Common.Solution;

import java.util.ArrayList;
import java.util.List;

public class InnerShift10 extends InnerOperator{

    public InnerShift10(Problem problem) {
        super(problem);
    }

    @Override
    public void operate(Solution sol) {
        List<Route> routesList = sol.getRoutes();
        for (Route route:routesList){
            executeValidOperation(route);
        }
    }

    @Override
    public void singleOperate(OperateContext context) {

    }

    public void executeValidOperation(Route route){
    }

}
