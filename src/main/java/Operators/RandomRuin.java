package Operators;

import Common.Solution;

public class RandomRuin implements Ruin {
    @Override
    public void doOuterRuin(Solution solution) {
        solution.shuffle();
        solution.destroy(solution.routes.size() - 1);
    }

    @Override
    public void doInnerRuin(Solution solution, OperationContext context) {
    }
}
