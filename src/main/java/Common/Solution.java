package Common;

import Cost.CostCalculator;

import java.util.ArrayList;

public class Solution {
    ArrayList<Route> routes;
    Problem problem;
    CostCalculator costCalculator;

    public Solution(ArrayList<Route> routes, Problem problem, CostCalculator costCalculator) {
        this.routes = routes;
        this.problem = problem;
        this.costCalculator = costCalculator;
    }

    public double getCost(){
        return costCalculator.getCost();
    }

    public ArrayList<Route> getRoutes() {
        return routes;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "sol=" + routes +
                ", problem=" + problem +
                ", costCalculator=" + costCalculator +
                '}';
    }
}