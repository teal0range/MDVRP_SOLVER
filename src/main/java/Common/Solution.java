package Common;

import Common.Node.Customer;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Route> routes;
    public List<Customer> unassignedCustomer;
    public Problem problem;
    private int autoIncrement;

    public Solution(List<Route> routes, Problem problem,List<Customer> unassignedCustomer) {
        this.routes = routes;
        this.unassignedCustomer = unassignedCustomer;
        this.problem = problem;
        this.autoIncrement = 0;
    }

    public Solution(List<Route> routes, Problem problem) {
        this(routes,problem, new ArrayList<>());
    }

    public Solution(Solution other){
        this.routes = new ArrayList<>(other.routes);
        this.problem = other.problem;
        this.unassignedCustomer = new ArrayList<>(other.unassignedCustomer);
        this.autoIncrement = other.getAutoIncrement();
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public int getAutoIncrement() {
        return autoIncrement;
    }

    public void addRoute(Route route){
        route.setId(autoIncrement++);
        routes.add(route);
    }
}