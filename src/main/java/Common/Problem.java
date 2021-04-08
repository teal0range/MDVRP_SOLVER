package Common;

import Common.Node.Customer;
import Common.Node.Depot;
import Common.Node.Node;

import java.util.Arrays;

public class Problem {
    public Node[] nodes;
    public Depot[] depots;
    public Customer[] customers;
    public int type;
    public int vehicleLimit;
    public int customerNumber;
    public int depotsNumber;
    public double[][] distances;
    public String name = "";

    public Problem(int type, int vehicleLimit, int customerNumber, int depotsNumber) {
        this.type = type;
        this.vehicleLimit = vehicleLimit;
        this.customerNumber = customerNumber;
        this.depotsNumber = depotsNumber;
    }

    public static double nodeDistance(Node a, Node b) {
        return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDistance(int idx, int idy) {
        return distances[idx - 1][idy - 1];
    }

    public double getDistance(Node a, Node b) {
        return getDistance(a.id, b.id);
    }

    public void setNodes(Node[] nodes) {
        this.nodes = nodes;
        distances = new double[nodes.length][nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes.length; j++) {
                distances[i][j] = nodeDistance(nodes[i], nodes[j]);
            }
        }
        depots = new Depot[depotsNumber];
        customers = new Customer[customerNumber];
        int depotPtr = 0, cusPtr = 0;
        for (Node node : nodes) {
            if (!node.isDepot()) {
                customers[cusPtr++] = (Customer) node;
            } else {
                depots[depotPtr++] = (Depot) node;
            }
        }
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
                "name=" + name +
                ",nodes=" + Arrays.toString(nodes) +
                ",type=" + type +
                ",vehicleLimit=" + vehicleLimit +
                ",customerNumber=" + customerNumber +
                ",depotsNumber=" + depotsNumber +
                "}";
    }
}
