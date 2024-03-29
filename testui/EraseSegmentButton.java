package testui;
import javax.swing.*;

import testmodel.*;

import java.awt.event.*;

//import java.awt.event.*;

public class EraseSegmentButton extends JButton implements ActionListener {

    private final DrawingApp drawingApp;

    public EraseSegmentButton( DrawingApp drawingApp) {
        super("Erase segment"); //Button's text
        this.drawingApp = drawingApp;
        addActionListener(this);

    }
    public void notifyForUpdate(){
        Segment selected=drawingApp.getDrawingAppModel().getSelectedSegment();
        setEnabled(selected!=null);

    }
    public final void actionPerformed(ActionEvent evt) {
          // Operation delegated to the model
      drawingApp.getDrawingAppModel().removeSelectedSegment() ;
    }

}
