package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * Represents the implementation of an AdjacencyMapGraph
 * @author Matthew Kierski
 *
 * @param <V> vertex of graph
 * @param <E> edge of graph
 */
public class AdjacencyMapGraph<V, E> extends AbstractGraph<V, E> {

	/** List of vertices in this graph */
    private PositionalList<Vertex<V>> vertexList;
    /** List of edges in this graph */
    private PositionalList<Edge<E>> edgeList;
    
    /**
     * Constructs an AdjacencyMapGraph
     */
    public AdjacencyMapGraph() {
        this(false);
    }
    
    /**
     * Constructs an AdjacencyMapGraph
     * @param directed whether or not the graph is directed
     */
    public AdjacencyMapGraph(boolean directed) {
        super(directed);
        vertexList = new PositionalLinkedList<Vertex<V>>();
        edgeList = new PositionalLinkedList<Edge<E>>();
    }
    
    @Override
    public int numVertices() {
        return vertexList.size();
    }

    @Override
    public Iterable<Vertex<V>> vertices() {
        return vertexList;
    }

    @Override
    public int numEdges() {
        return edgeList.size();
    }

    @Override
    public Iterable<Edge<E>> edges() {
        return edgeList;
    }

    @Override
    public Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2) {
        return validate(vertex1).getOutgoing().get(vertex2);
    }

    @Override
    public int outDegree(Vertex<V> vertex) {
        return validate(vertex).getOutgoing().size();
    }

    @Override
    public int inDegree(Vertex<V> vertex) {
        return validate(vertex).getIncoming().size();
    }

    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
        return validate(vertex).getOutgoing().values();
    }

    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
        return validate(vertex).getIncoming().values();
    }

    @Override
    public Vertex<V> insertVertex(V vertexData) {
        AMVertex vertex = new AMVertex(vertexData, isDirected());
        Position<Vertex<V>> pos = vertexList.addLast(vertex);
        vertex.setPosition(pos);
        return vertex;
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeData) {
        AMVertex origin = validate(v1);
		AMVertex destination = validate(v2);
		GraphEdge edge = new GraphEdge(edgeData, origin, destination);
        Position<Edge<E>> pos = edgeList.addLast(edge);
        edge.setPosition(pos);
        if (isDirected()) {
        	origin.getOutgoing().put(destination, edge);
            destination.getIncoming().put(origin, edge);
        } else {
        	origin.getIncoming().put(destination, edge);
        	origin.getOutgoing().put(destination, edge);
        	destination.getIncoming().put(origin, edge);
        	destination.getOutgoing().put(origin, edge);
        }
        return edge;
    }

    @Override
    public Vertex<V> removeVertex(Vertex<V> vertex) {
        AMVertex v = validate(vertex);
        for (Edge<E> e: v.getOutgoing().values()) {
        	removeEdge(e);
        }
        for (Edge<E> e: v.getIncoming().values()) {
        	removeEdge(e);
        }
        return vertexList.remove(v.getPosition());
    }

    @Override
    public Edge<E> removeEdge(Edge<E> edge) {
        GraphEdge e = validate(edge);
        Vertex<V>[] ends = e.getEndpoints();
        AMVertex origin = validate(ends[0]);
        AMVertex dest = validate(ends[1]);
        if (isDirected()) {
        	origin.getOutgoing().remove(dest);
            dest.getIncoming().remove(origin);
        } else {
        	origin.getOutgoing().remove(dest);
        	origin.getIncoming().remove(dest);
            dest.getOutgoing().remove(origin);
            dest.getIncoming().remove(origin);
        }
        return edgeList.remove(e.getPosition());
    }
    
    /**
     * Represents the data and behavior of an AdjacencyMapGraph vertex
     * @author Matthew Kierski
     *
     */
    private class AMVertex extends GraphVertex {
        /** Map of outgoing edges of this vertex */
        private Map<Vertex<V>, Edge<E>> outgoing;
        /** Map of incoming edges of this vertex */
        private Map<Vertex<V>, Edge<E>> incoming;
        
        /**
         * Constructs an AM vertex
         * @param data data to be set
         * @param isDirected whether or not the graph is directed
         */
        public AMVertex(V data, boolean isDirected) {
            super(data);
            outgoing = new LinearProbingHashMap<Vertex<V>, Edge<E>>();
            if (isDirected) {
                incoming = new LinearProbingHashMap<>();
            } else {
                incoming = outgoing;
            }
        }
        
        /**
         * Returns the map of outgoing edges of this vertex
         * @return the map of outgoing edges of this vertex
         */
        public Map<Vertex<V>, Edge<E>> getOutgoing() {
            return outgoing;
        }
        
        /**
         * Returns the map of incoming edges of this vertex
         * @return the map of incoming edges of this vertex
         */
        public Map<Vertex<V>, Edge<E>> getIncoming() {
            return incoming;
        }
    }
    
    /**
     * Helper method for casting vertices
     * @param v vertex to cast
     * @return validated vertex
     */
    private AMVertex validate(Vertex<V> v) {
        if(!(v instanceof AdjacencyMapGraph.AMVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid adjacency map vertex.");
        }
        return (AMVertex)v;
    }
}