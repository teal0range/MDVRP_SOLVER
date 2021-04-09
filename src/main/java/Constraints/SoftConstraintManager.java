package Constraints;

import IO.ConfigReader;
import Operators.OperationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SoftConstraintManager extends SoftCostConstraint {
    public static final ArrayList<String> constraints2Load = new ConfigReader().readConfig().softConstraints;
    private static final HashMap<String, SoftConstraintManager> mapper = new HashMap<>();
    protected List<SoftConstraint> constraints;

    private SoftConstraintManager(String className) {
        constraints = new ArrayList<>();
        try {
            for (String constraintName : constraints2Load) {
                Class<?> clazz = Class.forName(String.format("Constraints.%s.Soft%sConstraintImpl",
                        className, constraintName));
                SoftConstraint constraint = (SoftConstraint) clazz.getConstructor().newInstance();
                constraints.add(constraint);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SoftConstraintManager getInstance(Class<?> clazz) {
        String name = clazz.getName();
        name = name.substring(name.lastIndexOf('.') + 1);
        return getInstance(name);
    }

    public static SoftConstraintManager getInstance(String className) {
        if (!mapper.containsKey(className)) {
            mapper.put(className, new SoftConstraintManager(className));
        }
        return mapper.get(className);
    }

    @Override
    public double fulfilled(OperationContext context) {
        double cost = 0;
        for (SoftConstraint constraint : constraints) {
            cost += constraint.fulfilled(context);
        }
        return cost;
    }
}
