package Algorithm;

import Common.Node.Customer;
import Common.Node.Depot;
import Common.Node.Node;
import Common.Problem;
import Common.Route;
import Common.Solution;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    protected Solution initSolution() {
        List<Route> routes = new ArrayList<>();
        List<Node> unassigned = new ArrayList<>(Arrays.asList(problem.customers));
        return new Solution(routes, problem, unassigned);
    }

    public abstract Solution build();
}
