package wireworld;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A CellTest osztály a WireWorld szimuláció Cell osztályának tesztjeit tartalmazza.
 */
public class CellTest {

    /**
     * Teszteli a Cell osztály létrehozását és az alapértelmezett állapot beállítását.
     */
    @Test
    public void testCell() {
        Cell cell = new Cell();
        assertEquals(CellState.EMPTY, cell.getState());
    }

    /**
     * Teszteli a Cell állapotának beállítását.
     */
    @Test
    public void testSetState() {
        Cell cell = new Cell();
        cell.setState(CellState.HEAD);
        assertEquals(CellState.HEAD, cell.getState());
    }

    /**
     * Teszteli a Cell következő állapotának beállítását.
     */
    @Test
    public void testSetNextState() {
        Cell cell = new Cell();
        cell.setNextState(CellState.HEAD);
        assertEquals(CellState.HEAD, cell.getNextState());
    }

    /**
     * Teszteli, hogy a Cell a következő állapotot helyesen állítja be a progress metódussal.
     */
    @Test
    public void testProgress() {
        Cell cell = new Cell();
        cell.setNextState(CellState.HEAD);
        cell.progress();
        assertEquals(CellState.HEAD, cell.getState());
    }

    /**
     * Teszteli, hogy a Cell osztály helyesen rendeli hozzá a színeket az állapotokhoz.
     */
    @Test
    public void testColor() {
        Cell cell = new Cell();
        cell.setState(CellState.HEAD);
        assertEquals(java.awt.Color.RED, cell.getColor());
        cell.setState(CellState.TAIL);
        assertEquals(java.awt.Color.BLUE, cell.getColor());
        cell.setState(CellState.WIRE);
        assertEquals(java.awt.Color.YELLOW, cell.getColor());
        cell.setState(CellState.EMPTY);
        assertEquals(java.awt.Color.BLACK, cell.getColor());
    }
}
