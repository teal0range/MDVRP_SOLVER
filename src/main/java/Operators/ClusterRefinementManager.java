package Operators;

import Utils.RandomController;

import java.util.ArrayList;
import java.util.List;

public class ClusterRefinementManager {
    List<ClusterRefinementCriteria> criteria;
    private static final ClusterRefinementManager manager = new ClusterRefinementManager();

    private ClusterRefinementManager(){
        criteria = new ArrayList<>();
        criteria.add(new ClusterRefinement1());
        criteria.add(new ClusterRefinement2());
        criteria.add(new ClusterRefinement3());
    }

    public static ClusterRefinementManager getInstance(){
        return manager;
    }

    public ClusterRefinementCriteria randomCriteria(){
        return criteria.get(RandomController.nextInt(criteria.size()));
    }
}
