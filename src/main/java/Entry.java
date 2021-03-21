import Algorithm.InitialSolutionGenerator;
import Common.Problem;
import Common.Solution;
import IO.CourdeauInstanceReader;

import java.io.IOException;

public  class  Entry {
    public  static  void  main(String[] args) throws IOException {
        Problem[] problems = CourdeauInstanceReader.getReader().readData();
        InitialSolutionGenerator initialSolutionGenerator = new InitialSolutionGenerator(problems[0]);
        Solution solution = initialSolutionGenerator.getSolution();
        System.out.println(solution);
    }
}
