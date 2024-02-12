package MenuItem;
import modelihm.*;
import ui.MazeApp;

import javax.swing.*;
import java.awt.event.*;

public class RandomMazeItem extends JMenuItem implements ActionListener{
    
    private final MazeApp mazeApp ;

    public RandomMazeItem(MazeApp mazeApp)
    {   
        super("Créer un nouveau labyrinthe Aléatoire") ; // Text of menu item
        this.mazeApp = mazeApp;
        addActionListener(this);
    }

    /**
     * on choisit les dimensions et il execute la fonction de MazeAppModel qui crée un labyrinthe aléatoire
     */
    public void actionPerformed(ActionEvent evt) {
        MazeAppModel mazeAppModel =   mazeApp.getMazeAppModel();
        String w = JOptionPane.showInputDialog("largeur du labyrinthe");//demande la largeur du labyrinthe
        String l = JOptionPane.showInputDialog("longueur du labyrinthe");//demande la longueur du labyrinthe
        int width = Integer.parseInt(w); 
        int length = Integer.parseInt(l);
        mazeAppModel.newMaze(width, length);
        mazeAppModel.randomMaze();
    }
        
    
}
