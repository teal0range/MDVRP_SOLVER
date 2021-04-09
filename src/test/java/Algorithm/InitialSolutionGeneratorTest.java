package Algorithm;

import Common.Customer;
import Common.Depot;
import Common.Node;
import Common.Problem;
import Common.Route;
import Common.Solution;
import IO.CourdeauInstanceReader;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;

import static org.apache.log4j.LogManager.getLogger;

public class InitialSolutionGeneratorTest {

    Logger logger = getLogger(InitialSolutionGeneratorTest.class);

    public boolean validChecker(Solution solution){
        Problem problem = solution.problem;
        boolean flag = true;
//        Check whether all nodes exist
        Customer[]customers = problem.customers;
        HashSet<Customer> customerSet = new HashSet<>();
        for (Route route:solution.getRoutes()) {
            for (Node customer:route.getRoute()){
                customerSet.add((Customer) customer);
            }
        }
        for (Customer value : customers) {
            if (!customerSet.contains(value)) {
                logger.error("NodeSet is not complete");
                logger.error(String.format("ProblemSetSize: %d,SolutionSetSize: %d", customers.length, customerSet.size()));
                flag = false;
                break;
            }
        }
//        Check whether all constraints satisfied
        for (Route route:solution.getRoutes()){
            Depot depot = (Depot) route.start;
            int capacity=depot.maxVehicleLoad,time=depot.maxDuration - depot.duration;
            for (Node node:route.getRoute()){
                Customer customer = (Customer) node;
                capacity -= customer.need;
                time -= customer.duration;
            }
            if (capacity < 0) {
                logger.error("weight constraint not satisfied");
                flag = false;
            }
            if (time < 0){
                logger.error("time constraint not satisfied");
            }
        }

//        Check whether distance is correct
//        logger.info(solution.getDistance());
        return flag;
    }

    @Test
    public void singleNodeGenerate() throws IOException {
        Problem[] data = CourdeauInstanceReader.getReader().readData();
        Problem p01 = data[0];
        Solution solution = new InitializeSolution(p01).getSolution();
        int number = 0;
        for(Route route:solution.getRoutes()){
            number += route.length();
        }
        Assert.assertTrue(validChecker(solution));
    }
}