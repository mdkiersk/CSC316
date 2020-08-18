package edu.ncsu.csc316.dsa.tree;
import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.list.List;

/**
 * Represents the abstract implementation of all Binary Search Trees
 * Citation: Data Structures & Algorithms, p. 320 & 342
 * @author Matthew Kierski
 *
 * @param <E> generic element of tree
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTreeCollection<E> {

    @Override
    public Iterable<Position<E>> inOrder() {
        List<Position<E>> traversal = new SinglyLinkedList<Position<E>>();
        if (!isEmpty()) {
            inOrderHelper(root(), traversal);
        }
        return traversal;
    }

    /**
     * Private helper method for in-order traversal of trees
     * @param node node to evaluate
     * @param traversal list to add to
     */
    private void inOrderHelper(Position<E> node, List<Position<E>> traversal) {
        if (left(node) != null) {
        	inOrderHelper(left(node), traversal);
        }
        
        if (node.getElement() != null) {
        		traversal.addLast(node);
        }
        
        if (right(node) != null) {
        	inOrderHelper(right(node), traversal);
        }
    }
    
    @Override
    public Position<E> sibling(Position<E> p) {
    	Position<E> parent = parent(p);
    	if (parent == null) {
    		return null;
    	}
    	if (p == left(parent)) {
    		return right(parent);
    	}
    	else {
    		return left(parent);
    	}
    }
    
    /**
     * Validates whether a given position is a valid node
     * @param p position to evaluate
     * @return true if proper instance of node, false otherwise
     */
    private AbstractNode<E> validate(Position<E> p) {
        if(!(p instanceof AbstractNode)) {
            throw new IllegalArgumentException("Position is not a valid binary tree node");
        }
        return (AbstractNode<E>)(p);
    }
    
    @Override
    public int numChildren(Position<E> p) {
    	int count = 0;
    	if (left(p) != null) {
    		count++;
    	}
    	if (right(p) != null) {
    		count++;
    	}
    	return count;
    }
    
    @Override
    public E set(Position<E> p, E value) {
        AbstractNode<E> node = validate(p);
        E original = node.getElement();
        node.setElement(value);
        return original;
    }
    
    @Override
    public Iterator<E> iterator() {
        return new ElementIterator(inOrder().iterator());
    }
    
    @Override
    public Iterable<Position<E>> children(Position<E> p) {
        AbstractNode<E> node = validate(p);
        List<Position<E>> ret = new SinglyLinkedList<Position<E>>();
        if(left(node) != null) {
            ret.addLast(left(node));
        }
        if(right(node) != null) {
            ret.addLast(right(node));
        }
        return ret;
    }
}