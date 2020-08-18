package edu.ncsu.csc316.dsa.graph;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * Represents the implementation of an AdjacencyListGraph
 * @author Matthew Kierski
 *
 * @param <V> vertex of graph
 * @param <E> edge of graph
 */
public class AdjacencyListGraph<V, E> extends AbstractGraph<V, E> {

	/** List of vertices in this graph */
    private PositionalList<Vertex<V>> vertexList;
    /** List of edges in this graph */
    private PositionalList<Edge<E>> edgeList;
    
    /**
     * Constructs an AdjacencyListGraph
     */
    public AdjacencyListGraph() {
        this(false);
    }
    
    /**
     * Constructs an AdjacencyListGraph
     * @param directed whether or not graph is directed
     */
    public AdjacencyListGraph(boolean directed) {
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
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
        return validate(vertex).getOutgoing();
    }
    
    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
        return validate(vertex).getIncoming();
    }

    @Override
    public Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2) {
        ALVertex v1 = validate(vertex1);
        ALVertex v2 = validate(vertex2);
        Iterator<Edge<E>> it = edgeList.iterator();
        while (it.hasNext()) {
            GraphEdge current = validate(it.next());
            Vertex<V>[] ends = current.getEndpoints();
            if(!isDirected() && ends[1] == v1 && ends[0] == v2) {
                return current;
            }
            if (ends[0] == v1 && ends[1] == v2) {
                return current;
            }
        }
        return null;
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
    public Vertex<V> insertVertex(V vertexData) {
        ALVertex vertex = new ALVertex(vertexData, isDirected());
        Position<Vertex<V>> pos = vertexList.addLast(vertex);
        vertex.setPosition(pos);
        return vertex;
        
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeData) {
        ALVertex origin = validate(v1);
        ALVertex destination = validate(v2);   
        ALEdge edge = new ALEdge(edgeData, origin, destination);
        Position<Edge<E>> pos = edgeList.addLast(edge);
        edge.setPosition(pos);
        edge.setOutgoingListPosition(origin.outgoing.addLast(edge));
        edge.setIncomingListPosition(destination.incoming.addLast(edge));
        return edge;
        
    }

    @Override
    public Vertex<V> removeVertex(Vertex<V> vertex) {
        ALVertex v = validate(vertex);
        for (Edge<E> e: v.incoming) {
        	removeEdge(e);
        }
        for (Edge<E> e: v.outgoing) {
        	removeEdge(e);
        }
        return vertexList.remove(v.getPosition());
    }

    @Override
    public Edge<E> removeEdge(Edge<E> edge) {
        ALEdge e = validate(edge);
        Vertex<V>[] ends = e.getEndpoints();
        ALVertex origin = validate(ends[0]);
        ALVertex dest = validate(ends[1]);
        origin.outgoing.remove(e.getOutgoingListPosition());
        dest.incoming.remove(e.getIncomingListPosition());
        return edgeList.remove(e.getPosition());
    }
    
    /**
     * Represents the data and behavior of an AL vertex
     * @author Matthew Kierski
     *
     */
    private class ALVertex extends GraphVertex {
        
    	/** List of outgoing edges from this vertex */
        private PositionalList<Edge<E>> outgoing;
        /** List of incoming edges to this vertex */
        private PositionalList<Edge<E>> incoming;
        
        /**
         * Constructs an ALVertex
         * @param data data to set
         * @param isDirected whether or not graph is directed
         */
        public ALVertex(V data, boolean isDirected) {
            super(data);
            outgoing = new PositionalLinkedList<Edge<E>>();
            if (isDirected) {
                incoming = new PositionalLinkedList<Edge<E>>();
            } else {
                incoming = outgoing;
            }
        }
        
        /**
         * Returns the outgoing edges of this vertex
         * @return the outgoing edges of this vertex
         */
        public PositionalList<Edge<E>> getOutgoing() {
            return outgoing;
        }
        
        /**
         * Returns the incoming edges of this vertex
         * @return the incoming edges of this vertex
         */
        public PositionalList<Edge<E>> getIncoming() {
            return incoming;
        }
    }
    
    /**
     * Represents the data and behavior of an AL Edges
     * @author Matthew Kierski
     *
     */
    private class ALEdge extends GraphEdge {
    	/** The position of this edge in the outgoing list */
        private Position<Edge<E>> outgoingListPosition;
        /** The position of thios edge in the incoming list */
        private Position<Edge<E>> incomingListPosition;
        
        /**
         * Constructs an AL edge
         * @param element element to set
         * @param v1 vertex of this edge
         * @param v2 vertex of this edge
         */
        public ALEdge(E element, Vertex<V> v1, Vertex<V> v2) {
            super(element, v1, v2);
        }
        
        /**
         * Returns the position of this edge in the outgoing list
         * @return the position of this edge in the outgoing list
         */
        public Position<Edge<E>> getOutgoingListPosition() {
            return outgoingListPosition;
        }
        
        /**
         * Sets the position of this edge in the outgoing list
         * @param outgoingListPosition position to set
         */
        public void setOutgoingListPosition(Position<Edge<E>> outgoingListPosition) {
            this.outgoingListPosition = outgoingListPosition;
        }
        
        /**
         * Returns the position of this edge in the incoming list
         * @return the position of this edge in the incoming list
         */
        public Position<Edge<E>> getIncomingListPosition() {
            return incomingListPosition;
        }
        
        /**
         * Sets the position of this edge in the incoming list
         * @param incomingListPosition position to set
         */
        public void setIncomingListPosition(Position<Edge<E>> incomingListPosition) {
            this.incomingListPosition = incomingListPosition;
        }
    }

    /**
     * Helper method for casting AL vertices
     * @param v vertex to cast
     * @return validated vertex
     */
    private ALVertex validate(Vertex<V> v) {
        if (!(v instanceof AdjacencyListGraph.ALVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid adjacency list vertex.");
        }
        return (ALVertex)v;
    }
    
    /**
     * Helper method for casting AL edges
     * @param e edge to cast
     * @return validated edge
     */
    protected ALEdge validate(Edge<E> e) {
        if(!(e instanceof AdjacencyListGraph.ALEdge)) {
            throw new IllegalArgumentException("Edge is not a valid adjacency list edge.");
        }
        return (ALEdge)e;
    }
}