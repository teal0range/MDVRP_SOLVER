import Algorithm.GreedyGenerator;
import Common.Problem;
import Common.Solution;
import IO.CourdeauInstanceReader;
import Operators.InnerShift10;
import Operators.OuterShift10;

import java.io.IOException;

public  class  Entry {
    public  static  void  main(String[] args) throws IOException {
        Problem[] problems = CourdeauInstanceReader.getReader().readData();
        Solution solution = new GreedyGenerator(problems[0]).build();
        System.out.println(solution.getDistance());
        new OuterShift10(problems[0]).doOperateAll(solution);
        new InnerShift10(problems[0]).doOperateAll(solution);
        new OuterShift10(problems[0]).doOperateAll(solution);
        new OuterShift10(problems[0]).doOperateAll(solution);
        new OuterShift10(problems[0]).doOperateAll(solution);
        new OuterShift10(problems[0]).doOperateAll(solution);
        new OuterShift10(problems[0]).doOperateAll(solution);
        new OuterShift10(problems[0]).doOperateAll(solution);
        new OuterShift10(problems[0]).doOperateAll(solution);
        new InnerShift10(problems[0]).doOperateAll(solution);
        new OuterShift10(problems[0]).doOperateAll(solution);
        new InnerShift10(problems[0]).doOperateAll(solution);
        System.out.println(solution.getDistance());
    }
}
