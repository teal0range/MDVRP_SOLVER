package Operators;

import Common.Problem;
import Common.Solution;
import IO.ConfigReader;

import java.util.List;

public class SequentialPerturbation implements IPerturbation, OperationSelector {
    public static final List<String> optNames = ConfigReader.getInstance().readConfig().perturbation;
    public final OperatorManager operatorManager;
    public final double threshold = 10;
    private int counter;

    public SequentialPerturbation(Problem problem) {
        operatorManager = OperatorManager.getInstance(problem, optNames);
        counter = 0;
    }

    @Override
    public void perturb(Solution solution, int times) {
        for (int i = 0; i < times; i++) {
            perturb(solution);
        }
    }

    @Override
    public void perturb(Solution solution) {
        doOperateAll(solution);
    }

    @Override
    public void doOperateAll(Solution solution) {
        doOperateRandom(solution, threshold);
    }

    @Override
    public void doOperateBest(Solution solution) {
        doOperateRandom(solution, threshold);
    }

    @Override
    public void doOperateRandom(Solution solution, double threshold) {
        operatorManager.getOpt(counter).doOperateRandom(solution, threshold);
        counter++;
        counter %= operatorManager.size();
    }
}
