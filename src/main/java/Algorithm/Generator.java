package Algorithm;

import Common.Node.Customer;
import Common.Node.Depot;
import Common.Problem;
import Common.Route;
import Common.Solution;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public abstract class Generator {

    Logger logger = Logger.getLogger(Generator.class);
    ArrayList<Route> routes;
    Depot[] depots;
    Customer[] customers;
    Problem problem;

    public Generator(Problem problem) {
        this.depots = problem.depots;
        this.customers = problem.customers;
        this.problem = problem;
        routes = new ArrayList<>();
    }

    public abstract Solution build();
}
