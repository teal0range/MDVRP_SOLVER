package Operators.Triggers;

public class CostChangeEvent extends Event{

    public double getCostChg() {
        return costChg;
    }

    double costChg;

    public CostChangeEvent(double costChg) {
        this.costChg = costChg;
    }

}
