package Constraints.InsertionConstraints;

import Constraints.ConstraintManager;
import Constraints.HardConstraint;
import Operators.OperateContext;

import java.util.ArrayList;
import java.util.List;

public class InsertionConstraintManager extends ConstraintManager{
    private static InsertionConstraintManager insertionConstraintManager;

    public static InsertionConstraintManager getInstance(){
        if (insertionConstraintManager==null){
            insertionConstraintManager = new InsertionConstraintManager();
        }
        return insertionConstraintManager;
    }

    private InsertionConstraintManager() {
        constraints = new ArrayList<>();
        constraints.add(new InsertionHardWeightConstraint());
    }

    @Override
    public ConsStatus fulfilled(OperateContext context) {
        for (HardConstraint constraint:constraints) {
            ConsStatus status = constraint.fulfilled(context);
            if (status!=ConsStatus.FULFILLED)return status;
        }
        return ConsStatus.FULFILLED;
    }
}
