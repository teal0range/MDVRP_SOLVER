package Constraints.InnerSwap11;

import Common.Node.Node;
import Constraints.InnerConstraint;
import Operators.operateContext;

import java.util.ArrayList;

public class InnerSwap11ConsManager implements InnerConstraint {
    ArrayList<InnerConstraint> innerConstraints;

    public InnerSwap11ConsManager() {
        this.innerConstraints = new ArrayList<>();
    }

    public void addConstraint(InnerConstraint constraint){
        innerConstraints.add(constraint);
    }

    @Override
    public ConsStatus fulfilled(operateContext context, Node[] nodes) {
        for (InnerConstraint constraint: innerConstraints){
            ConsStatus status = constraint.fulfilled(context,nodes);
            if (status!=ConsStatus.FULFILLED){
                return status;
            }
        }
        return ConsStatus.FULFILLED;
    }
}
