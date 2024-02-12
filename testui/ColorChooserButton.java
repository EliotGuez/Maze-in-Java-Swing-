package testui;
import javax.swing.*;

import testmodel.*;

import java.awt.*;
import java.awt.event.*;


public class ColorChooserButton extends JButton implements ActionListener {

    private final DrawingApp drawingApp;

    public ColorChooserButton(DrawingApp drawingApp) {
        super("Choose color"); 
        this.drawingApp =drawingApp;
        addActionListener(this);
    }
    public void actionPerformed(ActionEvent evt) {
        // Used twice
        DrawingAppModel drawingAppModel = drawingApp.getDrawingAppModel() ;
  
        // Show JColorChooser dialog		
        Color newColor = JColorChooser.showDialog(drawingApp,"Change segment color", drawingAppModel.getCurrentColor()) ;
        // Changing color triggers refresh	
        if (newColor != null)
           drawingAppModel.setCurrentColor(newColor) ;
    }
}
