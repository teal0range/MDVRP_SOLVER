package Common;

import Common.Node.Customer;
import Common.Node.Depot;
import Common.Node.Node;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class RouteTest {

    Route route;

    @Before
    public void before(){
        ArrayList<Node> r = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            r.add(new Customer(i,i,10-i,0,10));
        }
        route = new Route(r,
                new Depot(11,0,0,0,80,0),
                new Depot(11,0,0,0,80,0)
        );
    }

    @Test
    public void testSubNode() {
        route.subNode(5,new Customer(12,12,10-12,0,10));
        Assert.assertEquals(12,route.getNode(5).id);
    }

    @Test
    public void testConNode() {
        route.conNode(1,9);
        Assert.assertEquals(3, route.getLength());
    }

    @Test
    public void testAddNode() {
        route.addNode(1,new Customer(12,0,0,0,80));
        Assert.assertEquals(12,route.getNode(1).id);
    }

    @Test
    public void testRmNode() {
        route.rmNode(1);
        Assert.assertEquals(2,route.getNode(1).id);
    }
}