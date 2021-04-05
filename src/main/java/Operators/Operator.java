package Operators;

import Common.Solution;

public interface Operator {
    void doOperateAll(Solution solution);
    void doOperateBest(Solution solution);
    void doOperateRandom(Solution solution, double threshold);
}
