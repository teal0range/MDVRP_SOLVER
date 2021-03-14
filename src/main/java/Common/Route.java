package Common;


import Common.Node.Node;

import java.util.ArrayList;

public class Route {
    public ArrayList<Node> route;
    public Node start;
    public Node end;


    public Route(ArrayList<Node> route, Node start, Node end) {
        this.route = route;
        this.start = start;
        this.end = end;
    }

    public Route(Route route){
        this.route = new ArrayList<>(route.route);
        this.start = route.start;
        this.end = route.end;
    }

    public Node getNode(int pos){
        return this.route.get(pos);
    }

    public int getLength(){
        return route.size();
    }

    public void addNode(int pos,Node node){
        this.route.add(pos, node);
    }

    public void removeNode(int pos){
        this.route.remove(pos);
    }

    public void removeNode(Node node){
        this.route.remove(node);
    }

    @Override
    public String toString() {
        return "Route{" +
                "route=" + route +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
