package Operators;

import Common.Problem;
import Common.Solution;
import IO.ConfigReader;
import Utils.RandomController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperatorManager {
    public static final List<String> opt2Load = ConfigReader.getInstance().readConfig().operators;
    private final List<Operator> operators;
    private final List<Integer> successRecorder;
    private int counter;
    public static final double sigma = (double) ConfigReader.getInstance().readConfig().parameters.get("sigma");
    private final Map<Operator, Integer> invertedIndex;

    private OperatorManager(Problem problem) {
        this(problem, OperatorManager.opt2Load);
    }

    private OperatorManager(Problem problem, List<String> optNames) {
        this.operators = new ArrayList<>();
        successRecorder = new ArrayList<>();
        invertedIndex = new HashMap<>();
        try {
            for (int i = 0; i < optNames.size(); i++) {
                String optName = optNames.get(i);
                Class<?> opt = Class.forName(String.format("Operators.%s", optName));
                Operator operator = (Operator) opt.getConstructor(Problem.class).newInstance(problem);
                operators.add(operator);
                successRecorder.add(0);
                invertedIndex.put(operator, i);
            }
            counter = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static OperatorManager getInstance(Problem problem) {
        return new OperatorManager(problem);
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


    public void randomNeighborhood(Solution solution) {
        Operator operator = this.randomOpt();
        double costBefore = solution.getDistance();
        operator.doOperateAll(solution);
        if (costBefore > solution.getDistance()) {
            this.incrementRecorder(invertedIndex.get(operator));
        }
    }

    public void sigmaLearn(Solution solution){
        Operator operator = this.sigmaGreedy();
        double costBefore = solution.getDistance();
        operator.doOperateAll(solution);
        if (costBefore > solution.getDistance()) {
            this.incrementRecorder(invertedIndex.get(operator));
        }
    }


    public void adaptiveLocalSearch(Solution solution) {
        Operator operator = rouletteWheel();
        operator.doOperateAll(solution);
    }

    public void incrementRecorder(int index) {
        successRecorder.set(index, successRecorder.get(index) + 1);
        counter++;
    }

    public void resetRecorder(int index) {
        counter -= successRecorder.get(index);
        successRecorder.set(index, 0);
    }

    public void resetRecorder() {
        for (int i = 0; i < successRecorder.size(); i++) {
            resetRecorder(i);
        }
    }

    public Operator rouletteWheel() {
        int[] cumSum = new int[operators.size() + 1];
        for (int i = 1; i < operators.size() + 1; i++) {
            cumSum[i] += cumSum[i - 1] + successRecorder.get(i - 1);
        }
        double v = RandomController.nextDouble() * counter;
        int ptr = 0;
        for (int i = 0; i < operators.size(); i++) {
            if (v > cumSum[i] && v <= cumSum[i + 1]) {
                ptr = i;
                break;
            }
        }
        return operators.get(ptr);
    }


    public Operator sigmaGreedy() {
        if (RandomController.nextDouble() < sigma) {
            return randomOpt();
        } else {
            return rouletteWheel();
        }
    }

    public Operator randomOpt() {
        return operators.get(RandomController.nextInt(operators.size()));
    }

    public Operator getOpt(int index) {
        return operators.get(index);
    }

    public int size() {
        return operators.size();
    }
}
