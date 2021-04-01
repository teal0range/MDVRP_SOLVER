import Algorithm.GreedyGenerator;
import Common.Problem;
import Common.Solution;
import IO.CourdeauInstanceReader;
import Operators.InnerShift10;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public  class  Entry {
    public  static  void  main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Problem[] problems = CourdeauInstanceReader.getReader().readData();
        Solution solution = new GreedyGenerator(problems[3]).build();
        System.out.println(solution.getDistance());
        new InnerShift10(problems[3]).doOperateAll(solution);
        System.out.println(solution.getDistance());
    }
}
