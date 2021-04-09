package Constraints.Swap21;

import Constraints.SoftCostConstraint;
import Operators.OperationContext;

public class SoftCostConstraintImpl extends SoftCostConstraint {
    /**
     * Swap21 操作前后的变化值
     * @param context mainRoute:主路径(2), sideRoute:侧路径(1)
     *                operatePos[0]:主路径第一个节点的位置
     *                operatePos[1]:侧路径节点的位置
     * @return Swap21 操作前后的变化值
     */
    @Override
    public double fulfilled(OperationContext context) {

        return 0;
    }
}
