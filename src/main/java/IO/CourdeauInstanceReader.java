package IO;

import Common.Node.Customer;
import Common.Node.Depot;
import Common.Node.Node;
import Common.Problem;

import java.io.*;
import java.util.Arrays;

public class CourdeauInstanceReader implements DataReader{
    private static DataReader Instance;

    private CourdeauInstanceReader(){}

    public static DataReader getReader() {
        if(Instance == null){
            Instance = new CourdeauInstanceReader();
        }
        return Instance;
    }

    public Problem[] readData() throws IOException {
        String dataRoot = "src/main/resources/Data/";
        File file = new File(dataRoot);
        File[] fs = file.listFiles();
        assert fs != null;
        Problem []problem = new Problem[fs.length];
        for (int i = 0; i < fs.length; i++) {
            File f = fs[i];
            String name = f.getName();
            BufferedReader br = new BufferedReader(new FileReader(f));
            String []desc = br.readLine().split(" ");
            problem[i] = new Problem(
                    Integer.parseInt(desc[0]),
                    Integer.parseInt(desc[1]),
                    Integer.parseInt(desc[2]),
                    Integer.parseInt(desc[3])
            );
            int[][] constraints = new int[problem[i].getDepotsNumber()][2];
            for (int j = 0; j < problem[i].getDepotsNumber(); j++) {
                String []cons = br.readLine().trim().split("[ ]+");
                constraints[j] = new int[]{Integer.parseInt(cons[0]),Integer.parseInt(cons[1])};
            }
            Node []nodes = new Node[problem[i].getCustomerNumber()+problem[i].getDepotsNumber()];
            for (int j = 0; j < problem[i].getCustomerNumber(); j++) {
                String []cus = br.readLine().trim().split("[ ]+");
                nodes[j] = new Customer(
                        Integer.parseInt(cus[0]),
                        Double.parseDouble(cus[1]),
                        Double.parseDouble(cus[2]),
                        Integer.parseInt(cus[3]),
                        Integer.parseInt(cus[4])
                );
            }
            for (int j = 0; j < problem[i].getDepotsNumber(); j++) {
                String []dep = br.readLine().trim().split("[ ]+");
                nodes[j+problem[i].getCustomerNumber()] = new Depot(
                        Integer.parseInt(dep[0]),
                        Double.parseDouble(dep[1]),
                        Double.parseDouble(dep[2]),
                        Integer.parseInt(dep[3]),
                        constraints[j][0],
                        constraints[j][1]
                );
            }
            problem[i].setNodes(nodes);
            problem[i].setName(name);
        }
        logger.info(String.format("%d DataSets Loaded",fs.length));
        logger.info(String.format("DataSets List: %s",Arrays.toString(fs)));
        return problem;
    }
}
