package MenuItem;

import modelihm.*;
import ui.MazeApp;

import javax.swing.*;
import java.awt.event.*;

public class MazeDimensionItem extends JMenuItem implements ActionListener{
    
    private final MazeApp mazeApp ;

    public MazeDimensionItem(MazeApp mazeApp)
    {   
        super("Créer un nouveau labyrinthe") ; // Text of menu item
        this.mazeApp = mazeApp;
        addActionListener(this);
    }

    /**
     * @param evt : evenement
     * @param w : largeur du labyrinthe
     * @param l : longueur du labyrinthe
     * @return dans le mazeApp, la taille du labyrinthe est modifiée
     */

    // on pourrait faire un case pour traiter les erreurs de saisie
    public void actionPerformed(ActionEvent evt) {
       
        MazeAppModel mazeAppModel =   mazeApp.getMazeAppModel();
        String w = JOptionPane.showInputDialog("largeur du labyrinthe");//demande la largeur du labyrinthe
        String l = JOptionPane.showInputDialog("longueur du labyrinthe");//demande la longueur du labyrinthe
        int width = Integer.parseInt(w); 
        int length = Integer.parseInt(l);
        mazeAppModel.newMaze(width, length);
        
    }
    
}
