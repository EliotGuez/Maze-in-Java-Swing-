package MenuItem ;
import modelihm.*;
import ui.MazeApp;

import javax.swing.*;
import java.awt.event.*;

public class QuitMenuItem extends JMenuItem implements ActionListener  {

    private final MazeApp mazeApp ;

    public QuitMenuItem(MazeApp mazeApp) {
        super("Quitter") ; // Text of menu item

        this.mazeApp = mazeApp ;
        addActionListener(this);
    }

    /**
     * Action performed when the menu item is selected
     */
    public void actionPerformed(ActionEvent evt) {
        MazeAppModel mazeAppModel =   mazeApp.getMazeAppModel();
        if (mazeAppModel.isModified()) {
            int response = JOptionPane.showInternalOptionDialog(this, "Drawing not saved. Save it ?","Quit application",
                                                                JOptionPane.YES_NO_CANCEL_OPTION,
                                                                JOptionPane.WARNING_MESSAGE,
                                                            null,null,null) ;
		    switch (response) {
		    case JOptionPane.CANCEL_OPTION: return ;
		    case JOptionPane.OK_OPTION: mazeAppModel.saveToFile(); break ;
		    case JOptionPane.NO_OPTION:
		       break ;
		    }
	    }
	    System.exit(0) ;
    }
}
