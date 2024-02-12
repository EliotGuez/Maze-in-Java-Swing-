package ui;   
import java.awt.*;
import javax.swing.* ;

import Button.ArrivalBoxButton;
import Button.*;

public class ButtonsPanel extends JPanel {
	
   private final ArrivalBoxButton ArrivalBoxButton;
   private final EmptyBoxButton EmptyBoxButton;
   private final DepartureBoxButton DepartureBoxButton;
   private final WallButton WallButton;
	
   public ButtonsPanel(MazeApp mazeApp) {
      

      setLayout(new GridLayout(1,3)) ; // 1 row, 3 columns
      add(ArrivalBoxButton= new ArrivalBoxButton(mazeApp)) ;
      add(EmptyBoxButton= new EmptyBoxButton(mazeApp)) ;
      add(DepartureBoxButton= new DepartureBoxButton(mazeApp)) ;
      add(WallButton =new WallButton(mazeApp));
   }
   /**
    * Notifie les boutons pour qu'ils se mettent à jour
    */
   public final void notifyForUpdate(){
      ArrivalBoxButton.notifyForUpdate();
      EmptyBoxButton.notifyForUpdate();
      DepartureBoxButton.notifyForUpdate();
      WallButton.notifyForUpdate();

   }
}