package IO;

import java.util.ArrayList;

public class ConstraintsConfig {
    public ArrayList<String> hardConstraints;
    public ArrayList<String> softConstraints;
    public ArrayList<String> constraints;

    public ConstraintsConfig(ArrayList<String> hardConstraints, ArrayList<String> softConstraints, ArrayList<String> constraints) {
        this.hardConstraints = hardConstraints;
        this.softConstraints = softConstraints;
        this.constraints = constraints;
    }

    public ConstraintsConfig() {
        this.hardConstraints = new ArrayList<>();
        this.softConstraints = new ArrayList<>();
        this.constraints = new ArrayList<>();
    }
}
