package Common;

import java.util.Arrays;

public class Problem {
    Node []nodes;
    int type;
    int vehicleLimit;
    int customerNumber;
    int depotsNumber;

    public Problem(int type, int vehicleLimit, int customerNumber, int depotsNumber) {
        this.type = type;
        this.vehicleLimit = vehicleLimit;
        this.customerNumber = customerNumber;
        this.depotsNumber = depotsNumber;
    }

    public void setNodes(Node[] nodes) {
        this.nodes = nodes;
    }

    public int getVehicleLimit() {
        return vehicleLimit;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public int getDepotsNumber() {
        return depotsNumber;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "nodes=" + Arrays.toString(nodes) +
                ", type=" + type +
                ", vehicleLimit=" + vehicleLimit +
                ", customerNumber=" + customerNumber +
                ", depotsNumber=" + depotsNumber +
                "}\n";
    }
}
