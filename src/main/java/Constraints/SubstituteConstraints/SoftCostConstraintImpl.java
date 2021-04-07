package Constraints.SubstituteConstraints;

import Constraints.SoftCostConstraint;
import Operators.OperationContext;

public class SoftCostConstraintImpl extends SoftCostConstraint {
    @Override
    public double fulfilled(OperationContext context) {
        return 0;
    }
}
