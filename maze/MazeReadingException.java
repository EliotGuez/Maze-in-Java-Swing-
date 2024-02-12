package maze;

public class MazeReadingException extends Exception {
    public MazeReadingException (String fileName, int numL, String ErrorMsg ) {
            super(" Erreur à la lecture du labyrinthe" + fileName + "("+ numL +")"+ ErrorMsg);

    }
    
}
