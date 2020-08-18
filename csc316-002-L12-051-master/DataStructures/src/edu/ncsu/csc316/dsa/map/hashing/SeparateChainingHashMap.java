package edu.ncsu.csc316.dsa.map.hashing;

import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.search_tree.AVLTreeMap;
import edu.ncsu.csc316.dsa.map.search_tree.BinarySearchTreeMap;

/**
 * Represents implementation of a Hash Map with separate chaining.
 * Citation: Data Structures & Algorithms, Michael T. Goodrich, page 425.
 * @author Matthew Kierski
 *
 * @param <K> key of entry
 * @param <V> value of entry
 */
public class SeparateChainingHashMap<K extends Comparable<K>, V> extends AbstractHashMap<K, V> {

	/** Array of Map objects */
    private Map<K, V>[] table;
    
    /** Size of the table */
    private int size;
    
    /**
     * Constructs a SeparateChainingHashMap with default capacity and sets isTesting to false
     */
    public SeparateChainingHashMap() {
        this(AbstractHashMap.DEFAULT_CAPACITY, false);
    }
    
    /**
     * Constructs a SeparateChainingHashMap with default capacity and sets isTesting to given boolean
     * @param isTesting boolean flag to determine values of alpha and beta
     */
    public SeparateChainingHashMap(boolean isTesting) {
        this(AbstractHashMap.DEFAULT_CAPACITY, isTesting);
    }  	
	
    /**
     * Constructs a SeparateChainingHashMap, setting capacity to given capacity and
     * isTestng to false
     * @param capacity capacity to set
     */
    public SeparateChainingHashMap(int capacity) {
        this(capacity, false);
    }    
    
    /**
     * Constructs a SeparateChainingHashMap, setting capacity to given capacity and
     * isTesting to given boolean
     * @param capacity capacity to set
     * @param isTesting boolean flag to determine the values of alpha and beta
     */
    public SeparateChainingHashMap(int capacity, boolean isTesting) {
        super(capacity, isTesting);
        size = 0;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
    	List<Entry<K, V>> list = new SinglyLinkedList<Entry<K, V>>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                // Each bucket contains a map, so include
                // all entries in the entrySet for the map
                // at the current bucket
                for (Entry<K, V> entry : table[i].entrySet()) {
                    list.addLast(entry);
                }
            }
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void createTable(int capacity) {
        // Example -- change this to whatever map you'd like        
        table = (BinarySearchTreeMap<K, V>[]) new AVLTreeMap[capacity];
        size = 0;
    }

    @Override
    public V bucketGet(int hash, K key) {
        // Get the bucket at the specified index in the hash table      
        Map<K, V> bucket = table[hash];
        // If there is no map in the bucket, then the entry does not exist      
        if (bucket == null) {
            return null;
        }
        // Otherwise, delegate to the existing map's get method to return the value     
        return bucket.get(key);
    }

    @Override
    public V bucketPut(int hash, K key, V value) {
        Map<K, V> bucket = table[hash]; 
        if (bucket == null) {
        	table[hash] = new AVLTreeMap<>();
        	bucket = table[hash];
        }
        int oldSize = bucket.size();
        V newValue = bucket.put(key, value);
        size += bucket.size() - oldSize;
        return newValue;
    }

    @Override
    public V bucketRemove(int hash, K key) {
        Map<K, V> bucket = table[hash];
        if (bucket == null) {
        	return null;
        }
        int oldSize = bucket.size();
        V removed = bucket.remove(key);
        size -= oldSize - bucket.size();
        return removed;
    }
    
    @Override
    public int size() {
        return size;
    }

    @Override
    protected int capacity() {
        return table.length;
    }
}