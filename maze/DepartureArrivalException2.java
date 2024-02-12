package maze;

public class DepartureArrivalException2 extends Exception{
    public DepartureArrivalException2(String message) {
        super("Erreur: il y a plusieurs "+ message);
    }    
}
