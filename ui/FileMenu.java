package ui;
import javax.swing.*;

import MenuItem.*;

//le menu ne declenche pas d'action, c'est les items du menu qui le font. Donc pas besoin de connaitre la fenetre. 
//on garde les refs sur les sous composants

public class FileMenu extends JMenu {

   private final QuitMenuItem quitMenuItem ;
   private final MazeDimensionItem mazeDimensionItem;
   private final ExportItem exportItem;
   private final ImportMenuItem importMenuItem;
   private final SolveItem solveItem;
   private final RandomMazeItem randomMazeItem;

   public FileMenu(MazeApp mazeApp) {
      super("File") ;        
      add(quitMenuItem = new QuitMenuItem(mazeApp)) ;// Create and add menu items
      add(mazeDimensionItem = new MazeDimensionItem(mazeApp));
      add(exportItem = new ExportItem(mazeApp));
      add(importMenuItem = new ImportMenuItem(mazeApp));
      add(solveItem = new SolveItem(mazeApp));
      add(randomMazeItem = new RandomMazeItem(mazeApp));
   }
}
