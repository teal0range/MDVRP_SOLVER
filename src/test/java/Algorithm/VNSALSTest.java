package Algorithm;

import Common.Problem;
import IO.CourdeauInstanceReader;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class VNSALSTest {
    @Test
    public void testMainLoop() throws IOException {
        Problem[] problems = CourdeauInstanceReader.getReader().readData();
        Problem problem = problems[3];
        new VNSALS(problem).run();
    }
}