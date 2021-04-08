package Constraints.TwoOptStar1;

import Common.Node.Depot;
import Operators.OperationContext;

public class HardTimeConstraintImpl extends Constraints.HardTimeConstraint {
    /**
     * 计算时间变化
     * @param context mainRoute,sideRoute,operatePos[0]和[1]
     *                为了防止变成O(n),这里多传一个参数operateVal.get("Time"),为一个int[2] 表明到operatePos[0]和[1]的累计时间
     * @return 计算时间变化
     */
    @Override
    public ConsStatus fulfilled(OperationContext context) {
        int[] cumTime = (int [])context.operateVal.get("Time");
        if (cumTime[0] + cumTime[1] > ((Depot) context.mainRoute.start).maxDuration ||
                context.mainRoute.getTimeCost()+context.sideRoute.getTimeCost() - (cumTime[0] + cumTime[1]) >
                        ((Depot) context.mainRoute.start).maxDuration )return ConsStatus.NOT_FULFILLED_BREAK;
        return ConsStatus.FULFILLED;
    }
}
