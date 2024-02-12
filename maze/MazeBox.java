package maze;

import graph.Vertex;
import java.awt.Color;
import java.awt.*;
import java.awt.Polygon;

public abstract class MazeBox extends Polygon implements Vertex{
    private int x; //absisse de la boite
    private int y; //ordonnée de la boite
    private String label;
    private final boolean passage;
    private  Color color;


    public MazeBox(final int x, final int y, final String label, final boolean passage, final Color color){ // une boite est désigné par sa ligne et sa colonne
        this.x=x;
        this.y=y;
        this.label=label;
        this.passage=passage;
        this.color=color;
    }
    public final void setLabel(final String label){
        this.label=label;
    }
    public final String getLabel(){
        return label;
    }
    public final void setX(final int x){
        this.x=x;
    }
    public final int getX(){
        return x; }     

    public final void setY(final int y){
        this.y=y;
    }
    public final int getY(){
        return y;}
        
    public final boolean getPassage(){
        return passage;
    }

    @Override
    public final String toString() {
        return Integer.toString(x)+ ":" + Integer.toString(y)+ label ;
    }
    public final Color getColor(){
        return color;
    }
    public final void setColor(final Color color){
        this.color=color;
    }

    /**
     * dessine un hexagone
     * @param graphics : contexte graphique
     * @param ox : abscisse du point le plus à droite de l'hexagone
     * @param oy : ordonnée du point le plus en bas de l'hexagone
     * @param size : taille de l'hexagone
     * @return: un polygone dessiné
     */

    public final void drawHex(final Graphics graphics, final int ox, final int oy, final int size) {
 
        //coordonnées des sommets de l'hexagone
        Graphics2D g =(Graphics2D) graphics;
        double sqrt3 = Math.sqrt(3);

        int[] hexXPoints = new int[6];
        int[] hexYPoints = new int[6];
        hexXPoints[0] = (int)(ox + sqrt3*size);
        hexXPoints[1] = (int)(ox + 2*sqrt3*size);
        hexXPoints[2] = (int)(ox + 2*sqrt3*size);
        hexXPoints[3] = (int)(ox + sqrt3*size);
        hexXPoints[4] = ox;
        hexXPoints[5] = ox;

        hexYPoints[0] = oy;
        hexYPoints[1] = oy+size;
        hexYPoints[2] = oy+3*size;
        hexYPoints[3] = oy+4*size;
        hexYPoints[4] = oy+3*size;
        hexYPoints[5] = oy+size;

        g.setStroke(new BasicStroke((float) 2.5));
        g.setColor(color);
        g.fillPolygon(hexXPoints, hexYPoints, 6);
        g.setColor(Color.BLACK);
        g.drawPolygon(hexXPoints, hexYPoints, 6);
    } 

}
