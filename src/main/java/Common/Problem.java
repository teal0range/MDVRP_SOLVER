package Common;

import java.util.Arrays;

public class Problem {
    Node []nodes;
    int type;
    int vehicleLimit;
    int customerNumber;
    int depotsNumber;
    double [][]distances;

    public Problem(int type, int vehicleLimit, int customerNumber, int depotsNumber) {
        this.type = type;
        this.vehicleLimit = vehicleLimit;
        this.customerNumber = customerNumber;
        this.depotsNumber = depotsNumber;
    }

    public double getDistance(int idx,int idy){
        return distances[idx][idy];
    }

    public double getDistance(Node a,Node b){
        return distances[a.id][b.id];
    }

    public void setNodes(Node[] nodes) {
        this.nodes = nodes;
        distances = new double[nodes.length][nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes.length; j++) {
                distances[i][j] = nodeDistance(nodes[i],nodes[j]);
            }
        }
    }

    public static double nodeDistance(Node a,Node b){
        return Math.sqrt((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y));
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
                "\nnodes=" + Arrays.toString(nodes) +
                ",\ntype=" + type +
                ",\nvehicleLimit=" + vehicleLimit +
                ",\ncustomerNumber=" + customerNumber +
                ",\ndepotsNumber=" + depotsNumber +
                "}\n";
    }
}
