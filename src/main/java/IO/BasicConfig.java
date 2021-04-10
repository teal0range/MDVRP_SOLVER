package IO;

import java.util.ArrayList;
import java.util.List;

public class BasicConfig {
    public List<String> hardConstraints;
    public List<String> softConstraints;
    public List<String> operators;
    public List<String> perturbation;

    public BasicConfig(List<String> hardConstraints,
                       List<String> softConstraints,
                       List<String> operators,
                       List<String> perturbation) {
        this.hardConstraints = hardConstraints;
        this.softConstraints = softConstraints;
        this.operators = operators;
        this.perturbation = perturbation;
    }

    public BasicConfig() {
        this.hardConstraints = new ArrayList<>();
        this.softConstraints = new ArrayList<>();
        this.operators = new ArrayList<>();
        this.perturbation = new ArrayList<>();
    }
}
