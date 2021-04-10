package Operators;

import Common.Node;
import Common.Route;
import Common.Solution;
import Utils.RandomController;

public class RandomRuin implements Ruin {

    @Override
    public void doOuterRuin(Solution solution, double ratio) {
        for (int i=0;i<solution.getRoutes().size();i++) {
            if (RandomController.nextDouble() < ratio){
                solution.destroy(i);
                i--;
            }
        }
    }

    @Override
    public void doInnerRuin(Solution solution, double ratio) {
        for (Route route: solution.getRoutes()) {
            for (int i = 0; i < route.length();i++) {
                if (RandomController.nextDouble() < ratio){
                    solution.unassignedCustomer.add(route.getNode(i));
                    route.rmNode(i);
                    i--;
                }
            }
        }
        for (int i=0;i<solution.getRoutes().size();i++){
            if (solution.getRoutes().get(i).length()==0){
                solution.destroy(i);
                i--;
            }
        }
    }
}
