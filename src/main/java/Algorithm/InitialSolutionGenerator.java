package Algorithm;

import Common.Node.Customer;
import Common.Node.Depot;
import Common.Node.Node;
import Common.Problem;
import Common.Route;
import Common.Solution;
import Cost.CostCalculator;
import Operators.BaseOperator;

import java.util.ArrayList;
import java.util.List;

public class InitialSolutionGenerator {
    Problem problem;
    Solution solution;
    CostCalculator costCalculator;
    ArrayList<BaseOperator> operators;

    public InitialSolutionGenerator(Problem problem) {
        this.problem = problem;
    }

    private void prepare(){
        this.costCalculator = new CostCalculator();
        this.solution = new Solution(new SingleNodeRouteGenerator().generate(),problem,costCalculator);
    }

    public Solution getSolution() {
        prepare();
        return solution;
    }

    private class SingleNodeRouteGenerator {
        ArrayList<Route> routes = new ArrayList<>(problem.customers.length);
        Depot[] depots = problem.depots;
        Customer[] customers = problem.customers;

        public ArrayList<Route> generate() {
            for (Customer customer:customers) {
                ArrayList<Node> nodes = new ArrayList<>();
                nodes.add(customer);
                Depot depot = selectBestDepot(customer, depots);
                routes.add(new Route(nodes,depot,depot));
            }
            return routes;
        }

        private Depot selectBestDepot(Customer customer,Depot []depots){
            double minDistance = Double.MAX_VALUE;
            Depot best = null;
            for (Depot depot:depots){
                double distance = problem.getDistance(customer,depot);
                if (distance < minDistance){
                    best = depot;
                    minDistance = distance;
                }
            }
            return best;
        }
    }



}
