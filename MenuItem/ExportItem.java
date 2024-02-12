package MenuItem;
import java.awt.event.*;
import javax.swing.*;

import maze.Maze;
import ui.*;



public class ExportItem extends JMenuItem implements ActionListener {
    
    private final MazeApp mazeApp;

    
        public ExportItem(MazeApp mazeApp) {
            super("Exporter le labyrinthe");
            this.mazeApp =mazeApp;
            addActionListener(this);
        }
        //lorsqu'on on appuit sur le bouton, on enregistre le Labyrinthe dans un fichier texte avec la fonction saveToTextFile
        /**
         * @param chooser : fenêtre de dialogue pour choisir le fichier
         * @param fileName : nom du fichier enregistré
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser(); 
            if (chooser.showSaveDialog(this )== JFileChooser.APPROVE_OPTION){
                String fileName = chooser.getSelectedFile().getAbsolutePath();
                Maze maze = mazeApp.getMazeAppModel().getMaze();
                maze.saveToTextFile(fileName);
                

        }
    
        }
}
