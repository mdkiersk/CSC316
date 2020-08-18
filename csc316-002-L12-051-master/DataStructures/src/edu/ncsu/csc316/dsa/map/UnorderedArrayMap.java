package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;
import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * Implementation of an UnorderedArrayMap using our ArrayBasedList as data structure.
 * Citation: Data Structures and Algorithms, p. 408-9.
 * @author Matthew Kierski
 *
 * @param <K> key associated with entry
 * @param <V> value associated with entry
 */
public class UnorderedArrayMap<K, V> extends AbstractMap<K, V> {

	/** Instance of list to be used as underlying data structure */
	private ArrayBasedList<Entry<K, V>> list;

	/**
	 * Constructs an UnorderedArrayMap, setting list to new list
	 */
	public UnorderedArrayMap() {
		this.list = new ArrayBasedList<Entry<K, V>>();
	}
	
	/**
	 * Returns the index of a given key, or -1 if key doesn't exist
	 * @param key key to search by
	 * @return the index of a given key, or -1 if key doesn't exist
	 */
	private int lookUp(K key) {
		int index = 0;
		for (index = 0; index < size(); index++) {
			if (list.get(index).getKey().equals(key)) {
				transpose(index);
				if (index > 0) {
					index--;
				}
				return index;
			}
		}
		return -1;
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
	public V put(K key, V value) {
		int index = lookUp(key);
		if (index == -1) {
			MapEntry<K, V> entry = new MapEntry<K, V>(key, value);
			list.addFirst(entry);
			return null;
		} else {
			return list.get(index).setValue(value);
		}
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
	public int size() {
		return list.size();
	}

	
	/**
	 * Implementation of the transpose heuristic
	 * @param index index to be moved up
	 * @return the value of the transposed entry
	 */
	private V transpose(int index) {
		if (index == 0) { 
			return list.get(index).getValue();
		}
		Entry<K, V> temp = list.get(index - 1);
		list.set(index - 1, list.get(index));
		list.set(index, temp);
		return list.get(index).getValue();
	}
	
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayBasedList<Entry<K, V>> newList = new ArrayBasedList<Entry<K, V>>();
		Iterator<Entry<K, V>> it = list.iterator();
		while (it.hasNext()) {
			newList.addLast(it.next());
		}
		return newList;
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