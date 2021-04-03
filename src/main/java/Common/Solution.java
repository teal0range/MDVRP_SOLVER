package Common;

import Common.Node.Customer;
import Common.Node.Node;
import RandomController.RandomController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public List<Route> routes;
    public List<Node> unassignedCustomer;
    public Problem problem;
    private int autoIncrement;

    public Solution(List<Route> routes, Problem problem,List<Node> unassignedCustomer) {
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

    public void shuffle(){
        RandomController.shuffle(routes);
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public int getAutoIncrement() {
        return autoIncrement;
    }

    public double getDistance(){
        double distance = 0;
        for (Route route:routes){
            for (int i = 0; i < route.length(); i++) {
                distance += problem.getDistance(route.getNode(i-1),route.getNode(i));
            }
            distance += problem.getDistance(route.getNode(route.length()-1),route.getNode(route.length()));
        }
        return distance;
    }

    public void addRoute(Route route){
        route.setId(autoIncrement++);
        routes.add(route);
    }
}