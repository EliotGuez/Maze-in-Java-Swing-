package MenuItem;
import java.awt.event.*;
import javax.swing.*;

import ui.*;
import maze.*;

public class ImportMenuItem extends JMenuItem implements ActionListener {
    
    private final MazeApp mazeApp;

    
        public ImportMenuItem(MazeApp mazeApp) {
            super("Importer un labyrinthe");
            this.mazeApp =mazeApp;
            addActionListener(this);
        }
        /**
         * @param chooser : fichier selectionné
         * @param fileName : chemin du fichier
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser(); 
            if (chooser.showOpenDialog(null)== JFileChooser.APPROVE_OPTION){
                String fileName = chooser.getSelectedFile().getAbsolutePath(); 
                
                try{mazeApp.getMazeAppModel().importMaze(fileName);
                }catch(MazeReadingException f){
                    f.printStackTrace();
                }
            }    
        }
    
}
