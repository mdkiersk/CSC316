package edu.ncsu.csc316.dsa.graph;

/**
 * Represents the interface for all Graph implementations
 * @author Matthew Kierski
 *
 * @param <V> vertex of graph
 * @param <E> edge of graph
 */
public interface Graph<V, E> {
	
	/**
	 * Returns true if graph is directed, false otherwise
	 * @return true if graph is directed, false otherwise
	 */
	boolean isDirected();

	/**
	 * Returns the number of vertices in a graph
	 * @return the number of vertices in a graph
	 */
	int numVertices();

	/**
	 * Returns an iterator over the vertices in the graph
	 * @return an iterator over the vertices in the graph
	 */
	Iterable<Vertex<V>> vertices();

	/**
	 * Returns the number of edges
	 * @return the number of edges
	 */
	int numEdges();

	/**
	 * Returns an iterator over the edges in a graph
	 * @return an iterator over the edges in a graph
	 */
	Iterable<Edge<E>> edges();

	/**
	 * Returns the edge incident of the two given vertices
	 * @param vertex1 vertex to evaluate
	 * @param vertex2 vertex to evaluate
	 * @return the edge incident of the two given vertices
	 */
	Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2);

	/**
	 * Returns the vertices incident of the given edge
	 * @param edge edge to evaluate
	 * @return the vertices incident of the given edge
	 */
	Vertex<V>[] endVertices(Edge<E> edge);

	/**
	 * Returns the opposite vertex of the given vertex on a given edge
	 * @param vertex vertex to evaluate
	 * @param edge edge to evaluate
	 * @return the opposite vertex
	 */
	Vertex<V> opposite(Vertex<V> vertex, Edge<E> edge);

	/**
	 * Returns the number of edges going out from vertex
	 * @param vertex vertex to evaluate
	 * @return the number of edges going out from vertex
	 */
	int outDegree(Vertex<V> vertex);

	/**
	 * Returns the number of edges coming in to vertex
	 * @param vertex vertex to evaluate
	 * @return the number of edges coming into vertex
	 */
	int inDegree(Vertex<V> vertex);

	/**
	 * Returns an iterator of the outgoing edges of a given vertex
	 * @param vertex vertex to evaluate
	 * @return an iterator of the outgoing edges of a given vertex
	 */
	Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex);

	/**
	 * Returns an iterator of the incoming edges of a given vertex
	 * @param vertex vertex to evaluate
	 * @return an iterator of the incoming edges of a given vertex
	 */
	Iterable<Edge<E>> incomingEdges(Vertex<V> vertex);

	/**
	 * Inserts a new vertex into the graph
	 * @param vertexData data of new vertex
	 * @return the new vertex
	 */
	Vertex<V> insertVertex(V vertexData);

	/**
	 * Inserts a new edge between to vertices in the graph
	 * @param v1 vertex to add between
	 * @param v2 vertex to add between
	 * @param edgeData weight of edge
	 * @return the new edge
	 */
	Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeData);

	/**
	 * Removes and returns a vertex from the graph
	 * @param vertex vertex to remove
	 * @return the removed vertex
	 */
	Vertex<V> removeVertex(Vertex<V> vertex);

	/**
	 * Removes and returns the given edge from a graph
	 * @param edge edge to remove
	 * @return the removed edge
	 */
	Edge<E> removeEdge(Edge<E> edge);

	/**
	 * Represents the interface for all graph edges
	 * @author Matthew Kierski
	 *
	 * @param <E> edge of edge
	 */
	interface Edge<E> {
		/**
		 * Returns the element of this edge
		 * @return the element of this edge
		 */
		E getElement();
	}

	/**
	 * Represents the interface for all vertices of a graph
	 * @author Matthew Kierski
	 *
	 * @param <V> vertex of graph
	 */
	interface Vertex<V> {
		/**
		 * Returns the element of this vertex
		 * @return the element of this vertex
		 */
		V getElement();
	}
}