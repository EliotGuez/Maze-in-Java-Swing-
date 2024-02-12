package graph;
import java.lang.Integer;
import java.util.HashMap;

public class MinDistanceImpl extends HashMap< Vertex, Integer> implements MinDistance {
    
    public MinDistanceImpl(){
        super();
    }
    /**
     *définit la relation entre deux vertex
     */
    public final void Distance(Vertex vertex, int dist){
        put(vertex, dist);

    } 
    /**
     * associe un vertex à une distance
     */
	public final int getPere(Vertex i){
        return get(i);
    }  
}
