package Common;

import java.util.Objects;

public abstract class Node {
    int id;
    double x;
    double y;
    int duration;

    public Node(int id, double x, double y, int duration) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.duration = duration;
    }

    public abstract boolean isDepot();
}
