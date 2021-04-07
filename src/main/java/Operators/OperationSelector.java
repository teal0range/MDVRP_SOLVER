package Operators;

import Common.Solution;

public interface OperationSelector {
    void doOperateAll(Solution solution);
    void doOperateBest(Solution solution);
    void doOperateRandom(Solution solution, double threshold);
}
