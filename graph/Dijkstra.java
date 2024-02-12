package graph;
import java.util.List;
import maze.*;

import java.lang.Integer;



public class Dijkstra {

    /**
     * 
     * @param graph : graphe sur lequel on applique l'algorithme
     * @param startVertex : sommet de départ
     * @param endVertex : sommet d'arrivée
     * @return le plus court chemin entre startVertex et endVertex
     * @throws NoSolutionException si il n'y a pas de solution
     */

    public static ShortestPaths dijkstra ( Graph graph, Vertex startVertex, Vertex endVertex) throws NoSolutionException 
    {
        ProcessedVertexesImpl Chemin = new ProcessedVertexesImpl();
        ShortestPathsImpl shortestPaths = new ShortestPathsImpl();
        Vertex pivot = startVertex;
        MinDistanceImpl distance= new MinDistanceImpl();
        
        List<Vertex> allVertexes= graph.getAllVertexes();
        shortestPaths.setPereFils(startVertex,null);
        
        Chemin.ajoute(startVertex);

        distance.Distance(startVertex,0);

        for (Vertex vertex: allVertexes) { 
            if (vertex!= pivot){ 
                distance.Distance(vertex, (Integer.MAX_VALUE));
            }
        }
        
        while (!Chemin.contains(endVertex) && pivot!=null){
            for (Vertex vertex: graph.succVertex(pivot)){
                if (!Chemin.contains(vertex)){
                    int val=distance.getPere(pivot)+ graph.getPoids(pivot,vertex);
                    if (val < distance.getPere(vertex)){
                        distance.Distance(vertex,val);
                        shortestPaths.setPereFils(pivot, vertex);                        
                    }                  
                }
            }

            int infDist= Integer.MAX_VALUE;
            Vertex nextVertex= null;
            distance.Distance(nextVertex, infDist);
            for (Vertex vertex : allVertexes) {
                if (!Chemin.contains(vertex) && distance.getPere(vertex)<distance.getPere(nextVertex)) {
                    nextVertex=vertex;
                }
            }
            pivot =nextVertex;
            //System.out.println(pivot.toString()+ " " + nextVertex.toString());
            //System.out.println(pivot);
            Chemin.ajoute(pivot);
            //System.out.println(Chemin);
            
        }
        if (!Chemin.contains(endVertex)) {throw new NoSolutionException();}

        
        return shortestPaths ;

    

    }
}
