package wireworld;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Az AutomataTest osztály a WireWorld szimuláció Automata osztályának tesztjeit tartalmazza.
 */
public class AutomataTest {

    private WireWorldPanel mockPanel;

    /**
     * A tesztelés előtt inicializál egy hamis WireWorldPanel objektumot.
     */
    @Before
    public void setUp() {
        // Create a mock WireWorldPanel for testing
        mockPanel = new WireWorldPanel(new Grid(10, 10));
    }

    /**
     * Teszteli az Automata osztály létrehozását és a delay beállítását.
     */
    @Test
    public void testAutomata() {
        Automata automata = new Automata(mockPanel, 100);
        assertEquals(100, automata.getDelay());
        assertFalse(automata.running());
    }

    /**
     * Teszteli a delay lekérdezését.
     */
    @Test
    public void testGetDelay() {
        Automata automata = new Automata(mockPanel, 100);
        assertEquals(100, automata.getDelay());
    }

    /**
     * Teszteli, hogy az Automata running() metódusa helyesen detektálja a szimuláció futását.
     */
    @Test
    public void testRunning() {
        Automata automata = new Automata(mockPanel, 100);
        assertFalse(automata.running());
        Thread thread = new Thread(automata);
        thread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(automata.running());
        automata.stopRunning();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertFalse(automata.running());
    }
}
