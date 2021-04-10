package Operators;

import Common.Problem;
import IO.ConfigReader;
import Utils.RandomController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OperatorManager {
    public static final List<String> opt2Load = ConfigReader.getInstance().readConfig().operators;
    private static final HashMap<Problem, OperatorManager> operatorManagerHashMap = new HashMap<>();
    private final List<Operator> operators;

    private OperatorManager(Problem problem) {
        operators = new ArrayList<>();
        try {
            for (String optName : opt2Load) {
                Class<?> opt = Class.forName(String.format("Operators.%s", optName));
                Operator operator = (Operator) opt.getConstructor(Problem.class).newInstance(problem);
                operators.add(operator);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private OperatorManager(Problem problem, List<String> optNames) {
        this.operators = new ArrayList<>();
        try {
            for (String optName : optNames) {
                Class<?> opt = Class.forName(String.format("Operators.%s", optName));
                Operator operator = (Operator) opt.getConstructor(Problem.class).newInstance(problem);
                operators.add(operator);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static OperatorManager getInstance(Problem problem) {
        if (!operatorManagerHashMap.containsKey(problem))
            operatorManagerHashMap.put(problem, new OperatorManager(problem));
        return operatorManagerHashMap.get(problem);
    }

    public static OperatorManager getInstance(Problem problem, List<String> optNames) {
        return new OperatorManager(problem, optNames);
    }

    @Override
    public String toString() {
        return "OperatorManager{" +
                "operators=" + operators +
                '}';
    }

    public Operator RandomOpt() {
        return operators.get(RandomController.nextInt(operators.size()));
    }

    public Operator getOpt(int index) {
        return operators.get(index);
    }

    public int size() {
        return operators.size();
    }
}
