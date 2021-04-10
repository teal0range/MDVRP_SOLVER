package Operators;

import Common.Solution;

public interface IPerturbation {
    void perturb(Solution solution, int times);

    void perturb(Solution solution);

    void reset();
}
