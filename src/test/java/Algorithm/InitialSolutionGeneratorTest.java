package Algorithm;

import Common.Problem;
import Common.Route;
import Common.Solution;
import IO.CourdeauInstanceReader;
import junit.framework.TestCase;

import java.io.IOException;

public class InitialSolutionGeneratorTest extends TestCase {
    public void testSingleNodeGenerate() throws IOException {
        Problem[] data = CourdeauInstanceReader.getReader().readData();
        Problem p01 = data[0];
        Solution solution = new InitializeSolution(p01).getSolution();
        int number = 0;
        for(Route route:solution.getRoutes()){
            number += route.length();
        }
        assertEquals(number,p01.customerNumber);
    }
}