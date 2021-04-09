package Algorithm;

import Common.Problem;
import IO.CourdeauInstanceReader;
import Operators.OperatorManager;
import org.apache.log4j.Logger;

import java.io.IOException;

import static org.apache.log4j.LogManager.getLogger;

public class MainAlgo {
    OperatorManager operatorManager;
    Problem problem;

    public MainAlgo(Problem problem) {
        this.operatorManager = OperatorManager.getInstance(problem);
        this.problem = problem;
    }

    public static void main(String[] args) throws IOException {
        Logger logger = getLogger(MainAlgo.class);
        Problem[] problems = CourdeauInstanceReader.getReader().readData();
        new MainAlgo(problems[0]);
    }
}
