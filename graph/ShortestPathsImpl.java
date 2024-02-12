package graph;
import java.util.HashMap;
import java.util.ArrayList;

public class ShortestPathsImpl extends HashMap<Vertex,Vertex> implements ShortestPaths{
    
    public ShortestPathsImpl(){
        super();
    }

    public final Vertex previous(Vertex pere){
        return get(pere); // on recupere la donnée sur le père 
    }
    
    public final void setPereFils(Vertex pere, Vertex fils) {
		put(fils, pere);  // on définit la relation pere fils è
	}

    /**
     * @param pere : le sommet dont on veut connaitre le chemin au point de départ
     * retourne le chemin le plus court entre le point de départ et le pere
     */
    public final ArrayList<Vertex> getShortestPaths(Vertex pere){
        ArrayList<Vertex> chemin = new ArrayList<Vertex>();
        while(pere!=null){
            chemin.add(pere);
            //System.out.println(pere);
            pere = previous(pere);
        }
        return chemin; 

    }
    //pour tout sommet succVertex successeur de pivotVertex et non inclu dans processedVertexes alors 
    //Si minDistance(pivotvertex) + distance(pivot vertex, succvertex )<minDistance (succvertex) alors ...
}
