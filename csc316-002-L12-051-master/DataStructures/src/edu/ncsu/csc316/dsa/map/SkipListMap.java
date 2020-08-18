package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Random;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;
import edu.ncsu.csc316.dsa.list.List;

/**
 * Represents the implementation of a SkipListMap 
 * @author Matthew Kierski
 *
 * @param <K> key of entry
 * @param <V> value of entry
 */
public class SkipListMap<K extends Comparable<K>, V> extends AbstractSortedMap<K, V> {
	
	/** Instance of random object to determine coin flip */
	private Random coinToss;
	/** Private instance of entry representing start of list */
	private SkipListEntry<K, V> start;
	/** Size of list */
	private int size;
	/** Height of list */
	private int height;
	/** Represents left-most sentinel node key */
	private final K negInf = null;
	/** Represents right-most sentinel node key */
	private final K inf = null;

	/**
	 * Constructs SkipListMap with null comparator
	 */
	public SkipListMap() {
		this(null);
	}

	/**
	 * Constructs a SkipListMap with given comparator, setting coinToss to new Random 
	 * object and start to new entry
	 * @param compare comparator to be used for sorting
	 */
	public SkipListMap(Comparator<K> compare) {
		super(compare);
		coinToss = new Random();
		// Create a dummy head node for the left "-INFINITY" sentinel tower
		start = new SkipListEntry<K, V>(null, null);
		// Create a dummy tail node for the right "+INFINITY" sentinel tower		
		start.setNext(new SkipListEntry<K, V>(null, null));
		// Set the +INFINITY tower's previous to be the "start" node
		start.getNext().setPrevious(start);
		size = 0;
		height = 0;
	}

    /**
     * Helper method to determine if given entry is one of the sentinel nodes
     * @param entry entry to be evaluated
     * @return true if sentinel, false otherwise
     */
	private boolean isSentinel(SkipListEntry<K, V> entry) {
		return entry.getKey() == null;
	}	

	/**
	 * Helper method that returns the entry where the given key exists
	 * @param key key to search by
	 * @return the entry where key exists
	 */
	private SkipListEntry<K, V> lookUp(K key) {
		SkipListEntry<K, V> current = start;
		while (current.getBelow() != null) {
            current = current.getBelow();
            while (!isSentinel(current.getNext()) && compare(key, current.getNext().getKey()) >= 0) {
                current = current.getNext();
            }
        }
        return current;
	}

	@Override
	public V get(K key) {
		SkipListEntry<K, V> temp = lookUp(key);
		if (temp.getKey() == key) {
			return temp.getValue();
		}
		return null;
	}

	/**
	 * Inserts a new entry to the list with given key and value. Added after a given entry, and above a given node
	 * @param prev previous node of new entry
	 * @param down below node of new entry
	 * @param key key of new entry
	 * @param value value of new entry
	 * @return the new entry
	 */
	private SkipListEntry<K, V> insertAfterAbove(SkipListEntry<K, V> prev, SkipListEntry<K, V> down, K key, V value) {
		SkipListEntry<K, V> newEntry = new SkipListEntry<K, V>(key, value);
		newEntry.setBelow(down);
		newEntry.setPrevious(prev);
		if (prev != null) {
			newEntry.setNext(prev.getNext());
			newEntry.getPrevious().setNext(newEntry);
		}
		if (newEntry.getNext() != null) {
			newEntry.getNext().setPrevious(newEntry);
		}
		if (down != null) {
			down.setAbove(newEntry);
		}
		return newEntry;
	}

	@Override
	public V put(K key, V value) {
		SkipListEntry<K, V> temp = lookUp(key);
		if (temp.getKey() != null && compare(temp.getKey(), key) == 0) {
			V originalValue = temp.getValue();
			while (temp != null) {
				temp.setValue(value);
				temp = temp.getAbove();
			}
			return originalValue;
		}
		SkipListEntry<K, V> q = null;
		int currentLevel = -1;
		do {
			currentLevel++;
			if (currentLevel >= height) {
				height++;
				SkipListEntry<K, V> tail = start.getNext();
				start = insertAfterAbove(null, start, negInf, null);
				insertAfterAbove(start, tail, inf, null);
			}
			q = insertAfterAbove(temp, q, key, value);
			while (temp.getAbove() == null) {
				temp = temp.getPrevious();
			}
			temp = temp.getAbove();
		} while (coinToss.nextDouble() < 0.5);
		size++;
		return null;
	}
	
    @Override
    public V remove(K key) {
    	SkipListEntry<K, V> temp = lookUp(key);
    	if (temp.getKey() == key) {
    		V removed = temp.getValue();
    		while (temp != null) {
    			temp.getPrevious().setNext(temp.getNext());
    			temp.getNext().setPrevious(temp.getPrevious());
    			temp = temp.getAbove();
    		}
    		size--;
    		return removed;
    	}
    	return null;
    }	

	@Override
	public int size() {
		return size;
	}
	
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		List<Entry<K, V>> set = new ArrayBasedList<Entry<K, V>>();
		SkipListEntry<K, V> current = start;
		while (current.getBelow() != null) {
			current = current.getBelow();
		}
		current = current.getNext();
		while (!isSentinel(current)) {
			set.addLast(current);
			current = current.getNext();
		}
		return set;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[");
		SkipListEntry<K, V> cursor = start;
		while( cursor.getBelow() != null) {
			cursor = cursor.getBelow();
		}
		cursor = cursor.getNext();
		while(cursor != null && cursor.getKey() != null) {
			sb.append(cursor.getKey());
			if(!isSentinel(cursor.getNext())) {
				sb.append(", ");
			}
			cursor = cursor.getNext();
		}
		sb.append("]");
		
		return sb.toString();
	}

    // This method may be useful for testing or debugging.
    // You may comment-out this method instead of testing it, since
    // the full string will depend on the series of random coin flips
    // and will not have deterministic expected results.
	/**
	 * Constructs a full String representation of the present state of the list
	 * @return skip list in String format
	 */
	public String toFullString() {
		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[\n");
		SkipListEntry<K, V> cursor = start;
		SkipListEntry<K, V> firstInList = start;
		while( cursor != null) {
			firstInList = cursor;
			sb.append("-INF -> ");
			cursor = cursor.getNext();
			while(cursor != null && !isSentinel(cursor)) {
				sb.append(cursor.getKey() + " -> ");
				cursor = cursor.getNext();
			}
			sb.append("+INF\n");
			cursor = firstInList.getBelow();
		}
		sb.append("]");
		return sb.toString();
	}
	
	/**
	 * Represents the behavior and data of a SkipListEntry
	 * @author Matthew Kierski
	 *
	 * @param <K> key of entry 
	 * @param <V> value of entry
	 */
	private static class SkipListEntry<K, V> extends MapEntry<K, V> {

		/** Entry above this entry */
		private SkipListEntry<K, V> above;
		/** Entry below this entry */
		private SkipListEntry<K, V> below;
		/** Entry before this entry */
		private SkipListEntry<K, V> prev;
		/** Entry after this entry */
		private SkipListEntry<K, V> next;

		/**
		 * Constructs a SkipListEntry, setting all surrounding entries to null
		 * @param key key of new entry
		 * @param value value of new entry
		 */
		public SkipListEntry(K key, V value) {
			super(key, value);
			setAbove(null);
			setBelow(null);
			setPrevious(null);
			setNext(null);
		}

		/**
		 * Returns the entry below this node
		 * @return the entry below this node
		 */
		public SkipListEntry<K, V> getBelow() {
			return below;
		}

		/**
		 * Returns the entry after this entry
		 * @return the entry after this entry
		 */
		public SkipListEntry<K, V> getNext() {
			return next;
		}

		/**
		 * Returns the entry before this entry
		 * @return the entry before this entry
		 */
		public SkipListEntry<K, V> getPrevious() {
			return prev;
		}

		/**
		 * Returns the entry above this entry
		 * @return the entry above this entry
		 */
		public SkipListEntry<K, V> getAbove() {
			return above;
		}

		/**
		 * Sets the entry below this entry to given entry
		 * @param down given entry to be set
		 */
		public void setBelow(SkipListEntry<K, V> down) {
			this.below = down;
		}

		/**
		 * Sets the entry after this entry to given entry
		 * @param next given entry to be set
		 */
		public void setNext(SkipListEntry<K, V> next) {
			this.next = next;
		}

		/**
		 * Sets the entry before this entry to given entry
		 * @param prev given entry to be set
		 */
		public void setPrevious(SkipListEntry<K, V> prev) {
			this.prev = prev;
		}

		/**
		 * Sets the entry above this entry to given entry
		 * @param up given entry to be set
		 */
		public void setAbove(SkipListEntry<K, V> up) {
			this.above = up;
		}
	}
}