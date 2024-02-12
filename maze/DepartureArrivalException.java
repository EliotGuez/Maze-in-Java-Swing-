package maze;

public class DepartureArrivalException extends Exception {
	public DepartureArrivalException(String message) {
		super("Erreur: il n'y a pas de "+ message);
	}
}
