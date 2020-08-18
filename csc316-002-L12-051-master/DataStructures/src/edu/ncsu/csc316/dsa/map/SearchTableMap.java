package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Iterator;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * Represents the array based implementation of a sorted SearchTableMap
 * @author Matthew Kierski
 *
 * @param <K> key of entry
 * @param <V> value of entry
 */
public class SearchTableMap<K extends Comparable<K>, V> extends AbstractSortedMap<K, V> {

	/** Private instance of array list to serve as implementation */
	private ArrayBasedList<Entry<K, V>> list;

	/**
	 * Constructs a SearchTableMap, setting comparator to null
	 */
	public SearchTableMap() {
		this(null);
	}
	
	/**
	 * Constructs a SearchTableMap, setting list to new list and comparator to given comparator
	 * @param compare comparator to be used for sorting 
	 */
	public SearchTableMap(Comparator<K> compare) {
		super(compare);
		list = new ArrayBasedList<Entry<K, V>>();
	}

	/**
	 * Returns value of entry with given key, null if key not found
	 * @param key key to search by
	 * @return value of entry with given key, null if key not found
	 */
	private int lookUp(K key) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getKey() == key) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Helper method that determines where a given key is in the list, or the index at which a given 
	 * key should be placed in the list
	 * @param min min value to evalutate
	 * @param max max value to evaluate
	 * @param key key to search for
	 * @return the index of the key if it exists, or negative index of where key should exist in list
	 */
	private int binarySearchHelper(int min, int max, K key) {
		if (min > max) {
			return -1 * (min + 1);
		}
		int mid = (max + min) / 2;
		if (super.compare(list.get(mid).getKey(), key) == 0) {
			return mid;
		}
		else if (super.compare(list.get(mid).getKey(), key) > 0) {
			return binarySearchHelper(min, mid - 1, key);
		}
		else {
			return binarySearchHelper(mid + 1, max, key);
		}
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public V get(K key) {
		int index = lookUp(key);
		if (index == -1) {
			return null;
		}
		return list.get(index).getValue();
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayBasedList<Entry<K, V>> set = new ArrayBasedList<Entry<K, V>>();
		for(Entry<K, V> m : list) {
			set.addLast(m);
		}
		return set;
	}

	@Override
	public V put(K key, V value) {
		int index = lookUp(key);
		if (index == -1) {
			MapEntry<K, V> entry = new MapEntry<K, V>(key, value);
			list.add(-1 * (binarySearchHelper(0, list.size() - 1, key) + 1), entry);
			return null;
		}
		return list.get(index).setValue(value);
	}

	@Override
	public V remove(K key) {
		int index = lookUp(key);
		if (index == -1) {
			return null;
		}
		V removed = list.get(index).getValue();
		list.remove(index);
		return removed;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[");
		Iterator<Entry<K, V>> it = list.iterator();
		while(it.hasNext()) {
			sb.append(it.next().getKey());
			if(it.hasNext()) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
}