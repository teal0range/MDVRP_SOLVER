package Constraints.SubstituteConstraints;

import Constraints.SoftCostConstraint;
import Operators.OperationContext;

public class SubstituteSoftCostConstraint extends SoftCostConstraint {
    @Override
    public double fulfilled(OperationContext context) {
        return 0;
    }
}
