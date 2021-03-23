package Common;


import Common.Node.Node;

import java.util.ArrayList;
import java.util.List;

public class Route {
    public List<Node> route;
    public Node start;
    public Node end;
    private int weight;

    public int getWeight() {
        return weight;
    }

    public Route(List<Node> route, Node start, Node end) {
        this.route = route;
        this.start = start;
        this.end = end;
    }

    public Route(Route route){
        this.route = new ArrayList<>(route.route);
        this.start = route.start;
        this.end = route.end;
        this.weight = route.getWeight();
    }

    public Node getNode(int pos){
        if (pos < 0) {
            return this.start;
        }else if (pos < this.route.size()) {
            return this.route.get(pos);
        }else return this.end;
    }

    public int length(){
        return route.size();
    }

    public void subNode(int pos,Node node){
        this.route.set(pos, node);
    }

    public void conNode(int st, int ed){
        List<Node> subList = this.route.subList(0, st+1);
        subList.addAll(this.route.subList(ed,this.route.size()));
        this.route = subList;
    }

    public void addNode(int pos,Node node){
        this.route.add(pos, node);
    }

    public void rmNode(int pos){
        this.route.remove(pos);
    }

    public void rmNode(Node node){
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
