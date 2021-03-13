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

    @Override
    public String toString() {
        return "Route{" +
                "route=" + route +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
