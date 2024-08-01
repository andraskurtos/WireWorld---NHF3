package wireworld;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * A WireWorldFrame osztály egy JFrame-t reprezentál, amelyben a WireWorld szimuláció van megjelenítve.
 */
public class WireWorldFrame extends JFrame {
    private WireWorldPanel panel;
    private AtomicReference<Automata> automata = new AtomicReference<>(new Automata(panel, 100));
    private AtomicReference<Thread> thread = new AtomicReference<>(new Thread(automata.get()));

    /**
     * Konstruktor beállítja a Frame tulajdonságait, elrendezését és hozzáadja a komponenseket.
     *
     * @param grid a kezdeti rács a szimulációhoz
     */
    public WireWorldFrame(Grid grid) {
        setTitle("WIREWORLD");
        setIconImage(new ImageIcon("pictures/icon.png").getImage());
        setSize(1300, 700);
        panel = new WireWorldPanel(grid);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JButton stepButton = new JButton("Step");
        stepButton.addActionListener(e -> {
            panel.grid.nextGen();
            panel.repaint();
        });

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            panel.grid.reset();
            panel.repaint();
        });

        JTextField delayField = new JTextField("100");
        JTextArea delayLabel = new JTextArea("Delay (ms):");
        delayLabel.setEditable(false);
        delayLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        delayLabel.setBackground(new Color(0, 0, 0, 0));
        delayField.setPreferredSize(new Dimension(50, 20));
        delayField.setFont(new Font("Arial", Font.PLAIN, 13));
        delayField.addActionListener(e -> {
            try {
                automata.get().setDelay(Integer.parseInt(delayField.getText()));
            } catch (Exception exception) {
                System.out.println("Error: Invalid input");
                delayField.setText("100");
            }

        });

        JButton startButton = new JButton("Start/Stop");
        startButton.addActionListener(e -> {
            if (automata.get().running()) {
                automata.get().stopRunning();
            } else {
                automata.set(new Automata(panel, Integer.parseInt(delayField.getText())));
                thread.set(new Thread(automata.get()));
                thread.get().start();
            }
        });

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            try {
                FileManager.save(panel.grid);
            } catch (Exception exception) {
                System.out.println("Error: File not found");
            }
        });

        JButton quitButton = new JButton("Quit to Menu");
        quitButton.addActionListener(e -> {
            new Menu();
            dispose();
        });

        JPanel panel2 = new JPanel();
        panel2.add(stepButton);
        panel2.add(delayLabel);
        panel2.add(delayField);
        panel2.add(startButton);
        panel2.add(clearButton);
        panel2.add(saveButton);
        panel2.add(quitButton);

        add(panel2, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        setResizable(false);
        setVisible(true);
    }
}
