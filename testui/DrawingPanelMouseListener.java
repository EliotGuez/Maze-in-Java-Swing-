package testui;

import java.awt.event.*;

public class DrawingPanelMouseListener extends MouseAdapter {
    DrawingApp drawingApp;

    public DrawingPanelMouseListener(DrawingApp drawingApp) {
		super();
		this.drawingApp = drawingApp ;
	}

    public final void mousePressed(MouseEvent e){
        drawingApp.getDrawingAppModel().initCurrentSegment(e.getX(),e.getY());
    }
    
    public final void mouseDragged(MouseEvent e) {
        drawingApp.getDrawingAppModel().modifyCurrentSegment(e.getX(),e.getY()) ;
    }
    
    public final void mouseReleased(MouseEvent e) {
        drawingApp.getDrawingAppModel().registerCurrentSegment(e.getX(),e.getY()) ;
    }
    
	public final void mouseExited(MouseEvent e) {
		drawingApp.getDrawingAppModel().cancelCurrentSegment();
	}
	
	public final void mouseClicked(MouseEvent e) {
		drawingApp.getDrawingAppModel().setSelection(e.getX(),e.getY());
	}

}
