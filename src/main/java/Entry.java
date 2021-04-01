import Algorithm.GreedyGenerator;
import Common.Problem;
import Common.Solution;
import Constraints.ConstraintManager;
import IO.CourdeauInstanceReader;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public  class  Entry {
    public  static  void  main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
//        Problem[] problems = CourdeauInstanceReader.getReader().readData();
//        Solution solution = new GreedyGenerator(problems[29]).build();
//        System.out.println(solution);
//        Class<?> clazz = Class.forName("Constraints.SubstituteConstraints.SubstituteConstraintManager");
//        Method getInstance = clazz.getMethod("getInstance");
//        System.out.println(getInstance.invoke(null));
        ConstraintManager substitute = ConstraintManager.getInstance("Substitute");
        ConstraintManager insertion = ConstraintManager.getInstance("Insertion");
        System.out.println(insertion);
    }
}
