package edu.ncsu.csc316.dsa.map.hashing;

import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;

/**
 * Represents a hash map that relies on linear probing to store data
 * Citation: Data Structures & Algorithms, Michael T. Goodrich, page 427.
 * @author Matthew Kierski
 *
 * @param <K> key of entry
 * @param <V> value of entry
 */
public class LinearProbingHashMap<K, V> extends AbstractHashMap<K, V> {

    /** Array of TableEntry objects */
    private TableEntry<K, V>[] table;
    /** Size of map */
    private int size;

    /**
     * Constructs a LinearProbingHashMap, setting capacity to default and isTesting to false
     */
    public LinearProbingHashMap() {
        this(AbstractHashMap.DEFAULT_CAPACITY, false);
    }
    
    /**
     * Constructs a LinearProbingHashMap, setting capacity to default and isTesting to custom
     * @param isTesting boolean flag for determining alpha and beta values
     */
    public LinearProbingHashMap(boolean isTesting) {
        this(AbstractHashMap.DEFAULT_CAPACITY, isTesting);
    }
    
    /**
     * Constructs a LinearProbingHashMap, setting capacity to given capacity and isTesting to false
     * @param capacity capacity to set
     */
    public LinearProbingHashMap(int capacity) {
        this(capacity, false);
    }
    
    /**
     * Constructs a LinearProbingHashMap, setting capacity to given capacity and isTesting to given boolean
     * @param capacity capacity to set
     * @param isTesting boolean flag for determining alpha and beta values
     */
    public LinearProbingHashMap(int capacity, boolean isTesting) {
        super(capacity, isTesting);
        size = 0;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
    	List<Entry<K, V>> list = new SinglyLinkedList<Entry<K, V>>();
        for (int i = 0; i < table.length; i++) {
            if (!isAvailable(i)) {
            	list.addLast(table[i]);
            }
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void createTable(int capacity) {
        table = (TableEntry<K, V>[]) new TableEntry[capacity];
        size = 0;
    }
    
    // Helper method to determine whether a bucket has an entry or not  
    /**
     * Private helper method to determine whether a bucket has an entry or not
     * @param index index to evaluate
     * @return true if bucket is null or has been deleted, false otherwise
     */
    private boolean isAvailable(int index) {
        return (table[index] == null || table[index].isDeleted());
    }

    // Helper method to find the bucket for an entry;
    // If the entry *is* in the map, returns the index of the bucket
    // If the entry is *not* in the map, returns -(a + 1) to indicate 
    //     that the entry should be added at index a
    /**
     * Private helper method to find the bucket for an entry
     * @param index index to evaluate
     * @param key key to evaluate
     * @return the index of the bucket if entry is in the map, or value of where it should be added in map
     */
    private int findBucket(int index, K key) {
    	int avail = -1;
    	int j = index;
    	do {
    		if (isAvailable(j)) {
    			if (avail == -1) {
    				avail = j;
    			}
    			if (table[j] == null) {
    				return -(avail + 1);
    			}
    		}
    		else if (table[j].getKey().equals(key)) {
    				return j;
    		}
    		j = (j + 1) % table.length;
    	} while (j != index);
    	return -(avail + 1);
    }
    
    @Override
    public V bucketGet(int hash, K key) {
        int index = findBucket(hash, key);
        if (index < 0) {
        	return null;
        }
        return table[index].getValue();
    }

    @Override
    public V bucketPut(int hash, K key, V value) {
        int index = findBucket(hash, key);
        if (index >= 0) {
        	return table[index].setValue(value);
        }
        table[-(index + 1)] = new TableEntry<K, V>(key, value);
        size++;
        return null;
    }   

    @Override
    public V bucketRemove(int hash, K key) {
        int index = findBucket(hash, key);
        if (index < 0) {
        	return null;
        }
        V removed = table[index].getValue();
        table[index] = null;
        size--;
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
    
    /**
     * Class represents the data and behavior of a TableEntry 
     * @author Matthew Kierski, Dr. King
     *
     * @param <K> key of entry
     * @param <V> value of entry
     */
    private static class TableEntry<K, V> extends MapEntry<K, V> {

    	/** Boolean flag to keep track of an entry that has been deleted from the map */
        private boolean isDeleted;

        /**
         * Constructs a TableEntry, setting key to given key and value to given value
         * @param key key to set
         * @param value value to set
         */
        public TableEntry(K key, V value) {
            super(key, value);
            setDeleted(false);
        }

        /**
         * Returns whether or not an entry is deleted
         * @return true if entry has been deleted, false otherwise
         */
        public boolean isDeleted() {
            return isDeleted;
        }

        /**
         * Sets isDeleted field to given boolean
         * @param deleted boolean to set isDeleted to
         */
        public void setDeleted(boolean deleted) {
            isDeleted = deleted;
        }
    }
}