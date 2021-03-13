package Algorithm;

import Common.Problem;
import Common.Solution;
import IO.CourdeauInstanceReader;
import IO.DataReader;
import junit.framework.TestCase;

import java.io.IOException;

public class InitialSolutionGeneratorTest extends TestCase {
    public void testGenerate() throws IOException {
        Problem[] data = CourdeauInstanceReader.getReader().readData();
        Problem p01 = data[0];
        Solution solution = new InitialSolutionGenerator(p01).getSolution();
        System.out.println(solution);
    }
}