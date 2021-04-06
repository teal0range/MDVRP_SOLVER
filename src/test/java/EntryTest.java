import Algorithm.GreedyGenerator;
import Common.Node.Customer;
import Common.Node.Depot;
import Common.Node.Node;
import Common.Problem;
import Common.Route;
import Common.Solution;
import IO.CourdeauInstanceReader;
import Operators.*;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import static org.apache.log4j.LogManager.getLogger;
import static org.junit.Assert.*;

public class EntryTest {


    Logger logger = getLogger(Entry.class);

    public boolean validChecker(Solution solution){
        Problem problem = solution.problem;
        boolean flag = true;
//        Check whether all nodes exist
        Customer []customers = problem.customers;
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
            if (capacity < 0||time<0) {
                logger.error("Constraints not satisfied");
                flag = false;
            }
        }

//        Check whether distance is correct
//        logger.info(solution.getDistance());
        return flag;
    }

    @Test
    public void randomOpt() throws IOException {
        Problem[] problems = CourdeauInstanceReader.getReader().readData();
        Solution solution = new GreedyGenerator(problems[0]).build();
        List<Operator> opt = new ArrayList<>();
        opt.add(new OuterShift10(problems[0]));
        opt.add(new InnerShift10(problems[0]));
        opt.add(new OuterSwap10(problems[0]));
        opt.add(new InnerSwap10(problems[0]));
        opt.add(new Insertion(problems[0]));
        logger.info(solution.getDistance());
        Solution bestSol = new Solution(solution);
        for (int i = 0; i < 100000; i++) {
            Operator operator = opt.get(new Random().nextInt(opt.size()));
            operator.doOperateAll(solution);
            if (!validChecker(solution)) {
                logger.error(String.format("%s opt", operator.getClass().getName()));
            }
            if (solution.getDistance() < bestSol.getDistance()){
                bestSol = new Solution(solution);
                logger.info(solution.getDistance());
            }
        }
        System.out.println(bestSol.getDistance());
    }
}