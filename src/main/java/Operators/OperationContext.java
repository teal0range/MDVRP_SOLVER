package Operators;

import Common.Node.Node;
import Common.Problem;
import Common.Route;

import java.util.HashMap;

public class OperationContext {
    public operatorType type;
    public Problem problem;
    public Route mainRoute;
    public Route sideRoute;
    public Node[] operateNodes;
    public Integer[] operatePos;
    public HashMap<String, Object> operateVal;
    private OperationContext(Problem problem, operatorType type, Route mainRoute, Route sideRoute, Node[] operateNodes, Integer[] operatePos, HashMap<String, Object> operateVal) {
        this.type = type;
        this.mainRoute = mainRoute;
        this.sideRoute = sideRoute;
        this.operateNodes = operateNodes;
        this.operatePos = operatePos;
        this.problem = problem;
        this.operateVal = operateVal;
    }

    public OperationContext setMainRoute(Route mainRoute) {
        this.mainRoute = mainRoute;
        return this;
    }

    public OperationContext setSideRoute(Route sideRouteRoute) {
        this.sideRoute = sideRouteRoute;
        return this;
    }

    public OperationContext setOperateVal(String key, Object val) {
        this.operateVal.put(key, val);
        return this;
    }

    public OperationContext setOperateNodes(int index, Node node) {
        this.operateNodes[index] = node;
        return this;
    }

    public OperationContext setOperatePos(int index, int pos) {
        this.operatePos[index] = pos;
        return this;
    }

    public OperationContext copy() {
        return new Builder(this).build();
    }

    public enum operatorType {
        INSERT, Shift10, Shift20, Swap11, Swap21, Swap22, TwoOpt, TwoOptStar1, TwoOptStar2
    }

    /**
     * 建造者模式，用set方法设置类参数，防止构造函数参数过长
     */
    public static class Builder {
        public Route mainRoute;
        public Route sideRoute;
        operatorType type;
        Node[] operateNodes;
        Integer[] operatePos;
        Problem problem;
        HashMap<String, Object> operateVal;

        public Builder(OperationContext context) {
            this.type = context.type;
            this.operatePos = context.operatePos.clone();
            this.operateNodes = context.operateNodes.clone();
            this.problem = context.problem;
            this.mainRoute = context.mainRoute;
            this.sideRoute = context.sideRoute;
        }

        public Builder(Problem problem, operatorType type) {
            this.type = type;
            this.problem = problem;
        }

        public Builder setMainRoute(Route mainRoute) {
            this.mainRoute = mainRoute;
            return this;
        }

        public Builder setSideRoute(Route sideRoute) {
            this.sideRoute = sideRoute;
            return this;
        }

        public Builder setOperatePos(Integer[] operatePos) {
            this.operatePos = operatePos;
            return this;
        }

        public Builder setOperateNodes(Node[] operateNodes) {
            this.operateNodes = operateNodes;
            return this;
        }

        public Builder setOperateVal(HashMap<String, Object> map) {
            this.operateVal = map;
            return this;
        }

        public OperationContext build() {
            return new OperationContext(problem, type, mainRoute, sideRoute, operateNodes, operatePos, operateVal);
        }
    }
}
