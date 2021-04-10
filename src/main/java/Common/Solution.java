package Common;

import Utils.RandomController;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Route> routes;
    public List<Node> unassignedCustomer;
    public Problem problem;
    private int autoIncrement;
    private double distance;

    public Solution(List<Route> routes, Problem problem, List<Node> unassignedCustomer) {
        this.routes = routes;
        this.unassignedCustomer = unassignedCustomer;
        this.problem = problem;
        this.autoIncrement = 0;
        this.distance = 0;
        for (Route route : routes) {
            for (int i = 0; i < route.length(); i++) {
                distance += problem.getDistance(route.getNode(i - 1), route.getNode(i));
            }
            distance += problem.getDistance(route.getNode(route.length() - 1), route.getNode(route.length()));
        }
    }

    public Solution(List<Route> routes, Problem problem) {
        this(routes, problem, new ArrayList<>());
    }

    public Solution(Solution other) {
        this.routes = new ArrayList<>();
        for (Route route : other.getRoutes()) {
            this.routes.add(new Route(route));
        }
        this.problem = other.problem;
        this.unassignedCustomer = new ArrayList<>(other.unassignedCustomer);
        this.autoIncrement = other.getAutoIncrement();
        this.distance = other.getDistance();
    }

    public void shuffle() {
        RandomController.shuffle(routes);
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public int getAutoIncrement() {
        return autoIncrement;
    }

    public double getDistance() {
        return distance;
    }

    public void updateDistance(double delta) {
        this.distance += delta;
    }

    public double refreshDistance() {
        double distance = 0;
        for (Route route : routes) {
            for (int i = 0; i < route.length(); i++) {
                distance += problem.getDistance(route.getNode(i - 1), route.getNode(i));
            }
            distance += problem.getDistance(route.getNode(route.length() - 1), route.getNode(route.length()));
        }
        this.distance = distance;
        return distance;
    }

    public void destroy(int index) {
        if (this.routes.size() == 0) {
            return;
        }
        this.unassignedCustomer.addAll(this.routes.get(index).getRoute());
        this.routes.get(index).setSolution(null);
        this.routes.remove(index);
    }

    public void destroy(Route route) {
        this.unassignedCustomer.addAll(route.getRoute());
        route.setSolution(null);
        this.routes.remove(route);
    }

    public void addRoute(Route route) {
        route.setId(autoIncrement++);
        route.setSolution(this);
        routes.add(route);
    }
}