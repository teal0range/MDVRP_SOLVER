package Constraints;

import IO.ConstraintsConfigReader;
import Operators.OperationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConstraintManager implements HardConstraint{

    public static final ArrayList<String> constraints2Load = new ConstraintsConfigReader().readConfig().hardConstraints;
    protected List<HardConstraint> constraints;
    private static final HashMap<String,ConstraintManager> mapper = new HashMap<>();

    public static ConstraintManager getInstance(Class<?> clazz){
        return getInstance(clazz.getName());
    }

    public static ConstraintManager getInstance(String className){
        if (!mapper.containsKey(className)) {
            mapper.put(className, new ConstraintManager(className));
        }
        return mapper.get(className);
    }

    private ConstraintManager(String className){
        constraints = new ArrayList<>();
        try {
            for(String contraintName:constraints2Load) {
                Class<?> clazz = Class.forName(String.format("Constraints.%sConstraints.%sHard%sConstraint",
                        className, className, contraintName));
                HardConstraint constraint = (HardConstraint) clazz.getConstructor().newInstance();
                constraints.add(constraint);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ConsStatus fulfilled(OperationContext context) {
        for (HardConstraint constraint:constraints) {
            ConsStatus status = constraint.fulfilled(context);
            if (status!=ConsStatus.FULFILLED)return status;
        }
        return ConsStatus.FULFILLED;
    }
}
