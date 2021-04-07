package Constraints;

import IO.ConstraintsConfigReader;
import Operators.OperationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HardConstraintManager implements HardConstraint{

    public static final ArrayList<String> constraints2Load = new ConstraintsConfigReader().readConfig().hardConstraints;
    protected List<HardConstraint> constraints;
    private static final HashMap<String, HardConstraintManager> mapper = new HashMap<>();

    public static HardConstraintManager getInstance(Class<?> clazz){
        String name = clazz.getName();
        name = name.substring(name.lastIndexOf('.')+1);
        return getInstance(name);
    }

    public static HardConstraintManager getInstance(String className){
        if (!mapper.containsKey(className)) {
            mapper.put(className, new HardConstraintManager(className));
        }
        return mapper.get(className);
    }

    private HardConstraintManager(String className){
        constraints = new ArrayList<>();
        try {
            for(String constraintName:constraints2Load) {
                Class<?> clazz = Class.forName(String.format("Constraints.%s.Hard%sConstraintImpl",
                        className, constraintName));
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
