package Operators;

import Common.Customer;
import Common.Depot;
import Common.Route;
import Common.Solution;
import IO.ConfigReader;

import java.util.Map;

public class ClusterRefinement3 implements ClusterRefinementCriteria{
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

        // find the most expensive customer to shift
        double maxCost = Double.MIN_VALUE;
        int customerAPos = -1;
        Route routeA = null;
        for(Route route: solution.routes) {
            if(route.start == depotA) {
                for (int i=0;i<route.length();++i) {
                    Customer customer = (Customer) route.getNode(i);
                    double curCost = alpha * solution.problem.getDistance(depotA,customer) +
                            beta * customer.need;
                    if (maxCost < curCost) {
                        maxCost = curCost;
                        customerAPos = i;
                        routeA = route;
                    }
                }
            }
        }
        assert (customerAPos!=-1);

        // fine the position of the customer to shift
        double minCost = Double.MAX_VALUE;
        Route shiftRoute = null;
        int shiftPos = -1;
        for(Depot depot:solution.problem.depots) {
            if (depot == depotA) continue;
            double costDepot = 0;
            for(Route route: solution.routes) {
                if(route.start == depot) // TODO: 是否能直接比较
                    costDepot += route.getDistance();
            }
            for (Route route : solution.routes) {
                if(route.start != depot) continue;
                for (int i = 0; i < route.length(); ++i) {
                    Customer customer = (Customer) route.getNode(i);
                    double curCost = (alpha * solution.problem.getDistance(depot, customer) +
                            beta * customer.need) / costDepot;
                    if (minCost > curCost) {
                        minCost = curCost;
                        shiftRoute = route;
                        shiftPos = i;
                    }
                }
            }
        }
        assert (shiftPos !=-1);

        routeA.shift10(shiftRoute, customerAPos, shiftPos);
    }
}
