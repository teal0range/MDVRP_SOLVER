package Algorithm;

import Common.Node.Customer;
import Common.Node.Depot;
import Common.Node.Node;
import Common.Problem;
import Common.Route;
import Common.Solution;
import Constraints.HardConstraint;
import Constraints.HardConstraintManager;
import Constraints.Insertion.SoftCostConstraintImpl;
import Constraints.SoftConstraint;
import Operators.Insertion;
import Operators.OperationContext;
import Operators.Operator;

import java.util.ArrayList;
import java.util.Collections;

public class GreedyGenerator extends Generator{

    Operator operator;

    public GreedyGenerator(Problem problem) {
        super(problem);
        operator = new Insertion(problem);
    }

    @Override
    public Solution build() {
        Solution solution = initSolution();
        executeGreedyAlgo(solution);
        return solution;
    }


    private void executeGreedyAlgo(Solution solution){
        operator.doOperateAll(solution);
    }

    private void addRoute(Depot depot){
        this.routes.add(new Route(new ArrayList<>(),depot,depot));
    }
}
