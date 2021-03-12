package IO;

import Common.Problem;
import org.apache.log4j.Logger;

import java.io.IOException;


public abstract class DataReader {
    protected final Logger logger = Logger.getLogger(DataReader.class);
    public abstract Problem[] readData() throws IOException;
}
