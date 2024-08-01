package wireworld;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

/**
 * A Menu osztály egy menüt reprezentál a WireWorld szimulációhoz.
 */
public class Menu extends JFrame {

    private JPanel panel = new JPanel();
    private JButton newButton = new JButton("NEW");
    private JButton loadButton = new JButton("LOAD");
    private JButton exitButton = new JButton("EXIT");

    /**
     * Konstruktor beállítja a menü tulajdonságait, elrendezését és hozzáadja a komponenseket.
     */
    public Menu() {
        setTitle("WIREWORLD");
        setIconImage(new ImageIcon("pictures/icon.png").getImage());
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        newButton.setFont(new Font("Arial", Font.BOLD, 50));
        loadButton.setFont(new Font("Arial", Font.BOLD, 50));
        exitButton.setFont(new Font("Arial", Font.BOLD, 50));

        panel.setLayout(new GridLayout(3, 1));
        panel.add(newButton);
        panel.add(loadButton);
        panel.add(exitButton);
        add(panel);

        JLabel pictureLabel = new JLabel(new ImageIcon("pictures/wireworld.png"));
        add(pictureLabel, BorderLayout.WEST);

        setSize(1300, 700);
        pictureLabel.setBackground(Color.BLACK);
        setVisible(true);

        newButton.addActionListener(e -> {
            new WireWorldFrame(new Grid(450, 220));
            dispose();
        });

        loadButton.addActionListener(e -> {
            try {
                new WireWorldFrame(FileManager.load());
                dispose();
            } catch (Exception ex) {
                System.out.println("Error: File not found or has been corrupted");
            }
        });

        exitButton.addActionListener(e -> {
            System.exit(0);
        });
    }

    /**
     * A program belépési pontja, létrehozza a menüt.
     *
     * @param args a parancssori argumentumok
     */
    public static void main(String[] args) {
        new Menu();
    }
}
