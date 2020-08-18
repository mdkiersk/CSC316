package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * Represents a LinkedList implementation of a Binary Search Tree
 * Citation: Data Structures & Algorithms, 326-329
 * @author Matthew Kierski
 *
 * @param <E> generic element of tree
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

	/** Private instance of the root of this tree */
    private Node<E> root;
    /** Private instance of the size of this tree */
    private int size;

    /**
     * Constructs a LinkedBinaryTree, setting root to null and size to 0
     */
    public LinkedBinaryTree() {
        root = null;
        size = 0;
    }

    /**
     * Validates whether a given position is a valid node
     * @param p node to evaluate
     * @return true if valid, false otherwise
     */
    protected Node<E> validate(Position<E> p) {
        if (!(p instanceof Node)) {
            throw new IllegalArgumentException("Position is not a valid linked binary node");
        }
        return (Node<E>) p;
    }

    @Override
    public Position<E> left(Position<E> p) {
        Node<E> node = validate(p);
        return node.getLeft();
    }

    @Override
    public Position<E> right(Position<E> p) {
        Node<E> node = validate(p);
        return node.getRight();
    }

    @Override
    public Position<E> addLeft(Position<E> p, E value) {
        Node<E> node = validate(p);
        if (left(node) != null) {
            throw new IllegalArgumentException("Node already has a left child.");
        }
        Node<E> child = createNode(value, node, null, null);
        node.setLeft(child);
        size++;
        return child;
    }

    @Override
    public Position<E> addRight(Position<E> p, E value) {
        Node<E> node = validate(p);
        if (right(node) != null) {
            throw new IllegalArgumentException("Node already has a right child.");
        }
        Node<E> child = createNode(value, node, null, null);
        node.setRight(child);
        size++;
        return child;
    }

    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public Position<E> parent(Position<E> p) {
        Node<E> node = validate(p);
        return node.getParent();
    }

    @Override
    public Position<E> addRoot(E value) {
        if (root() != null) {
            throw new IllegalArgumentException("The tree already has a root.");
        }
        this.root = createNode(value, null, null, null);
        size++;
        return root;
    }

    @Override
    public E remove(Position<E> p) {
        if (numChildren(p) == 2){
            throw new IllegalArgumentException("The node has two children");
        }
        Node<E> node = validate(p);
        Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
        if (child != null) {
        	child.setParent(node.getParent());
        }
        if (node == root) {
        	root = child;
        }
        else {
        	Node<E> parent = node.getParent();
        	if (node == parent.getLeft()) {
        		parent.setLeft(child);
        	}
        	else {
        		parent.setRight(child);
        	}
        }
        size--;
        E temp = node.getElement();
        node.setElement(null);
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node);
        return temp;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Creates a new node in this tree
     * @param e value of new node
     * @param parent parent of new node
     * @param left left child of new node
     * @param right right child of new node
     * @return the new node
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        Node<E> newNode = new Node<E>(e);
        newNode.setParent(parent);
        newNode.setLeft(left);
        newNode.setRight(right);
        return newNode;
    }

    // setRoot is needed for a later lab...
    // ...but THIS DESIGN IS BAD! If a client arbitrarily changes
    // the root by using the method, the size may no longer be correct/valid.
    // Instead, the precondition for this method is that
    // it should *ONLY* be used when rotating nodes in 
    // balanced binary search trees. We could instead change
    // our rotation code to not need this setRoot method, but that
    // makes the rotation code messier. For the purpose of this lab,
    // we will sacrifice a stronger design for cleaner/less code.
    /**
     * Sets the root to be a given node
     * @param p node to become root
     * @return the new root
     */
    protected Position<E> setRoot(Position<E> p) {
        root = validate(p);
        return root;
    }
    
    /**
     * Represents the data and behaviors of a node in a tree
     * @author Matthew Kierski
     *
     * @param <E> generic element of node
     */
    public static class Node<E> extends AbstractNode<E> {
    	/** Parent of this node */
        private Node<E> parent;
        /** Left child of this node */
        private Node<E> left;
        /** Right child of this node */
        private Node<E> right;

        /**
         * Constructs a node with a given element, setting parent to null
         * @param element element of new node
         */
        public Node(E element) {
            this(element, null);
        }

        /**
         * Constructs a node with a given element and parent
         * @param element element of new node
         * @param parent parent of new node
         */
        public Node(E element, Node<E> parent) {
            super(element);
            setParent(parent);
        }

        /**
         * Returns the left child of this node
         * @return left child of this node
         */
        public Node<E> getLeft() {
            return left;
        }

        /**
         * Returns the right child of this node
         * @return right child of this node
         */
        public Node<E> getRight() {
            return right;
        }

        /**
         * Sets the left child to given node
         * @param left node to be set
         */
        public void setLeft(Node<E> left) {
            this.left = left;
        }

        /**
         * Sets the right child to given node
         * @param right node to set
         */
        public void setRight(Node<E> right) {
            this.right = right;
        }

        /**
         * Returns the parent of this node
         * @return parent of this node
         */
        public Node<E> getParent() {
            return parent;
        }

        /**
         * Sets the parent of this node to given node
         * @param parent node to set as parent
         */
        public void setParent(Node<E> parent) {
            this.parent = parent;
        }
    }
}