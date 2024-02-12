package testmodel;
import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.event.*;



// ca c'est l'observable

public class DrawingAppModel {

    private final List<Segment> editedSegments = new ArrayList<Segment>();
    private Color currentColor = new Color(0,0,0);
    private Segment currentSegment = null;
    private Segment selectedSegment = null;
    private boolean modified= false;

    private final List<ChangeListener> listeners = new ArrayList<ChangeListener>();

    //setters

    public final void setCurrentColor (Color currentColor){
        if (this.currentColor !=currentColor){
            this.currentColor=currentColor;
            modified=true;
            stateChanged();
        }
    }
    public void setCurrentSegment(Segment currentSegment) {
		if(this.currentSegment != currentSegment) {
			this.currentSegment = currentSegment;
			modified = true;
			stateChanged();
			}	
	}
    public void setSelectedSegment(Segment selectedSegment) {
		if(this.selectedSegment != selectedSegment) {
			this.selectedSegment = selectedSegment;
			modified = true;
			stateChanged();
			}	
	}
    public void setModified(boolean modified){
        this.modified = modified;
    }

    //getters
    public boolean isModified(){
        return modified;
    }

    public Color getCurrentColor(){
        return currentColor;
    }
    public Segment getCurrentSegment(){
        return currentSegment;
    }
    public Segment getSelectedSegment(){
        return selectedSegment;
    }


    // observateur observable
    //si DrawingAppModel change, il faut prévenir les observateurs
    // unique méthode de l'interface ChangeListener
    // arg = event qui embarque un objet de n'importe quelle classe


    public void addObserver(ChangeListener listener){
        listeners.add(listener);
    }

    public void stateChanged(){
        ChangeEvent evt = new ChangeEvent(this);
        
        for (ChangeListener listener: listeners) {
            listener.stateChanged(evt);
        }
    }
    // state changed doit être appelé à chaque fois que DrawingAppModel change
    
    public final void paintSegments(Graphics g){
        
        for (Segment s : editedSegments) {
            s.paint(g, false, false);
        } 
        if (selectedSegment !=null){
            selectedSegment.paint(g, true, false);
        }
        if(currentSegment!=null) {
            currentSegment.paint(g,false,true);
        }
    }
    public final void removeSelectedSegment() {
        if (this.selectedSegment != null) {
           editedSegments.remove(this.selectedSegment) ;
           this.selectedSegment = null ;
           stateChanged() ;
        }
    }
    public final void initCurrentSegment(int x,int y){
        setCurrentSegment(new Segment(x,y,x,y,currentColor));
    }
    public final void modifyCurrentSegment(int x2, int y2) {
        if (currentSegment!=null){
            float x1 = (float)currentSegment.getX1() ;
            float y1 = (float)currentSegment.getY1();
            setCurrentSegment(new Segment(x1,y1,x2,y2,currentColor)) ;
        }
     }
    public final void registerCurrentSegment(int x2, int y2) {
        if (currentSegment!=null){
            float x1 = (float)currentSegment.getX1() ;
            float y1 = (float)currentSegment.getY1();
        
            if (Math.abs(x1 - (float)x2) >= 1.0f || Math.abs(y1 - (float)y2) >= 1.0f) {
                currentSegment = null ;
                editedSegments.add(new Segment(x1,y1,x2,y2,currentColor)) ;
                stateChanged() ;
            }
        }
    }
    public final void cancelCurrentSegment() {
        setCurrentSegment(null) ;
    }
    public final void setSelection(int x, int y) {
        double xd = (double)x ;
        double yd = (double)y ;
             
        for (Segment s : editedSegments) {
           if (s.ptLineDist(xd,yd) < 1.0) {
              setSelectedSegment(s) ;
              return ;
           }
        }             
        setSelectedSegment(null) ;
    }
    public void saveToFile(){
        // TODO Auto-generated method stub
    }
}
