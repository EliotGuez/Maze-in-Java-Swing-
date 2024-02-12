package Button;

import ui.MazeApp;
import ui.*;
import javax.swing.*;
import java.awt.event.*;
import maze.*;

public class EmptyBoxButton extends JButton implements ActionListener {
    private final MazeApp  mazeApp;

    public EmptyBoxButton(MazeApp mazeApp) {
        super("Case vide");
        this.mazeApp = mazeApp;
        addActionListener(this);
    }
    public final void notifyForUpdate(){
        MazeBox type =mazeApp.getMazeAppModel().getType();
        setEnabled(type.getLabel()!="E");
    }

    public final void actionPerformed(ActionEvent evt) {
        mazeApp.getMazeAppModel().setType(new EmptyBox(0,0));
    }
}