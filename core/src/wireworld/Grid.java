package wireworld;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * A Grid osztály reprezentálja a sejtekből álló rácsot.
 * Minden sejt egy bizonyos állapotban lehet: üres, fej, farok vagy vezeték.
 */
public class Grid implements Serializable {
    private List<List<Cell>> cells;

    /**
     * Konstruktor létrehozza a rácsot a megadott szélességű és magasságú sejtekből.
     * Minden sejtet inicializál egy üres sejttel.
     *
     * @param width  a rács szélessége
     * @param height a rács magassága
     */
    public Grid(int width, int height) {
        cells = new ArrayList<>(width);
        for (int i = 0; i < width; i++) {
            List<Cell> column = new ArrayList<>(height);
            for (int j = 0; j < height; j++) {
                column.add(new Cell());
            }
            cells.add(column);
        }
    }

    /**
     * Visszaadja a megadott koordinátájú sejtet a rácsban.
     *
     * @param x sejt x koordinátája
     * @param y sejt y koordinátája
     * @return a megadott koordinátájú sejt
     */
    public Cell getCell(int x, int y) {
        return cells.get(x).get(y);
    }

    /**
     * Visszaadja a rács szélességét.
     *
     * @return a rács szélessége
     */
    public int getWidth() {
        return cells.size();
    }

    /**
     * Visszaadja a rács magasságát.
     *
     * @return a rács magassága
     */
    public int getHeight() {
        return cells.isEmpty() ? 0 : cells.get(0).size();
    }

    /**
     * Minden sejtet visszaállít üres állapotba a rácsban.
     */
    public void reset() {
        for (List<Cell> column : cells) {
            for (Cell cell : column) {
                cell.setState(CellState.EMPTY);
            }
        }
    }

    /**
     * A következő generációra lép a rácsban lévő sejtek alapján.
     * Szabályok szerint frissíti a sejtek állapotát.
     * - Üres sejt marad üres.
     * - Fej (HEAD) esetén a következő állapot farok (TAIL).
     * - Farok (TAIL) esetén a következő állapot vezeték (WIRE).
     * - Vezeték (WIRE) esetén az új állapotot a környező fejek alapján határozza meg:
     *   - Ha pontosan egy vagy két fej van a környezetében, akkor a következő állapot fej (HEAD).
     *   - Ellenkező esetben a következő állapot vezeték (WIRE).
     */
    public void nextGen() {
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                Cell cell = getCell(i, j);
                switch (cell.getState()) {
                    case EMPTY:
                        cell.setNextState(CellState.EMPTY);
                        break;
                    case HEAD:
                        cell.setNextState(CellState.TAIL);
                        break;
                    case TAIL:
                        cell.setNextState(CellState.WIRE);
                        break;
                    case WIRE:
                        int heads = 0;
                        for (int k = -1; k < 2; k++) {
                            for (int l = -1; l < 2; l++) {
                                if (k == 0 && l == 0) continue;
                                if (i + k >= 0 && i + k < getWidth() && j + l >= 0 && j + l < getHeight()) {
                                    if (getCell(i + k, j + l).getState() == CellState.HEAD) heads++;
                                }
                            }
                        }
                        if (heads == 1 || heads == 2) cell.setNextState(CellState.HEAD);
                        else cell.setNextState(CellState.WIRE);
                        break;
                }
            }
        }
        for (List<Cell> column : cells) {
            for (Cell cell : column) {
                cell.progress();
            }
        }
    }
}