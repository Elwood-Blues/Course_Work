package waterjug;

import framework.Move;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A class to test the WaterJugMove class.
 * @Peter Braband
 */
public class WaterJugMoveTest {
    
    // You should use the BridgeMoveTest class as a model for setting up
    // the tests with private instance fields here.
    WaterJugState state1 = new WaterJugState(1,2);
    WaterJugState state2 = new WaterJugState(2,3);
    WaterJugMove fillX = new WaterJugMove("Fill Jug X");
    WaterJugMove fillY = new WaterJugMove("Fill Jug Y");
    WaterJugMove XtoY = new WaterJugMove("Transfer Jug X to Jug Y");
    WaterJugMove YtoX = new WaterJugMove("Transfer Jug Y to Jug X");
    WaterJugMove emptyX = new WaterJugMove("Empty Jug X");
    WaterJugMove emptyY = new WaterJugMove("Empty Jug Y");

    /**
     * Tests filling jug X
     */
    @Test
    public void testFillX() {
        WaterJugState next = (WaterJugState) fillX.doMove(state1);
        assertTrue(next.equals(new WaterJugState(3, state1.getJugAmount(1))));
    }

    /**
     * Tests filling jug Y
     */
    @Test
    public void testFillY() {
        WaterJugState next = (WaterJugState) fillY.doMove(state1);
        assertTrue(next.equals(new WaterJugState(state1.getJugAmount(0),4)));
    }
    
    /**
     * Tests emptying jug X
     */
    @Test
    public void testEmptyX() {
        WaterJugState next = (WaterJugState) emptyX.doMove(state1);
        assertTrue(next.equals(new WaterJugState(0,state1.getJugAmount(1))));
    }
    
    /**
     * Tests emptying jug Y
     */
    @Test
    public void testEmptyY() {
        WaterJugState next = (WaterJugState) emptyY.doMove(state1);
        assertTrue(next.equals(new WaterJugState(state1.getJugAmount(0),0)));
    }
    
    /**
     * Tests transferring jug X to jug Y
     */
    @Test
    public void testXToY() {
        WaterJugState next = (WaterJugState) XtoY.doMove(state1);
        assertTrue(next.equals(new WaterJugState(0,3)));
        
        next = (WaterJugState) XtoY.doMove(state2);
        assertTrue(next.equals(new WaterJugState(1,4)));
    }
    
    /**
     * Tests transferring jug Y to jug X
     */
    @Test
    public void testYToX() {
        WaterJugState next = (WaterJugState) YtoX.doMove(state1);
        assertTrue(next.equals(new WaterJugState(3,0)));
        
        next = (WaterJugState) YtoX.doMove(state2);
        assertTrue(next.equals(new WaterJugState(3,2)));
    }
}
