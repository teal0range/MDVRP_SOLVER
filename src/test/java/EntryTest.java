import Algorithm.GreedyGenerator;
import Common.Customer;
import Common.Depot;
import Common.Node;
import Common.Problem;
import Common.Route;
import Common.Solution;
import IO.CourdeauInstanceReader;
import Operators.*;
import Utils.RandomController;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.apache.log4j.LogManager.getLogger;

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
    public void randomOpt() throws IOException {
        RandomController.setSeed(1);
        Problem[] problems = CourdeauInstanceReader.getReader().readData();
        Problem problem = problems[0];
        Solution solution = new GreedyGenerator(problem).build();
        List<OperationSelector> opt = new ArrayList<>();
        IPerturbation perturbation = new RecreatePerturbation(problem);
        opt.add(new Shift10(problem));
        opt.add(new Swap11(problem));
        opt.add(new Shift20(problem));
//        opt.add(new Insertion(problem));
        opt.add(new TwoOpt(problem));
        opt.add(new Swap22(problem));
        opt.add(new Swap21(problem));
        opt.add(new TwoOptStar2(problem));
        opt.add(new TwoOptStar1(problem));
        logger.info(solution.getDistance());
        Solution bestSol = new Solution(solution);
        for (int i = 0; i < 200000; i++) {
            OperationSelector operationSelector = opt.get(RandomController.nextInt(opt.size()));
            operationSelector.doOperateAll(solution);
            if (!validChecker(solution)) {
                logger.error(String.format("%s opt", operationSelector.getClass().getName()));
            }
            if (solution.getDistance() < bestSol.getDistance()){
                bestSol = new Solution(solution);
                logger.info(solution.getDistance());
            }
            double distance = solution.getDistance();
            if (Math.abs(solution.refreshDistance()-distance)>0.001)
                System.out.println(i + " "+operationSelector.getClass().getName());
            Assert.assertEquals(solution.refreshDistance(),distance,0.001);
            if (i%10==0) {
//                new Insertion(problem).doOperateAll(solution);
                perturbation.perturb(solution, 3);
                solution.refreshDistance();
            }
        }
        Assert.assertTrue(validChecker(solution));
        System.out.println(bestSol.getDistance());
    }

    @Test
    public void testQuartz() throws IOException {
        Problem[] problems = CourdeauInstanceReader.getReader().readData();
        OperationSelector operationSelector = new Shift10(problems[0]);
    }
}