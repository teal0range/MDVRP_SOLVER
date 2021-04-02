package Operators;

import Common.Node.Node;
import Common.Problem;
import Common.Route;

public class OperationContext {
    public enum operatorType{
        INSERT,SUBSTITUTE,InnerShift10,OuterShift10
    }
    public operatorType type;
    public Problem problem;
    public Route mainRoute;
    public Route sideRoute;
    public Node[] operateNodes;
    public Integer[] operatePos;

    /**
     * 建造者模式，用set方法设置类参数，防止构造函数参数过长
     */
    public static class Builder{
        operatorType type;
        public Route mainRoute;
        public Route sideRoute;
        Node[] operateNodes;
        Integer[] operatePos;
        Problem problem;

        public Builder(OperationContext context){
            this.type = context.type;
            this.operatePos = context.operatePos.clone();
            this.operateNodes = context.operateNodes.clone();
            this.problem = context.problem;
            this.mainRoute = context.mainRoute;
            this.sideRoute = context.sideRoute;
        }

        public Builder(Problem problem,operatorType type) {
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

        public Builder setOperatePos(Integer[] operatePos){
            this.operatePos = operatePos;
            return this;
        }

        public Builder setOperateNodes(Node[] operateNodes) {
            this.operateNodes = operateNodes;
            return this;
        }

        public OperationContext build(){
            return new OperationContext(problem, type,mainRoute,sideRoute,operateNodes,operatePos);
        }
    }

    private OperationContext(Problem problem, operatorType type, Route mainRoute, Route sideRoute, Node[] operateNodes, Integer[] operatePos) {
        this.type = type;
        this.mainRoute = mainRoute;
        this.sideRoute = sideRoute;
        this.operateNodes = operateNodes;
        this.operatePos = operatePos;
        this.problem = problem;
    }

    public OperationContext setMainRoute(Route mainRoute) {
        this.mainRoute = mainRoute;
        return this;
    }

    public OperationContext setSideRoute(Route sideRouteRoute) {
        this.sideRoute = sideRouteRoute;
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

    public OperationContext copy(){
        return new Builder(this).build();
    }
}
