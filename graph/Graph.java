package graph;


import java.util.List;

public interface Graph extends Distance {
	public List<Vertex> getAllVertexes();
	public List<Vertex> succVertex(Vertex k); 
	public int getPoids(Vertex source, Vertex puit);
}
