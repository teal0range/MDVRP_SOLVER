package IO;

import junit.framework.TestCase;

import java.io.IOException;
import java.util.Arrays;

public class CourdeauInstanceReaderTest extends TestCase {
    public void testRead() throws IOException {
        System.out.println(Arrays.toString(CourdeauInstanceReader.getReader().readData()));
    }
}