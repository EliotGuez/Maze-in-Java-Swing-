import maze.*;
import graph.*;
import java.util.List;

public class MainTest {
    public static void main(String[] args) throws MazeReadingException, DepartureArrivalException, DepartureArrivalException2, NoSolutionException
    {
        
        Maze maze = new Maze(0,0);
        
        try {
            maze.initFromTextFile("D:\\NainA\\inf103\\guez-eliot\\src\\data\\labyrinthe");

            Vertex departure = maze.getDeparture(); 
            Vertex arrival = maze.getArrival();
            ShortestPaths shortest= Dijkstra.dijkstra(maze, departure,arrival);
            List<Vertex> chemin = shortest.getShortestPaths(arrival);
            //System.out.println(chemin);
            maze.saveSoluce("D:\\NainA\\inf103\\guez-eliot\\src\\data\\labyrinthe2", chemin);

        } catch (MazeReadingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
    }

}
