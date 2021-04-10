package Operators;

import Common.Customer;
import Common.Depot;
import Common.Route;
import Common.Solution;
import IO.ConfigReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClusterRefinement2 implements ClusterRefinementCriteria {
    @Override
    public void doClusterRefinement(Solution solution) {
        Map<String, Object> parameters = ConfigReader.getInstance().readConfig().parameters;
        int alpha = ((Double) parameters.get("alpha")).intValue();
        int beta = ((Double) parameters.get("beta")).intValue();
        double minDepotDistance = Double.MAX_VALUE;
        Depot depotA = null; // the most expensive depot measured by distance
        // find the most expensive depot
        for(Depot depot: solution.problem.depots) {
            for(Route route: solution.routes) {
                if(route.start == depot) //TODO: 这样判断是否正确
                {
                    if(minDepotDistance > route.getDistance()) {
                        minDepotDistance = route.getDistance();
                        depotA = depot;
                    }
                }
            }
        }
        assert (depotA != null);

        // compute the average cost for each customer in depotA
        double totalCost = 0;
        int numCustomers = 0;
        for(Route route: solution.routes) {
            if(route.start == depotA) {
                for (int i=0;i<route.length();++i) {
                    Customer customer = (Customer) route.getNode(i);
                    totalCost += alpha * solution.problem.getDistance(depotA,customer) +
                            beta * customer.need;
                }
                numCustomers += route.length();
            }
        }
        double averageCost = totalCost / numCustomers;

        List<Integer> customerAPos = new ArrayList<>();
        List<Route> routeA = new ArrayList<>();
        for(Route route: solution.routes) {
            if(route.start == depotA) {
                for (int i=0;i<route.length();++i) {
                    Customer customer = (Customer) route.getNode(i);
                    double curCost = alpha * solution.problem.getDistance(depotA,customer) +
                            beta * customer.need;
                    if(curCost > averageCost) {
                        customerAPos.add(i);
                        routeA.add(route);
                    }
                }
            }
        }


        for(int i=0; i<customerAPos.size(); ++i) {
            int mainPos = customerAPos.get(i);
            Route mainRoute = routeA.get(i);

            // find the position of the customer to shift
            double minCost = Double.MAX_VALUE;
            Route shiftRoute = null;
            int shiftPos = -1;
            for(Depot depot:solution.problem.depots) {
                if (depot == depotA) continue;
                for (Route route : solution.routes) {
                    for (int j = 0; j < route.length(); ++j) {
                        Customer customer = (Customer) route.getNode(j);
                        double curCost = alpha * solution.problem.getDistance(depot, customer) +
                                beta * customer.need;
                        if (minCost > curCost) {
                            minCost = curCost;
                            shiftRoute = route;
                            shiftPos = j;
                        }
                    }
                }
            }
            assert (shiftPos!=-1);

            mainRoute.shift10(shiftRoute,mainPos,shiftPos);
        }
    }
}
