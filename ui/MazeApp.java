package ui;
import javax.swing.*;
import javax.swing.event.*;

import modelihm.*;

//fenetre principal du labyrinthe 
//c'est elle qui contient les autres composants
public class MazeApp extends JFrame implements ChangeListener{

    public final MazeMenuBar mazeMenuBar;
    public final WindowPanel windowPanel;
    private MazeAppModel mazeAppModel= new MazeAppModel();

    public MazeApp(){
        super("Labyrinthe"); 
        setJMenuBar( mazeMenuBar = new MazeMenuBar(this));  
        setContentPane( windowPanel = new WindowPanel(this));  
        mazeAppModel.addObserver(this); 

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        this.setLocationRelativeTo(null);
        setVisible(true);  // Window display

    }
    
    public final MazeAppModel getMazeAppModel() {
        return mazeAppModel;
    }

    public final  void setDrawingAppModel(final MazeAppModel mazeAppModel){
        this.mazeAppModel= mazeAppModel;
    }

    public final void stateChanged(final ChangeEvent evt){
        windowPanel.notifyForUpdate();
        
    }
    public final JPanel getMazePanel(){return windowPanel.getMazePanel();}
}
