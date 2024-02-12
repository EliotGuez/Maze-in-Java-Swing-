package testui;
import javax.swing.*;
import javax.swing.event.*;

import testmodel.*;


// ca c'est l'observateur et il doit s'enregistrer auprès de l'observable

public class DrawingApp extends JFrame implements ChangeListener{
    
    public final DrawingMenuBar drawingMenuBar;
    public final WindowPanel windowPanel;
    private DrawingAppModel drawingAppModel= new DrawingAppModel();

    public DrawingApp(){
        super("Labyrinthe"); // Window title
        setJMenuBar( drawingMenuBar = new DrawingMenuBar(this));  // Window menu bar creation
        setContentPane( windowPanel =new WindowPanel(this)); // Window content creation 
        drawingAppModel.addObserver(this);  // info sur l'observateur

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();// Compenents sizes and positions
        setVisible(true);  // the great show

    }
    //getter
    public DrawingAppModel getDrawingAppModel() {
        return drawingAppModel;
    }
    //setter
    public void setDrawingAppModel(DrawingAppModel drawingAppModel){
        this.drawingAppModel= drawingAppModel;
    }

    public void stateChanged(ChangeEvent evt){
        windowPanel.notifyForUpdate();
    }
    
}
//Pour cela nous donnerons au constructeur de chaque composant une référence sur la fenêtre qu'il gardera dans un de ses attributs.