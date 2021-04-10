package Operators;

import Common.Problem;
import Common.Solution;

public class RecreatePerturbation implements OperationSelector, IPerturbation {
    Insertion operationSelector;


    public RecreatePerturbation(Problem problem) {
        this.operationSelector = new Insertion(problem);
    }

    @Override
    public void perturb(Solution solution, int times) {
        for (int i = 0; i < times; i++) {
            perturb(solution);
        }
    }

    @Override
    public void perturb(Solution solution) {
//        operationSelector.doOperateAll(solution);
        operationSelector.doOperateBest(solution);
    }

    @Override
    public void reset() {

    }

    @Override
    public void doOperateAll(Solution solution) {
        operationSelector.doOperateAll(solution);
    }

    @Override
    public void doOperateBest(Solution solution) {
        operationSelector.doOperateBest(solution);
    }

    @Override
    public void doOperateRandom(Solution solution, double threshold) {
        operationSelector.doOperateRandom(solution, threshold);
    }
}
