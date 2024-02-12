package ui;
import javax.swing.* ;

import java.awt.* ;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.awt.event.MouseAdapter;


import modelihm.*;
import maze.*;



public class MazePanel extends JPanel {

    private final MazeApp mazeApp ;
    
    // à changer pour varier en fonction de taille de l'app
    private int originX = 100;
    private int originY = 100;
    private int height =500;
    private int width =500;
    private int size = 20;
    private int largeur = 0;
    private int longueur = 0;

    
    double sqrt3 = Math.sqrt(3);
    //caractéristiques générales
	
    public MazePanel(MazeApp mazeApp) {

        this.mazeApp = mazeApp ;
     
        setBackground(Color.WHITE) ;
        setPreferredSize(new Dimension(width,height)) ; 
        addMouseListener(new MouseAdapter() {
            /**
             * quand la souris clique la case touché prend le type de l'attribut type du mazeAppModel
             * @param e : evenement
             */
            @Override
            public final void mousePressed(final MouseEvent e) {  
                int x = e.getX() - originX;
                int y = e.getY() - originY;
                

                
                if (x < 0 || y < 0 || x > (2*largeur+1)*sqrt3*size || y > 3*size*longueur) return;
                
                int hexY = (int) Math.floor((y - size/2)/(size*3));
                int hexX = (int) Math.floor((x - sqrt3*size*(hexY%2))/(sqrt3*2*size));
                
                if (hexX < 0  || hexX >= largeur || hexY < 0 || hexY >=  longueur ) return;
                MazeBox NBox = mazeApp.getMazeAppModel().getType();
                NBox.setX(hexX);
                NBox.setY(hexY);
                mazeApp.getMazeAppModel().changeMaze(hexY*largeur+hexX, NBox);
            }});
        
        addMouseMotionListener(new MouseAdapter() {    
            /**
             * toutes les cases du labyrinthe touchées par la souris sont modifiées
             * @param e : evenement
             */
            public final void mouseDragged(final MouseEvent e) {
                ArrayList<int[]> tab = new ArrayList<int[]>(); 
            
                int x = e.getX() - originX;
                int y = e.getY() - originY;                
        
                if (x < 0 || y < 0 || x >= (2*largeur+1)*sqrt3*size|| y >= 3*size*longueur) return;
                
                int hexY = (int) Math.floor((y - size/2)/(size*3));
                int hexX = (int) Math.floor((x - sqrt3*size*(hexY%2))/(sqrt3*2*size));
                if (hexX < 0  || hexX >largeur || hexY < 0 || hexY >  longueur) return;
                for (int j=0; j<longueur; j++){
                    for (int i=0; i<largeur; i++){
                        int[] g= {hexX,hexY};
                        if (!tab.contains(g))
                            tab.add(g);
                        if (tab.contains(g)){
                            MazeBox NBOX = mazeApp.getMazeAppModel().getType();
                            NBOX.setX(hexX);
                            NBOX.setY(hexY);
                            mazeApp.getMazeAppModel().changeMaze(g[1]*largeur+g[0], NBOX);
                        }
                    }
                }
            }
        });
    }

    /**
     * Dessine le labyrinthe dans le panel en dessinant chaque case une par une
     */
    @Override
    public final void paintComponent(final Graphics g) {
        this.height = getHeight();
        this.width = getWidth();

        
        MazeAppModel mazeAppModel = mazeApp.getMazeAppModel() ;
        Maze maze= mazeAppModel.getMaze(); 

        this.largeur = maze.getWidth();
        this.longueur = maze.getLength();
        size = (int) Math.min( 0.8*height/(longueur*3), 0.8*width/(2*sqrt3*largeur));
        originX = (int) (width/2-sqrt3*size*largeur);
        originY = (int) ((height-3*size*longueur)/2);


        super.paintComponent(g);     
        for (int y = 0; y < maze.getLength(); y++) {
            for (int x = 0; x < maze.getWidth(); x++) {
                MazeBox mazeBox=maze.getMazeBox(x,y);
                int abs = originX + (int)(2*size*sqrt3*x + (y%2)*sqrt3*size);
                int ord = originY + y*3*size;
                mazeBox.drawHex(g, abs, ord, size
                );                
            }
        }
    }
    public void notifyForUpdate(){  
        repaint();
    }

    
}
