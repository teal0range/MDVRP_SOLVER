import Algorithm.VNSALS;
import Common.Problem;
import IO.CourdeauInstanceReader;

import java.io.IOException;

public class Entry {
    public static void main(String[] args) throws IOException{
        Problem[] problems = CourdeauInstanceReader.getReader().readData();
        Problem problem = problems[3];
        new VNSALS(problem).run();
    }
}
