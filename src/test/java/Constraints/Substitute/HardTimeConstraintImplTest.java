package Constraints.Substitute;

import Common.Node.Customer;
import Common.Node.Depot;
import Common.Node.Node;
import Common.Route;
import org.junit.Before;

import java.util.ArrayList;

public class HardTimeConstraintImplTest {
    Route route;

    @Before
    public void before(){
        ArrayList<Node> r = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            r.add(new Customer(i, i,10-i,0,10));
        }
        route = new Route(r,
                new Depot(11,0,0,0,80,0),
                new Depot(11,0,0,0,80,0)
        );
    }


}


