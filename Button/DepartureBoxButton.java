package Button;

import ui.MazeApp;
import ui.*;
import javax.swing.*;
import java.awt.event.*;
import maze.*;

public class DepartureBoxButton extends JButton implements ActionListener {
    private final MazeApp  mazeApp;

    public DepartureBoxButton(MazeApp mazeApp) {
        super("Départ");
        this.mazeApp = mazeApp;
        addActionListener(this);
    }
    public final void notifyForUpdate(){
        MazeBox type =mazeApp.getMazeAppModel().getType();
        setEnabled(type.getLabel()!="D");
    }

    public final void actionPerformed(ActionEvent evt) {
        mazeApp.getMazeAppModel().setType(new DepartureBox(0,0));
    }

}
