package modelihm;

import javax.swing.event.*;
import java.util.List;
import java.awt.Color;
import java.util.*;
import maze.*;
import graph.*;




public class MazeAppModel 
{
    private Maze maze = new Maze(5, 5);
    private boolean modified= false;
    private ArrayList<MazeBox> path = new ArrayList<MazeBox>();
    private Maze selection = new Maze(0,0); 
    private MazeBox type = new EmptyBox(0,0);

    private final List<ChangeListener> listeners = new ArrayList<ChangeListener>();

    
    //observer listener
    public final void addObserver(final ChangeListener listener){
        listeners.add(listener);
    }

    public final void stateChanged(){
        ChangeEvent evt = new ChangeEvent(this);
        
        for (ChangeListener listener: listeners) {
            listener.stateChanged(evt);
        }

    }
    
    /////////////////////////////getter setter///////////////////////////////

    public final Maze getMaze() {
        return maze;
    }
    /**
     * Set the maze and notify observers
     * @param maze the new maze
     */
    public final void setMaze(final Maze maze) {
        if(!this.maze.chgmtMaze(maze)){
            this.maze = maze;
            this.modified = true;
            stateChanged();
        }
        
    }
    /**
     * Set the maze and notify observers
     * @param width the width of the new maze
     * @param length the length of the new maze
     * @return a new maze
     */
    public final void newMaze(final int width, final int length) {
        maze = new Maze(width, length);
        modified = true;
        stateChanged();
    }
    public final boolean isModified() {return modified;}
    public final void setModified(final boolean modified) {this.modified = modified;} 

    /**
     * Set the path and notify observers
     * @param path : chemin à afficher
     */
    public final void setPath(final ArrayList<MazeBox> path) {
        if (this.path != path && path != null) {
            this.path= new ArrayList<MazeBox>();
        } else {
            this.path = path;
            modified = true;
            stateChanged();}
        }
    public final ArrayList<MazeBox> getPath() {return path;}

    public final Maze getSelection() {return selection ;}

    public final void setSelection(final Maze selection) {
        if (this.selection != selection) {
            this.selection = selection;
            modified = true;
            stateChanged();}
        }
    
    /**
     * Get the type of the box
     * @return void
     */
    public final MazeBox getType() {
        MazeBox m = new EmptyBox(0,0);
        if (type.getLabel()=="E") {m = new EmptyBox(0,0);}
        if (type.getLabel()=="W") {m = new WallBox(0,0);}
        if (type.getLabel()=="D") {m = new DepartureBox(0,0);}
        if (type.getLabel()=="A") {m = new ArrivalBox(0,0);}
        return m;
    }
    
    public final void setType(final MazeBox type) {this.type = type;}
    


//////////////////////////////////////////////////////////////////

    /**
     * Change the maze and notify observers
     * @param k : indice de la case à modifier
     * @param mazeBox : nouvelle case
     */
    public final void changeMaze(final int k, final MazeBox mazeBox){
        maze.set2(k, mazeBox);
        this.modified = true ;
        stateChanged();
    }
    /**
     * Change the maze and notify observers
     * @param selection
     */
    public final void changeMaze2(final Maze selection){
        for (int y=0; y<maze.getLength(); y++){
            for (int x=0 ; x<maze.getWidth(); x++){
                if (selection.getMazeBox(x,y)!=null){
                    maze.set2(y*maze.getWidth() +x,selection.getMazeBox(x,y));
                    this.modified= true;
                }
            }
        }
        if (modified) {stateChanged();}

    }
    



    //////////////////////fonction qui implémente les fonction de l'interface MazeAppModelInterface

    public final void saveToFile(){
        // TODO Auto-generated method stub
    }

    /**
     *  trouve le chemin le plus court entre le départ et l'arrivée
     * @throws DepartureArrivalException : exception si il n'y a pas de départ ou d'arrivée
     * @throws DepartureArrivalException2 : exception si il y a plusieurs départ ou arrivée
     * @throws NoSolutionException : exception si il n'y a pas de solution
     */
    public final void Solve() throws DepartureArrivalException, DepartureArrivalException2,  NoSolutionException{
        Maze maze = getMaze();
        for (int y=0; y<maze.getLength();y++){
            for (int x=0; x<maze.getWidth(); x++){
                MazeBox box = maze.getMazeBox(x,y);
                if (box.getLabel() == "E") {
                    box.setColor(Color.GRAY);
                } else if (box.getLabel()  == "W") {
                    box.setColor(Color.DARK_GRAY);
                } else if (box.getLabel()  == "D") {
                    box.setColor(Color.GREEN);
                } else if (box.getLabel()  == "A") {
                    box.setColor(Color.RED);
                }
                maze.set2(x+y*maze.getWidth(), box);
            }
        }
        Vertex departure = maze.getDeparture(); 
        Vertex arrival = maze.getArrival();
        ShortestPaths shortest= Dijkstra.dijkstra(maze, departure,arrival);
        List<Vertex> chemin = shortest.getShortestPaths(arrival);
        for (Vertex v: chemin){
            if (chemin.contains(v)){
                MazeBox box = maze.getMazeBox(v.getX(), v.getY());
                box.setColor(Color.MAGENTA);
                if  (box.getLabel()=="A"){
                    box.setColor(Color.RED);
                }
                else if (box.getLabel()=="D"){
                    box.setColor(Color.GREEN);
                } 
                
                maze.set2(v.getY()*maze.getWidth()+v.getX(), box);
                path.add(box);
                
            }
        }
        modified = true;
        stateChanged();
        setPath(path);
    }

    /**
     * importe un labyrinthe depuis un fichier
     * @param fileName : nom du fichier
     * @throws MazeReadingException : exception si le fichier n'est pas au bon format
     */
    public final void importMaze(final String fileName) throws MazeReadingException{
        Maze maze = new Maze(0,0);
        maze.initFromTextFile(fileName);
        setMaze(maze);    
    }



    //////////////////////////////////////////////
    /**
     * génère un labyrinthe aléatoire
     */

    public final void randomMaze(){
        Maze maze = new Maze(getMaze().getWidth(),getMaze().getLength());        
        for (int y=0; y<maze.getLength();y++){
            for (int x=0; x<maze.getWidth(); x++){
                if (Math.random()<0.4){
                    maze.set2(x+y*maze.getWidth(), new WallBox(x,y));
                }                
            }
        }
        int nArrivalX= (int) (Math.random()*maze.getWidth());
        int nArrivalY= (int) (Math.random()*maze.getLength());
        maze.set2(nArrivalY*maze.getWidth()+nArrivalX,new ArrivalBox(nArrivalX, nArrivalY));

        int nDepartureX= (int) (Math.random()*maze.getWidth());
        int nDepartureY= (int) (Math.random()*maze.getLength());
        int nDeparture = nDepartureY*maze.getWidth()+nDepartureX;

        while (nDeparture == nArrivalY*maze.getWidth()+nArrivalX){
            nDepartureX= (int) (Math.random()*maze.getWidth());
            nDepartureY= (int) (Math.random()*maze.getLength());
            nDeparture = nDepartureY*maze.getWidth()+nDepartureX;
        }
        maze.set2(nDepartureY*maze.getWidth()+nDepartureX,new DepartureBox(nDepartureX, nDepartureY));
        setMaze(maze);
        
    }
}
