package Operators;

import Common.Node.Node;
import Common.Problem;
import Common.Route;

public class OperateContext {
    public enum operatorType{
        INSERT,SUBSTITUTE
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

        public Builder(OperateContext context){
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

        public OperateContext build(){
            return new OperateContext(problem, type,mainRoute,sideRoute,operateNodes,operatePos);
        }
    }

    private OperateContext(Problem problem, operatorType type, Route mainRoute, Route sideRoute, Node[] operateNodes, Integer[] operatePos) {
        this.type = type;
        this.mainRoute = mainRoute;
        this.sideRoute = sideRoute;
        this.operateNodes = operateNodes;
        this.operatePos = operatePos;
        this.problem = problem;
    }

    public OperateContext setMainRoute(Route mainRoute) {
        this.mainRoute = mainRoute;
        return this;
    }

    public OperateContext setOperateNodes(int index, Node node) {
        this.operateNodes[index] = node;
        return this;
    }

    public OperateContext setOperatePos(int index, int pos) {
        this.operatePos[index] = pos;
        return this;
    }

    public OperateContext copy(){
        return new Builder(this).build();
    }
}
