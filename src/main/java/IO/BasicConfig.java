package IO;

import java.util.ArrayList;

public class BasicConfig {
    public ArrayList<String> hardConstraints;
    public ArrayList<String> softConstraints;
    public ArrayList<String> operators;

    public BasicConfig(ArrayList<String> hardConstraints, ArrayList<String> softConstraints, ArrayList<String> operators) {
        this.hardConstraints = hardConstraints;
        this.softConstraints = softConstraints;
        this.operators = operators;
    }

    public BasicConfig() {
        this.hardConstraints = new ArrayList<>();
        this.softConstraints = new ArrayList<>();
        this.operators = new ArrayList<>();
    }
}
