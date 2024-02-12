package Button;

import ui.MazeApp;  
import ui.*;
import javax.swing.*;
import java.awt.event.*;
import maze.*;

public class ArrivalBoxButton extends JButton implements ActionListener {
    private final MazeApp  mazeApp;

    public ArrivalBoxButton(MazeApp mazeApp) {
        super("Arrivée");
        this.mazeApp = mazeApp;
        addActionListener(this);
    }
    public final void notifyForUpdate(){
        MazeBox type =mazeApp.getMazeAppModel().getType();
        setEnabled(type.getLabel()!="A");
    }

    public final void actionPerformed(ActionEvent evt) {
        mazeApp.getMazeAppModel().setType(new ArrivalBox(0,0));
        
    }
}

