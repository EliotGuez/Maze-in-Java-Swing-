package maze;

public class NoSolutionException extends Exception {
    public NoSolutionException() {
        super("Erreur: il n'y a pas de solution");
    }
    
}
