import Algorithm.GreedyGenerator;
import Algorithm.InitializeSolution;
import Common.Problem;
import Common.Solution;
import IO.CourdeauInstanceReader;

import java.io.IOException;

public  class  Entry {
    public  static  void  main(String[] args) throws IOException {
        Problem[] problems = CourdeauInstanceReader.getReader().readData();
        Solution solution = new GreedyGenerator(problems[0]).build();
        System.out.println(solution);
    }
}
