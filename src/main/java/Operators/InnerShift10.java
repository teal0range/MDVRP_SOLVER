package Operators;

import Common.Node.Node;
import Common.Problem;
import Common.Route;
import Common.Solution;
import Constraints.HardConstraint;
import Constraints.InnerShift10.HardShift10ConsManager;

import java.util.ArrayList;

public class InnerShift10 extends InnerOperator{

    HardShift10ConsManager consManager = new HardShift10ConsManager();

    public InnerShift10(Problem problem) {
        super(problem);
    }

    @Override
    public void operate(Solution sol) {
        ArrayList<Route> routesList = sol.getSol();
        for (Route route:routesList){
            executeValidOperation(route);
        }
    }

    @Override
    public void singleOperate(operateContext context) {

    }

    public void executeValidOperation(Route route){
        if (route.getLength()==1)return;
        operateContext context = new operateContext.Builder(operateContext.operatorType.SHIFT10,route,new Node[2]).build();
        for (int i = 0; i < route.getLength(); i++) {
            for (int j = i + 1; j < route.getLength(); j++) {
                context.setOperateNodes(0,route.getNode(i)).setOperateNodes(1,route.getNode(j));
                if (consManager.fulfilled(context)== HardConstraint.ConsStatus.FULFILLED)singleOperate(context);
            }
        }
    }

}
