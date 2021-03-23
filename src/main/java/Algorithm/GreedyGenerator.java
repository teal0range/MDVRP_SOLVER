package Algorithm;

import Common.Node.Customer;
import Common.Node.Depot;
import Common.Node.Node;
import Common.Problem;
import Common.Route;
import Common.Solution;
import Constraints.InsertionConstraints.InsertionSoftCostConstraint;
import Constraints.SoftConstraint;
import Operators.OperateContext;
import org.apache.log4j.Level;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GreedyGenerator extends Generator{

    SoftConstraint costConstraint = new InsertionSoftCostConstraint();

    public GreedyGenerator(Problem problem) {
        super(problem);
    }

    @Override
    public Solution build() {
        Solution solution = initSolution();
        executeGreedyAlgo(solution);
        return solution;
    }

    private Solution initSolution(){
        List<Route> routes = new ArrayList<>();
        List<Customer> unassigned = new ArrayList<>(Arrays.asList(problem.customers));
        Solution solution = new Solution(routes, problem, unassigned);
        executeGreedyAlgo(solution);
        return solution;
    }


    private void executeGreedyAlgo(Solution solution){
        for (Customer customer:solution.unassignedCustomer){
            OperateContext context = new OperateContext.Builder(problem, OperateContext.operatorType.INSERT).
            setOperatePos(new Integer[1]).setOperateNodes(new Node[1]).build();
            OperateContext bestContext = null;
            double minInsertCost = Double.MAX_VALUE;
            for(Route route:solution.routes){
                context.setMainRoute(route).setOperateNodes(0,customer);
                for (int i = 0; i <= route.length(); i++) {
                    context.setOperatePos(0,i);
                    double costChg = costOfInsertion(context);
                    if (minInsertCost > costChg){
                        minInsertCost = costChg;
                        bestContext = context.copy();
                    }
                }
            }

            double minRouteCost = Double.MAX_VALUE;
            Depot bestDepot = null;
            for(Depot depot:problem.depots){
                double costChg = costOfNewRoute(customer,depot);
                if (costChg < minRouteCost){
                    minRouteCost = costChg;
                    bestDepot = depot;
                }
            }
            if (bestContext==null&&bestDepot==null){
                logger.error("There's no valid operations, process exists");
            }
            if (minRouteCost < minInsertCost){
                assert bestDepot!=null;
                Route route = new Route(new ArrayList<>(Collections.singletonList(customer)),bestDepot,bestDepot);
                        logger.debug(String.format("new route %d added:%d->%d->%d",
                                route.hashCode(), bestDepot.id, customer.id, bestDepot.id));
                solution.addRoute(route);
            }else {
                assert bestContext!=null;
                logger.debug(String.format("customer: %d insert to pos %d of route %d",
                        bestContext.operateNodes[0].id,bestContext.operatePos[0],bestContext.mainRoute.hashCode()));
                bestContext.mainRoute.addNode(bestContext.operatePos[0],bestContext.operateNodes[0]);
            }
        }
    }

    private double costOfInsertion(OperateContext context){
        return costConstraint.fulfilled(context);
    }

    private double costOfNewRoute(Customer customer,Depot depot){
        return this.problem.getDistance(customer,depot) * 2;
    }

    private void addRoute(Depot depot){
        this.routes.add(new Route(new ArrayList<>(),depot,depot));
    }
}
