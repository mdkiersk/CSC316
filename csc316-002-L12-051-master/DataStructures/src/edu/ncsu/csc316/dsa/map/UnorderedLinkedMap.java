package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * Represents the a doubly-linked list implementation of an unordered map
 * @author Matthew Kierski
 *
 * @param <K> key of entry
 * @param <V> value of entry
 */
public class UnorderedLinkedMap<K, V> extends AbstractMap<K, V> {

	/** Instance of list to be used as data structure */
	private PositionalList<Entry<K, V>> list;
	
	/**
	 * Constructs an UnorderedLinkedMap, setting list to new list
	 */
	public UnorderedLinkedMap() {
		this.list = new PositionalLinkedList<Entry<K, V>>();
	}
	
	/**
	 * Returns the position at which a key exists. If key doesn't exist, return null
	 * @param key key to search by
	 * @return position at which key exists, null otherwise
	 */
	private Position<Entry<K, V>> lookUp(K key) {
		Iterator<Position<Entry<K, V>>> it = list.positions().iterator();
		while (it.hasNext()) {
			Position<Entry<K, V>> p = it.next();
			if (p.getElement().getKey() == key) {
				return p;
			}
		}
		return null;
	}

	@Override
	public V get(K key) {
		Position<Entry<K, V>> p = lookUp(key);
		if (p == null) {
			return null;
		}
		moveToFront(p);
		return p.getElement().getValue();
	}
	
	/**
	 * Implementation of move to front heuristic.
	 * @param position position to be moved to front
	 */
	private void moveToFront(Position<Entry<K, V>> position) {
		list.remove(position);
		list.addFirst(position.getElement());
	}

	@Override
	public V put(K key, V value) {
		Position<Entry<K, V>> p = lookUp(key);
		MapEntry<K, V> entry = new MapEntry<K, V>(key, value);
		if (p == null) {
			list.addFirst(entry);
			return null;
		} else {
			moveToFront(p);
			return list.set(p, entry).getValue();
		}
		
	}
	
	@Override
	public V remove(K key) {
       Position<Entry<K, V>> p = lookUp(key);
       if (p == null) {
    	   return null;
       }
       V removed = p.getElement().getValue();
       list.remove(p);
       return removed;
	}
	
	@Override
	public int size() {
		return list.size();
	}
	
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		PositionalList<Entry<K, V>> set = new PositionalLinkedList<Entry<K, V>>();
		for(Entry<K, V> m : list) {
			set.addLast(m);
		}
		return set;
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
