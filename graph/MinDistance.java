package graph;

public interface MinDistance {
	public void Distance(Vertex vertex, int dist);  //associe à un point une distance
	public int getPere(Vertex i); // prends la distance la plus courte à un point
}
