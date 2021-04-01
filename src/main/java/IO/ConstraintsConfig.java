package IO;

import java.util.ArrayList;

public class ConstraintsConfig{
    public ArrayList<String> hardConstraints;
    public ArrayList<String> softConstraints;

    public ConstraintsConfig(ArrayList<String> hardConstraints, ArrayList<String> softConstraints) {
        this.hardConstraints = hardConstraints;
        this.softConstraints = softConstraints;
    }

    public ConstraintsConfig() {
        this.hardConstraints = new ArrayList<>();
        this.softConstraints = new ArrayList<>();
    }
}
