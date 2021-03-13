package Cost;

import Common.Problem;
import Common.Solution;
import Operators.Triggers.CostChangeEvent;
import Operators.Triggers.Event;

public class CostCalculator {
    Problem problem;
    private double cost;
    public void update(Event e){
        if (e instanceof CostChangeEvent){
            cost += ((CostChangeEvent) e).getCostChg();
        }
    }

    public double getCost(Solution solution) {
//      TODO
        return cost;
    }


    public double getCost() {
        return cost;
    }
}
