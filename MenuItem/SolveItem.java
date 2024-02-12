package MenuItem;
import ui.MazeApp;

import javax.swing.*;
import java.awt.event.*;
import maze.*;

public class SolveItem extends JMenuItem implements ActionListener{
    private final MazeApp mazeApp;

    public SolveItem(MazeApp mazeApp) {
        super("Résoudre le labyrinthe"); 
        this.mazeApp = mazeApp;
        addActionListener(this);
    }

    public void notifyForUpdate(){
        repaint();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            mazeApp.getMazeAppModel().Solve();
        } catch ( DepartureArrivalException | DepartureArrivalException2 evt) {
            JOptionPane.showMessageDialog(null, "Erreur: il n'y a pas le bon nombre de case départ ou arrivée");
        } catch (NoSolutionException evt) {
            JOptionPane.showMessageDialog(null, "Erreur: il n'y a pas de solution");
        }
        
    }

}
