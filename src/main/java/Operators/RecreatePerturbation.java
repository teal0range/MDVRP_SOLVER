package Operators;

import Common.Problem;
import Common.Solution;
import Utils.RandomController;

import java.util.Collections;

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
        randomReverse(solution);
    }

    public void randomReverse(Solution solution){
        for (int i = 0; i < solution.routes.size(); i++) {
            if (RandomController.nextDouble() < 0.1){
                solution.routes.get(i).reverse();
            }
        }
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
