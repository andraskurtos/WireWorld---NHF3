package wireworld;

/**
 * Az Automata osztály felelős a WireWorld szimuláció futtatásáért és vezérléséért.
 */
public class Automata implements Runnable {
    private WireWorldPanel panel;
    private boolean running;
    private int delay;

    /**
     * Visszaadja a késleltetést a lépések között.
     *
     * @return a késleltetés a lépések között (ms-ben)
     */
    public int getDelay() {
        return delay;
    }

    /**
     * Konstruktor létrehozza az Automata objektumot a megadott WireWorldPanel-lel és késleltetéssel.
     *
     * @param panel a WireWorldPanel objektum, amelyen a szimuláció fut
     * @param delay a késleltetés a lépések között (ms-ben)
     */
    public Automata(WireWorldPanel panel, int delay) {
        this.panel = panel;
        this.delay = delay;
        running = false;
    }

    /**
     * Megadja, hogy éppen fut-e a szimuláció.
     *
     * @return true, ha a szimuláció fut, false egyébként
     */
    public boolean running() {
        return running;
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            panel.grid.nextGen();
            panel.repaint();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
    }

    /**
     * Leállítja a szimulációt.
     */
    public void stopRunning() {
        running = false;
    }

    /**
     * Beállítja a késleltetést a lépések között.
     *
     * @param delay a késleltetés a lépések között (ms-ben)
     */
    public void setDelay(int delay) {
        this.delay = delay;
    }
}
