package wireworld;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A GridTest osztály a WireWorld szimuláció Grid osztályának tesztjeit tartalmazza.
 */
public class GridTest {

    /**
     * Teszteli a Grid osztály létrehozását és az alapértelmezett állapotok beállítását.
     */
    @Test
    public void testGrid() {
        Grid grid = new Grid(10, 10);
        for (int i = 0; i < grid.getWidth(); i++) {
            for (int j = 0; j < grid.getHeight(); j++)
                assertEquals(CellState.EMPTY, grid.getCell(i, j).getState());
        }
    }

    /**
     * Teszteli a Grid szélesség lekérdezését.
     */
    @Test
    public void testGetWidth() {
        Grid grid = new Grid(10, 10);
        assertEquals(10, grid.getWidth());
    }

    /**
     * Teszteli a Grid magasság lekérdezését.
     */
    @Test
    public void testGetHeight() {
        Grid grid = new Grid(10, 10);
        assertEquals(10, grid.getHeight());
    }

    /**
     * Teszteli a Grid reset metódusát.
     */
    @Test
    public void testReset() {
        Grid grid = new Grid(10, 10);
        grid.getCell(0, 0).setState(CellState.HEAD);
        grid.reset();
        assertEquals(CellState.EMPTY, grid.getCell(0, 0).getState());
    }

    /**
     * Teszteli a Grid nextGen metódusát.
     */
    @Test
    public void testNextGen() {
        Grid grid = new Grid(10, 10);
        grid.getCell(0, 0).setState(CellState.TAIL);
        grid.getCell(0,1).setState(CellState.HEAD);
        grid.getCell(0,2).setState(CellState.WIRE);
        grid.getCell(0,4).setState(CellState.WIRE);
        grid.nextGen();
        assertEquals(CellState.WIRE, grid.getCell(0, 0).getState());
        assertEquals(CellState.TAIL, grid.getCell(0, 1).getState());
        assertEquals(CellState.HEAD, grid.getCell(0, 2).getState());
        assertEquals(CellState.WIRE, grid.getCell(0, 4).getState());
    }
}
