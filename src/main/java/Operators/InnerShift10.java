package Operators;

import Common.Node.Node;
import Common.Problem;
import Common.Route;
import Common.Solution;
import Constraints.HardConstraint;

import java.util.ArrayList;

public class InnerShift10 extends InnerOperator{

    public InnerShift10(Problem problem) {
        super(problem);
    }

    @Override
    public void operate(Solution sol) {
        ArrayList<Route> routesList = sol.getRoutes();
        for (Route route:routesList){
            executeValidOperation(route);
        }
    }

    @Override
    public void singleOperate(operateContext context) {

    }

    public void executeValidOperation(Route route){
    }

}
