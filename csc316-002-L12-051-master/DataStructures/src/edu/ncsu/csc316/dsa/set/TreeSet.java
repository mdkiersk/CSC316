package edu.ncsu.csc316.dsa.set;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.Map.Entry;
import edu.ncsu.csc316.dsa.map.search_tree.BinarySearchTreeMap;

// Remember that search trees are ordered, so our elements must be Comparable
/**
 * Represents the implementation of a Set using a BinarySearchTreeMap
 * @author Matthew Kierski
 *
 * @param <E> value of entry
 */
public class TreeSet<E extends Comparable<E>> extends AbstractSet<E> {

	/** Instance of underlying data structure */
    private Map<E, E> tree;
    
    /**
     * Constructs a TreeSet, setting tree to be a new BinarySearchTreeMap
     */
    public TreeSet() {
        tree = new BinarySearchTreeMap<E, E>();
    }
    
    @Override
    public Iterator<E> iterator() {
        return tree.iterator();
    }

    @Override
    public void add(E value) {
        tree.put(value, value);
    }

    @Override
    public boolean contains(E value) {
       Iterator<Entry<E, E>> it = tree.entrySet().iterator();
       while (it.hasNext()) {
    	   if (it.next().getValue().compareTo(value) == 0) {
    		   return true;
    	   }
       }
       return false;
    }

    @Override
    public E remove(E value) {
        return tree.remove(value);
    }

    @Override
    public int size() {
        return tree.size();
    }
}