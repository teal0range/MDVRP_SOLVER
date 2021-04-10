package Operators;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OperatorManagerTest {

    OperatorManager operatorManager;

    @Before
    public void before(){
        operatorManager = OperatorManager.getInstance(null);
    }

    @Test
    public void randomOpt() {
        for (int i = 0; i < 1000; i++) {
            operatorManager.incrementRecorder(0);
        }
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            Operator operator = operatorManager.sigmaGreedy();
            if (operator==operatorManager.getOpt(0))sum++;
        }
        System.out.println(sum/1000.);
        Assert.assertTrue(sum/1000.>1-OperatorManager.sigma-0.05);
    }
}