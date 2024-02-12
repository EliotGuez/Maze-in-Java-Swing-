package Button;

import ui.MazeApp;
import ui.*;
import javax.swing.*;
import java.awt.event.*;
import maze.*;


public class WallButton extends JButton implements ActionListener {
    private final MazeApp  mazeApp;

    public WallButton(MazeApp mazeApp) {
        super("Mur");
        this.mazeApp = mazeApp;
        addActionListener(this);
    }
    public final void notifyForUpdate(){
        MazeBox type =mazeApp.getMazeAppModel().getType();
        setEnabled(type.getLabel()!="W");
    }

    public final void actionPerformed(ActionEvent evt) {
        mazeApp.getMazeAppModel().setType(new WallBox(0,0));
    }
    
}
