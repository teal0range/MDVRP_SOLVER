import IO.CourdeauInstanceReader;
import  org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;

public  class  Entry {
    public  static  void  main(String[] args) throws IOException {
        System.out.println(Arrays.toString(new CourdeauInstanceReader().readData()));
    }
}
