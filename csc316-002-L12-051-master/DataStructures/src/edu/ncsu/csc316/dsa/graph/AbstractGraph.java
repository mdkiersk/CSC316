package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Position;

/**
 * Represents the common behavior of all Graphs
 * @author Matthew Kierski
 *
 * @param <V> vertex of graph
 * @param <E> edge of graph
 */
public abstract class AbstractGraph<V, E> implements Graph<V, E> {
    
	/** Boolean flag for directed characteristic */
    private boolean isDirected;
    
    /**
     * Constructs a graph, setting directed boolean to what is given
     * @param directed whether or not the graph is directed
     */
    public AbstractGraph(boolean directed) {
        setDirected(directed);
    }
    
    /**
     * Sets the directed instance
     * @param directed whether or not the graph is directed
     */
    private void setDirected(boolean directed) {
        isDirected = directed;
    }
    
    /**
     * Returns true if directed, false otherwise
     * @return true if directed, false otherwise
     */
    public boolean isDirected() {
        return isDirected;
    }
    
    @Override
    public Vertex<V>[] endVertices(Edge<E> edge) {
        return validate(edge).getEndpoints();
    }

    @Override
    public Vertex<V> opposite(Vertex<V> vertex, Edge<E> edge) {
        GraphEdge temp = validate(edge);
        Vertex<V>[] ends = temp.getEndpoints();
        if (ends[0] == vertex) {
            return ends[1];
        }
        if (ends[1] == vertex) {
            return ends[0];
        }
        throw new IllegalArgumentException("Vertex is not incident on this edge.");
    }
    
    /**
     * Represents the data and behavior of a graph vertex
     * @author Matthew Kierski
     *
     */
    protected class GraphVertex implements Vertex<V> {
    	/** Element of vertex */
        private V element;
        /** Position of this vertex */
        private Position<Vertex<V>> position;
        
        /**
         * Constructs a new vertex, setting element
         * @param element element to set
         */
        public GraphVertex(V element) {
            setElement(element);
        }
        
        /**
         * Sets the element of this vertex
         * @param element element to set
         */
        public void setElement(V element) {
            this.element = element;
        }
        
        /**
         * Returns the element of this vertex
         * @return the element of this vertex
         */
        public V getElement() {
            return element;
        }
        
        /**
         * Returns the position of this vertex
         * @return the position of this vertex
         */
        public Position<Vertex<V>> getPosition(){
            return position;
        }
        
        /**
         * Sets the position of this vertex
         * @param pos position to set
         */
        public void setPosition(Position<Vertex<V>> pos) {
            position = pos;
        }
    }
    
    /**
     * Represents the data and behavior of a Graph edge
     * @author Matthew Kierski
     *
     */
    protected class GraphEdge implements Edge<E> {
    	/** Element of this edge */
        private E element;
        /** Array of incident vertices */
        private Vertex<V>[] endpoints;
        /** Position of this edge */
        private Position<Edge<E>> position;
        
        /**
         * Constructs a GraphEdge
         * @param element element of new edge
         * @param v1 vertex of new edge
         * @param v2 vertex of new edge
         */
        @SuppressWarnings("unchecked")
        public GraphEdge(E element, Vertex<V> v1, Vertex<V> v2) {
            setElement(element);
            endpoints = (Vertex<V>[])new Vertex[]{v1, v2};
        }
        
        /**
         * Sets the element of this edge
         * @param element element to set
         */
        public void setElement(E element) {
            this.element = element;
        }
        
        /**
         * Returns the element of this edge
         * @return the element of this edge
         */
        public E getElement() {
            return element;
        }
        
        /**
         * Returns the endpoints of this edge
         * @return the endpoints of this edge
         */
        public Vertex<V>[] getEndpoints() {
            return endpoints;
        }
        
        /**
         * Returns the position of this edge
         * @return the position of this edge
         */
        public Position<Edge<E>> getPosition(){
            return position;
        }
        
        /**
         * Sets the position of this edge
         * @param pos position to set
         */
        public void setPosition(Position<Edge<E>> pos) {
            position = pos;
        }
        
        @Override
        public String toString() {
            return "Edge[element=" + element + "]";
        }
    }
    
    /**
     * Helper method for casting edges
     * @param e edge to cast
     * @return validated edge
     */
    protected GraphEdge validate(Edge<E> e) {
        if (!(e instanceof AbstractGraph.GraphEdge)) {
            throw new IllegalArgumentException("Edge is not a valid graph edge.");
        }
        return (GraphEdge) e;
    }
}