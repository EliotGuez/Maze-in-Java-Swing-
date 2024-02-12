package testui;
import javax.swing.* ;

//De la même manière, la barre de menu ne déclenche aucune action. 
//Il n'est donc pas nécessaire qu'il connaisse la fenêtre. Et l'on garde des références sur les sous-composants.
        
public class DrawingMenuBar extends JMenuBar {

    private final FileMenu fileMenu ;

    public DrawingMenuBar(DrawingApp drawingApp){
        super();
        add(fileMenu = new FileMenu(drawingApp));
    }

}