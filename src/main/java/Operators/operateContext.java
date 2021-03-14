package Operators;

import Common.Node.Node;
import Common.Route;

public class operateContext {
    public enum operatorType{
        SHIFT10
    }
    operatorType type;
    public Route mainRoute;
    public Route sideRoute;
    Node[] operateNodes;

    static class Builder{
        operatorType type;
        public Route mainRoute;
        public Route sideRoute;
        Node[] operateNodes;

        public Builder(operatorType type, Route mainRoute, Node[] operateNodes) {
            this.type = type;
            this.mainRoute = mainRoute;
            this.operateNodes = operateNodes;
        }

        public Builder setSideRoute(Route sideRoute) {
            this.sideRoute = sideRoute;
            return this;
        }

        public operateContext build(){
            return new operateContext(type,mainRoute,sideRoute,operateNodes);
        }
    }

    private operateContext(operatorType type, Route mainRoute, Route sideRoute, Node[] operateNodes) {
        this.type = type;
        this.mainRoute = mainRoute;
        this.sideRoute = sideRoute;
        this.operateNodes = operateNodes;
    }

    public operateContext setMainRoute(Route mainRoute) {
        this.mainRoute = mainRoute;
        return this;
    }

    public operateContext setSideRoute(Route sideRoute) {
        this.sideRoute = sideRoute;
        return this;
    }

    public operateContext setOperateNodes(Node[] operateNodes) {
        this.operateNodes = operateNodes;
        return this;
    }

    public operateContext setOperateNodes(int index,Node node) {
        this.operateNodes[index] = node;
        return this;
    }
}
