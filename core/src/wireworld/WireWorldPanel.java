package wireworld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * A WireWorldPanel osztály egy JPanel-t reprezentál, amely a WireWorld szimuláció rácsát rajzolja ki.
 */
public class WireWorldPanel extends JPanel {
    Grid grid;
    private int cellSize = 50;
    int startCol = 96;
    int startRow = 45;

    /**
     * Konstruktor létrehozza a WireWorldPanel-t a megadott ráccsal.
     *
     * @param grid a kezdeti rács a szimulációhoz
     */
    public WireWorldPanel(Grid grid) {
        this.grid = grid;
        addMouseListener(new CellClickListener());
        setPreferredSize(new Dimension(1285, 650));
        setBackground(Color.GREEN);
        setFocusable(true);
        requestFocusInWindow();
        KeyBinds keyBinds = new KeyBinds(this);
    }

    /**
     * Visszaadja a cella méretét.
     *
     * @return a cella mérete
     */
    public int getCellSize() {
        return cellSize;
    }

    /**
     * Beállítja a cella méretét.
     *
     * @param cellSize az új cella méret
     */
    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }

    /**
     * Az osztály paintComponent metódusa felelős a WireWorldPanel komponens tartalmának kirajzolásáért.
     * A rács elemeit kirajzolja a megfelelő színekkel és mérettel a képernyőre.
     *
     * @param g a Graphics objektum, amelyen keresztül a rajzolás történik
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int gridWidth = grid.getWidth();
        int gridHeight = grid.getHeight();
        int cols = 1300 / cellSize;
        int rows = 650 / cellSize;
        if (startCol < 0) startCol = 0;
        if (startRow < 0) startRow = 0;
        if (startCol > gridWidth - cols) startCol = gridWidth - cols;
        if (startRow > gridHeight - rows) startRow = gridHeight - rows;

        for (int i = startCol; i < Math.min(startCol + cols + 1, gridWidth); i++) {
            for (int j = startRow; j < Math.min(startRow + rows + 1, gridHeight); j++) {
                Cell cell = grid.getCell(i, j);
                Color color = cell.getColor();
                g.setColor(color);
                g.fillRect((i - startCol) * cellSize, (j - startRow) * cellSize, cellSize, cellSize);
            }
        }
    }

    /**
     * A CellClickListener osztály felelős a WireWorldPanel komponensen történő egérkattintások kezeléséért.
     * A felhasználó kattintásra állapotváltoztatást hajthat végre a rács egyes celláin.
     */
    private class CellClickListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            SwingUtilities.invokeLater(() -> {
                int mouseX = e.getX();
                int mouseY = e.getY();

                int clickedRow = mouseY / (cellSize) + startRow;
                int clickedCol = mouseX / (cellSize) + startCol;

                if (clickedRow >= 0 && clickedRow < grid.getHeight() &&
                        clickedCol >= 0 && clickedCol < grid.getWidth()) {
                    Cell clickedCell = grid.getCell(clickedCol, clickedRow);
                    clickedCell.click(e.getButton() == MouseEvent.BUTTON1);
                    repaint();
                }
            });
        }
    }
}
