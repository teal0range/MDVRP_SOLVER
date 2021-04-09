package Operators;

import Common.Node.Customer;
import Common.Node.Depot;
import Common.Node.Node;
import Common.Problem;
import Common.Route;
import Common.Solution;
import Constraints.HardConstraint;
import Constraints.HardConstraintManager;
import Constraints.SoftConstraintManager;
import Utils.RandomController;

import java.util.ArrayList;
import java.util.Collections;

public class Insertion extends Operator {
    /**
     * Ruin & Recreate
     *
     * @param problem prob description
     */

    private Ruin ruin;

    public Insertion(Problem problem, Ruin ruin) {
        super(problem);
        this.ruin = ruin;
    }

    public Insertion(Problem problem) {
        super(problem);
        this.ruin = new RandomRuin();
    }

    public void setRuin(Ruin ruin) {
        this.ruin = ruin;
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
        RandomController.shuffle(solution.unassignedCustomer);
        for (Node node : solution.unassignedCustomer) {
            Customer customer = (Customer) node;
            context = new OperationContext.Builder(problem, OperationContext.operatorType.INSERT).
                    setOperatePos(new Integer[1]).setOperateNodes(new Node[1]).build();
            OperationContext bestContext = null;
            double minInsertCost = Double.MAX_VALUE;
            for (Route route : solution.routes) {
                context.setMainRoute(route).setOperateNodes(0, customer);
                for (int i = 0; i <= route.length(); i++) {
                    context.setOperatePos(0, i);
                    if (hardConstraintManager.fulfilled(context) != HardConstraint.ConsStatus.FULFILLED) continue;
                    double costChg = softConstraintManager.fulfilled(context);
                    if (minInsertCost > costChg) {
                        minInsertCost = costChg;
                        bestContext = context.copy();
                    }
                }
            }

            double minRouteCost = Double.MAX_VALUE;
            Depot bestDepot = null;
            for (Depot depot : problem.depots) {
                double costChg = costOfNewRoute(customer, depot);
                if (costChg < minRouteCost) {
                    minRouteCost = costChg;
                    bestDepot = depot;
                }
            }
            if (bestContext == null && bestDepot == null) {
                return;
            }
            if (minRouteCost < minInsertCost || bestContext == null) {
                assert bestDepot != null;
                Route route = new Route(new ArrayList<>(Collections.singletonList(customer)), bestDepot, bestDepot);
                solution.addRoute(route);
            } else {
                bestContext.mainRoute.addNode(bestContext.operatePos[0], bestContext.operateNodes[0]);
            }
        }
        solution.refreshDistance();
        solution.unassignedCustomer.clear();
    }

    @Override
    public void doOperateAll(Solution solution) {
        ruin.doOuterRuin(solution);
        singleOperate(solution, null);
    }

    @Override
    public void doOperateBest(Solution solution) {

    }

    @Override
    public void doOperateRandom(Solution solution, double threshold) {

    }

    private double costOfNewRoute(Customer customer, Depot depot) {
        return this.problem.getDistance(customer, depot) * 2;
    }
}
