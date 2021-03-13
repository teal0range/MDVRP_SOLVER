package Common;

import Cost.CostCalculator;

import java.util.ArrayList;

public class Solution {
    ArrayList<Route> sol;
    Problem problem;
    CostCalculator costCalculator;

    public Solution(ArrayList<Route> sol, Problem problem, CostCalculator costCalculator) {
        this.sol = sol;
        this.problem = problem;
        this.costCalculator = costCalculator;
    }

    public double getCost(){
        return costCalculator.getCost();
    }

}
