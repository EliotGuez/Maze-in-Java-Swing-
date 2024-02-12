package graph;


import java.util.List;

public interface ShortestPaths {
	public List<Vertex> getShortestPaths(Vertex i); //affiche la distance la plus courte entre deux points
	public Vertex previous(Vertex k); // regarde le chemin le plus court précédent  
	public void setPereFils(Vertex fils, Vertex pere);

}
