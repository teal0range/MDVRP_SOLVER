package Operators;

import Common.Problem;
import Constraints.HardConstraintManager;
import Constraints.SoftConstraintManager;
import org.apache.log4j.Logger;

public abstract class Operator implements OperationSelector, ConstrainedOpt {
    Problem problem;
    HardConstraintManager hardConstraintManager;
    SoftConstraintManager softConstraintManager;
    Logger logger = Logger.getLogger(this.getClass());

    public Operator(Problem problem) {
        this.problem = problem;
        softConstraintManager = getSoftConstraintManager();
        hardConstraintManager = getHardConstraintManager();
    }
}
