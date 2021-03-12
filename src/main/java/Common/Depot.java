package Common;

public class Depot extends Node{
    int maxVehicleLoad;
    int maxDuration;

    public Depot(int id, double x, double y, int duration, int maxVehicleLoad, int maxDuration) {
        super(id, x, y, duration);
        this.maxVehicleLoad = maxVehicleLoad;
        this.maxDuration = maxDuration;
    }

    @Override
    public boolean isDepot() {
        return true;
    }

    @Override
    public String toString() {
        return "Depot{" +
                "maxVehicleLoad=" + maxVehicleLoad +
                ", maxDuration=" + maxDuration +
                ", id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", duration=" + duration +
                '}';
    }
}
