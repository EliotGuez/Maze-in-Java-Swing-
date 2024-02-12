package testui;
import javax.swing.*;

//le menu ne declenche pas d'action, c'est les items du menu qui le font. Donc pas besoin de connaitre la fenetre. 
//on garde les refs sur les sous composants

public class FileMenu extends JMenu {

   private final QuitMenuItem quitMenuItem ;

   public FileMenu(DrawingApp drawingApp) {
      super("File") ;        
      add(quitMenuItem = new QuitMenuItem(drawingApp)) ;// Create and add menu items
   }
}