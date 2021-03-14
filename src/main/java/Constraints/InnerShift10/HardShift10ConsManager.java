package Constraints.InnerShift10;

import Common.Node.Node;
import Constraints.HardConstraint;
import Operators.operateContext;

import java.util.ArrayList;

public class HardShift10ConsManager implements HardConstraint {
    ArrayList<HardConstraint> hardConstraints;

    public HardShift10ConsManager() {
        this.hardConstraints = new ArrayList<>();
    }

    public void addConstraint(HardConstraint constraint){
        hardConstraints.add(constraint);
    }

    @Override
    public ConsStatus fulfilled(operateContext context) {
        for (HardConstraint constraint: hardConstraints){
            ConsStatus status = constraint.fulfilled(context);
            if (status!=ConsStatus.FULFILLED){
                return status;
            }
        }
        return ConsStatus.FULFILLED;
    }
}
