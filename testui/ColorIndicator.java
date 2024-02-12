package testui;
import javax.swing.*;
import java.awt.*;

public class ColorIndicator extends JPanel {

    private final DrawingApp drawingApp ;
	
    public ColorIndicator(DrawingApp drawingApp) {

       this.drawingApp = drawingApp ;    
         
       setPreferredSize(new Dimension(100,100)) ;

    }

    @Override
    protected final void paintComponent( Graphics g) { 
      //peint le background
        super.paintComponent(g);

        int width=getWidth();
        int height = getHeight();

        g.setColor(drawingApp.getDrawingAppModel().getCurrentColor());
        g.fillRoundRect(4,4,width-8,height-8,15,15);

        g.setColor(Color.BLACK);
        g.drawRoundRect(4,4,width-8,height-8,15,15);
        
   }
   public void notifyForUpdate(){
      repaint(); 
   }
   
}
    
