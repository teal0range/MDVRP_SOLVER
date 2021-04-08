import Algorithm.GreedyGenerator;
import Common.Problem;
import Common.Solution;
import IO.CourdeauInstanceReader;
import Operators.Insertion;
import Operators.OperationSelector;
import Operators.Shift10;
import Operators.Swap11;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.apache.log4j.LogManager.getLogger;

public class Entry {
    public static void main(String[] args) throws IOException {
        Logger logger = getLogger(Entry.class);
        Problem[] problems = CourdeauInstanceReader.getReader().readData();
        Solution solution = new GreedyGenerator(problems[0]).build();
        List<OperationSelector> opt = new ArrayList<>();
        opt.add(new Shift10(problems[0]));
        opt.add(new Swap11(problems[0]));
//        opt.add(new InnerSwap11(problems[0]));
        opt.add(new Insertion(problems[0]));
        logger.info(solution.getDistance());
        Solution bestSol = new Solution(solution);
        for (int i = 0; i < 100000; i++) {
            OperationSelector operationSelector = opt.get(new Random().nextInt(opt.size()));
            double distance = solution.getDistance();
            operationSelector.doOperateAll(solution);
            if (solution.getDistance() - distance < -0.001) {
                logger.info(String.format("%s opt: decreased %.5f", operationSelector.getClass().getName(), -distance + solution.getDistance()));
            }
            if (solution.getDistance() < bestSol.getDistance()) {
                bestSol = new Solution(solution);
            }
        }
        logger.info(bestSol.getDistance());
    }
}
