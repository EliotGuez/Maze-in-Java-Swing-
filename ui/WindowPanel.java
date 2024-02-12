package ui;
import java.awt.*;
import javax.swing.* ;

public class WindowPanel  extends JPanel{

    private final MazePanel mazePanel;
    private final ButtonsPanel buttonsPanel ;

    
    
    public WindowPanel(MazeApp mazeApp){

        setLayout(new BorderLayout()) ;
		
        add( mazePanel = new MazePanel (mazeApp), BorderLayout.CENTER) ;
        add(buttonsPanel = new ButtonsPanel(mazeApp), BorderLayout.SOUTH) ;
    }     

    public final void notifyForUpdate(){
        mazePanel.notifyForUpdate();
        buttonsPanel.notifyForUpdate();   
    }
    public final JPanel getMazePanel(){
       return mazePanel;
    }
}
