package Operators;

import Common.Problem;
import Common.Solution;
import Operators.Triggers.Event;
import Operators.Triggers.Listener;

import java.util.ArrayList;

public abstract class BaseOperator {
    public ArrayList<Listener> listeners = new ArrayList<>();
    Problem problem;

    public BaseOperator(Problem problem) {
        this.problem = problem;
    }

    public void addListener(Listener listener) {
        this.listeners.add(listener);
    }

    public void inform(Event e){
        for (Listener listener:listeners) {
            listener.inform(e);
        }
    }

    public abstract void operate(Solution sol);
    public abstract void singleOperate(operateContext context);
}
