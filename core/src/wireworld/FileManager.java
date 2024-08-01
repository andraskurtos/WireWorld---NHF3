package wireworld;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

/**
 * A FileManager osztály felelős a WireWorld szimuláció mentéséért és betöltéséért.
 */
public class FileManager {
    /**
     * Elmenti a rácsot egy fájlba.
     *
     * @param grid a mentendő rács
     */
    public static void save(Grid grid) throws IOException {
        File saveFile = fileChooser();
        if (saveFile == null) throw new FileNotFoundException("File not found");
        FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
        ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
        out.writeObject(grid);
        out.close();
        System.out.println("Saved to: " + saveFile.getName());
    }

    /**
     * Betölti a rácsot egy fájlból.
     *
     * @return a betöltött rács
     * @throws FileNotFoundException ha a fájl nem található
     */
    public static Grid load() throws IOException, ClassNotFoundException, FileNotFoundException{
        File loadFile = fileChooser();
        if (loadFile == null) throw new FileNotFoundException("File not found");
        System.out.println("Loaded from: " + loadFile.getName());
        return (Grid) new ObjectInputStream(new FileInputStream(loadFile)).readObject();
    }

    /**
     * Fájl kiválasztó ablakot jelenít meg.
     *
     * @return a kiválasztott fájl
     */
    public static File fileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save to / load from");
        fileChooser.setCurrentDirectory(new java.io.File("./saves"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Wireworld Saves", "ww");
        fileChooser.setFileFilter(filter);
        int userSelection = fileChooser.showOpenDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            if (!fileChooser.getSelectedFile().getAbsolutePath().endsWith(".ww"))
                return new File(fileChooser.getSelectedFile().getAbsolutePath() + ".ww");
            else return fileChooser.getSelectedFile();
        }
        return null;
    }
}
