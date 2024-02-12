package graph;
import java.util.HashSet;

public final class ProcessedVertexesImpl extends HashSet<Vertex> implements ProcessedVertexes {
    //constructeur 
    public ProcessedVertexesImpl() {
        super();
    }
    /**
     * ajoute un vertex au set
     */
    public final void ajoute(Vertex vertex) {
        add(vertex) ;
    }
    /**
     * vérifie si le vertex est dans le set
     */
    public final boolean contient(Vertex vertex){
        return contains(vertex);
    }
}
