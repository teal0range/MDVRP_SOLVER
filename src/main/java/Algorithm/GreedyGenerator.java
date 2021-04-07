package Algorithm;

import Common.Problem;
import Common.Solution;
import Operators.BaseOperator;
import Operators.Insertion;
import Operators.OperationSelector;

public class GreedyGenerator extends Generator{

    BaseOperator operator;

    public GreedyGenerator(Problem problem) {
        super(problem);
        operator = new Insertion(problem);
    }

    @Override
    public Solution build() {
        Solution solution = initSolution();
        executeGreedyAlgo(solution);
        return solution;
    }


    private void executeGreedyAlgo(Solution solution){
        operator.doOperateAll(solution);
    }
}
