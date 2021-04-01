package IO;

import Common.Problem;
import org.apache.log4j.Logger;

import java.io.IOException;


public interface DataReader {
    Logger logger = Logger.getLogger(DataReader.class);
    Problem[] readData() throws IOException;
}
