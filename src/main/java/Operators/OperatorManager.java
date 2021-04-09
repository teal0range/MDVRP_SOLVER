package Operators;

import Common.Problem;
import Constraints.HardConstraint;
import IO.BasicConfig;
import IO.ConfigReader;
import Utils.RandomController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OperatorManager {
    public static final ArrayList<String> opt2Load = new ConfigReader().readConfig().operators;
    private static final HashMap<Problem,OperatorManager> operatorManagerHashMap = new HashMap<>();
    private final List<Operator> operators;

    public static OperatorManager getInstance(Problem problem){
        if (!operatorManagerHashMap.containsKey(problem))operatorManagerHashMap.put(problem,new OperatorManager(problem));
        return operatorManagerHashMap.get(problem);
    }

    private OperatorManager(Problem problem){
        operators = new ArrayList<>();
        try {
            for (String optName:opt2Load) {
                Class<?> opt = Class.forName(String.format("Operators.%s", optName));
                Operator operator = (Operator) opt.getConstructor(Problem.class).newInstance(problem);
                operators.add(operator);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Operator RandomOpt(){
        return operators.get(RandomController.nextInt(operators.size()));
    }
}
