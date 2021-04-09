package Constraints.TwoOptStar2;

import Common.Depot;
import Constraints.HardWeightConstraint;
import Operators.OperationContext;

public class HardWeightConstraintImpl extends HardWeightConstraint {
    /**
     * 计算载重变化
     *
     * @param context mainRoute,sideRoute,operatePos[0]和[1]
     *                为了防止变成O(n),这里多传一个参数operateVal.get("Weight"),为一个int[2] 表明到operatePos[0]和[1]的累计载重
     * @return 计算载重变化
     */
    @Override
    public ConsStatus fulfilled(OperationContext context) {
        int[] cumWeight = (int[]) context.operateVal.get("Weight");
        if (cumWeight[0] + context.sideRoute.getWeight() - cumWeight[1] > ((Depot) context.mainRoute.start).maxVehicleLoad ||
                context.mainRoute.getWeight() - cumWeight[0] + context.sideRoute.getWeight() >
                        ((Depot) context.sideRoute.start).maxVehicleLoad) return ConsStatus.NOT_FULFILLED_BREAK;
        return ConsStatus.FULFILLED;
    }
}
