package wireworld;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * A KeyBinds osztály felelős a billentyűparancsok kezeléséért a WireWorldPanel komponensen.
 * Lehetővé teszi a rács mozgatását, valamint a cellák nagyítását és kicsinyítését.
 */
public class KeyBinds {
    WireWorldPanel panel;
    InputMap inputMap;
    ActionMap actionMap;

    // Akciók a zoomhoz és a panninghez
    Action zoomIn = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.setCellSize((int) (panel.getCellSize() * 1.2));
            panel.repaint();
        }
    };

    Action zoomOut = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.setCellSize((int) (panel.getCellSize() * 0.8));
            if (panel.getCellSize() < 5) panel.setCellSize(5);
            panel.repaint();
        }
    };

    Action panRight = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.startCol++;
            panel.repaint();
        }
    };

    Action panLeft = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.startCol--;
            panel.repaint();
        }
    };

    Action panUp = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.startRow -= 1;
            panel.repaint();
        }
    };

    Action panDown = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.startRow += 1;
            panel.repaint();
        }
    };

    /**
     * Konstruktor létrehozza a KeyBinds objektumot a megadott WireWorldPanel-lel.
     *
     * @param panel a WireWorldPanel objektum, amelyen a billentyűparancsok érvényesek
     */
    public KeyBinds(WireWorldPanel panel) {
        this.panel = panel;
        inputMap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        actionMap = panel.getActionMap();

        // Billentyűkombinációk hozzárendelése az akciókhoz
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "panRight");
        actionMap.put("panRight", panRight);

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "panLeft");
        actionMap.put("panLeft", panLeft);

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "panUp");
        actionMap.put("panUp", panUp);

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "panDown");
        actionMap.put("panDown", panDown);

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, 0), "zoomIn");
        actionMap.put("zoomIn", zoomIn);

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_X, 0), "zoomOut");
        actionMap.put("zoomOut", zoomOut);
    }
}
