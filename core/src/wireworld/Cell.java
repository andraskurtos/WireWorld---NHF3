package wireworld; /**
 * A Cell osztály reprezentálja a sejteket egy rácsban.
 * Minden sejt egy bizonyos állapotban lehet: üres, fej, farok vagy vezeték.
 */
import java.awt.*;
import java.io.Serializable;

public class Cell implements Serializable {
    private CellState state;
    private CellState nextState;

    /**
     * Visszaadja a sejt jelenlegi állapotát szöveges formában.
     *
     * @return a sejt jelenlegi állapotának szöveges reprezentációja
     */
    public String toString() {
        return state.toString();
    }

    /**
     * Visszaadja a sejt következő állapotát.
     *
     * @return a sejt következő állapota
     */
    public CellState getNextState() {
        return nextState;
    }

    /**
     * Beállítja a sejt következő állapotát.
     *
     * @param state a sejt következő állapota
     */
    public void setNextState(CellState state) {
        this.nextState = state;
    }

    /**
     * Frissíti a sejt állapotát a következő állapotra.
     */
    public void progress() {
        state = nextState;
        nextState = null;
    }

    /**
     * Konstruktor létrehozza a sejtet üres állapotban.
     */
    public Cell() {
        state = CellState.EMPTY;
    }

    /**
     * A sejt állapotát módosítja az egérkattintás típusa alapján.
     *
     * @param leftClick true, ha bal egérgomb, false, ha jobb egérgomb lett lenyomva
     */
    public void click(boolean leftClick) {
        if (state == CellState.EMPTY && leftClick) state = CellState.WIRE;
        else if ((state == CellState.EMPTY || state == CellState.WIRE) && !leftClick) {
            state = CellState.HEAD;
        } else if (state == CellState.HEAD && !leftClick) state = CellState.TAIL;
        else state = CellState.EMPTY;
    }

    /**
     * Beállítja a sejt állapotát a megadott állapotra.
     *
     * @param state a sejt új állapota
     */
    public void setState(CellState state) {
        this.state = state;
    }

    /**
     * Visszaadja a sejt színét az állapotától függően.
     *
     * @return a sejt színe
     */
    public Color getColor() {
        if (state == CellState.WIRE) return Color.YELLOW;
        else if (state == CellState.HEAD) return Color.RED;
        else if (state == CellState.TAIL) return Color.BLUE;
        else return Color.BLACK;
    }

    /**
     * Visszaadja a sejt jelenlegi állapotát.
     *
     * @return a sejt jelenlegi állapota
     */
    public CellState getState() {
        return state;
    }
}