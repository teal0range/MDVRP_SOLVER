package IO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicConfig {
    public List<String> hardConstraints;
    public List<String> softConstraints;
    public List<String> operators;
    public List<String> perturbation;
    public Map<String,Object> parameters;

    public BasicConfig(List<String> hardConstraints,
                       List<String> softConstraints,
                       List<String> operators,
                       List<String> perturbation,
                       Map<String,Object> parameters) {
        this.hardConstraints = hardConstraints;
        this.softConstraints = softConstraints;
        this.operators = operators;
        this.perturbation = perturbation;
        this.parameters = parameters;
    }

    public BasicConfig() {
        this.hardConstraints = new ArrayList<>();
        this.softConstraints = new ArrayList<>();
        this.operators = new ArrayList<>();
        this.perturbation = new ArrayList<>();
        this.parameters = new HashMap<>();
    }
}
