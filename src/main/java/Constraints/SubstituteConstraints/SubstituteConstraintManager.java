package Constraints.SubstituteConstraints;


import Constraints.ConstraintManager;
import Constraints.HardConstraint;
import Operators.OperationContext;

import java.util.ArrayList;

public class SubstituteConstraintManager extends ConstraintManager {
    private static SubstituteConstraintManager substituteConstraintManager;

    public  static SubstituteConstraintManager getInstance() {
        if (substituteConstraintManager==null) {
            substituteConstraintManager = new SubstituteConstraintManager();
        }
        return substituteConstraintManager;
    }
    private SubstituteConstraintManager() {
        constraints = new ArrayList<>();
        constraints.add(new SubstituteHardWeightConstraint());
        constraints.add(new SubstituteHardTimeConstraint());
    }

    @Override
    public ConsStatus fulfilled(OperationContext context) {
        for (HardConstraint constraint:constraints) {
            ConsStatus status = constraint.fulfilled(context);
            if(status!=ConsStatus.FULFILLED) return status;
        }
        return ConsStatus.FULFILLED;
    }
}
