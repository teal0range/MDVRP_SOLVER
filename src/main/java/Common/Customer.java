package Common;

public class Customer extends Node{
    int need;

    public Customer(int id, double x, double y, int duration, int need) {
        super(id, x, y, duration);
        this.need = need;
    }

    @Override
    public boolean isDepot() {
        return false;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "need=" + need +
                ", id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", duration=" + duration +
                '}';
    }
}
