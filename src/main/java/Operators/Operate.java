package Operators;

import Common.Solution;

public interface Operate {
    void doOperateAll(Solution solution);
    void doOperateBest(Solution solution);
    void doOperateRandom(Solution solution, double threshold);
}
