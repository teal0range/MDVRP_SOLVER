package Operators.Triggers;

import Cost.CostCalculator;

public class OperatorListener implements Listener{
    CostCalculator cs;

    public OperatorListener(CostCalculator cs) {
        this.cs = cs;
    }

    public void inform(Event e){
        cs.update(e);
    }
}
