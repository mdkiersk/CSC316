package edu.ncsu.csc316.dsa.map.hashing;

import java.util.Random;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.AbstractMap;

/**
 * Represents the abstract class for a Hash map
 * @author Matthew Kierski
 *
 * @param <K> key of entry
 * @param <V> value of entry
 */
public abstract class AbstractHashMap<K, V> extends AbstractMap<K, V> {

    /** An initial capacity for the hash table */
    protected static final int DEFAULT_CAPACITY = 17;
    
    // From our discussion in class, the expected number of probes
    // for separate chaining remains relatively the same no matter
    // what the load factor may be. However, for linear probing, to
    // reduce the chance of having large clusters, we will resize
    // when the load factor reaches 0.5
    /** Constant for the max load factor for linear probing */
    private static final double MAX_LOAD_FACTOR = 0.5;
    
    /** Constant for prime used in compression */
    protected static final int DEFAULT_PRIME = 109345121;
    
    // Alpha and Beta values for MAD compression
    // This implementation uses a variation of the MAD method
    // where h(k) = ( (alpha * f(k) + beta) % prime) % capacity
    /** Alpha value for multiply and divide compression */
    private long alpha;
    /** Beta value for multiply and divide compression */
    private long beta;
    
    /** The prime number to use for compression strategy */
    private int prime;
    
    // You can use the isTesting flag (set to true) to control
    // the testing environment and avoid random numbers when testing
    /**
     * Constructs a hash map with given capacity. If isTesting is true, we set specific values for compression
     * to be able to test. If false, we assign random values for compression. 
     * @param capacity capacity of map
     * @param isTesting flag to determine if method is called for testing
     */
    public AbstractHashMap(int capacity, boolean isTesting) {
        if (isTesting) {
            alpha = 1;
            beta = 1;
            prime = 7;
        } else {
            Random rand = new Random();
            alpha = rand.nextInt(DEFAULT_PRIME - 1) + 1;
            beta = rand.nextInt(DEFAULT_PRIME);
            prime = DEFAULT_PRIME;
        }
        createTable(capacity);
    }
    
    /**
     * Compresses a given key
     * @param key key to compress
     * @return compressed key
     */
    private int compress(K key) {
        return (int)((Math.abs(key.hashCode() * alpha + beta) % prime) % capacity());
    }

    @Override
    public V put(K key, V value) {
        V ret = bucketPut(compress(key), key, value);
        if ((double)size() / capacity() > MAX_LOAD_FACTOR) {
            resize(2 * capacity() + 1);
        }
        return ret;
    }
    
    @Override
    public V get(K key) {
        return bucketGet(compress(key), key);
    }

    @Override
    public V remove(K key) {
        return bucketRemove(compress(key), key);
    }
    
    /**
     * Resizes the hash map with new capacity
     * @param newCapacity updated capacity of hash map
     */
    private void resize(int newCapacity) {
        List<Entry<K, V>> list = new ArrayBasedList<Entry<K, V>>();
        for (Entry<K, V> entry : entrySet()) {
            list.addLast(entry);
        }
        createTable(newCapacity);
        for (Entry<K, V> entry : list) {
            put(entry.getKey(), entry.getValue());
        }
    }
    
    /**
     * Returns the capacity of a hash map
     * @return the capacity of a hash map
     */
	protected abstract int capacity();

	/**
	 * Creates a hash table with a given capacity
	 * @param capacity initial capacity of hash table
	 */
	protected abstract void createTable(int capacity);

	/**
	 * Returns the value of the bucket with a given hash and key
	 * @param hash hash to search by
	 * @param key key to search by
	 * @return the value of the bucket
	 */
	protected abstract V bucketGet(int hash, K key);

	/**
	 * Puts a bucket into the hash map with the given hash, key, and value
	 * @param hash hash to set
	 * @param key key to set
	 * @param value value to set
	 * @return the value of the new bucket
	 */
	protected abstract V bucketPut(int hash, K key, V value);

	/**
	 * Removes a bucket with the given hash and key, and returns the value of the
	 * removed bucket
	 * @param hash hash to search by
	 * @param key key to search by
	 * @return the value of the removed bucket
	 */
	protected abstract V bucketRemove(int hash, K key);
}