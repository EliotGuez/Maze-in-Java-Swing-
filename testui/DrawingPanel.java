package testui;

import javax.swing.* ;
import java.awt.* ;


public class DrawingPanel extends JPanel {

   private final DrawingApp drawingApp ;
	
   public DrawingPanel(DrawingApp drawingApp) {

      this.drawingApp = drawingApp ;
		
      setBackground(Color.WHITE) ;
      //for pack() instruction
      setPreferredSize(new Dimension(256,256)) ; 
       
      DrawingPanelMouseListener drawingPanelMouseListener= new DrawingPanelMouseListener(drawingApp) ;
      addMouseListener (drawingPanelMouseListener) ;
      addMouseMotionListener (drawingPanelMouseListener) ;

   }
   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      drawingApp.getDrawingAppModel().paintSegments(g);
   }
   public void notifyForUpdate(){
      repaint();
   }
   
		

}