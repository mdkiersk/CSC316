package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;

/**
 * Serves as the Abstract implementation of all unsorted maps
 * @author Matthew Kierski
 *
 * @param <K> key of entry
 * @param <V> value of entry
 */
public abstract class AbstractMap<K, V> implements Map<K, V> {

	/**
	 * Serves as the parent class of any entry in a map
	 * @author Matthew Kierski
	 *
	 * @param <K> key of entry
	 * @param <V> value of entry
	 */
	protected static class MapEntry<K, V> implements Entry<K, V> {

		/** Private instance of key */
		private K key;
		/** Private instance value */
		private V value;

		/**
		 * Constructs a MapEntry, setting key and value
		 * @param key key to set
		 * @param value value to set
		 */
		public MapEntry(K key, V value) {
			setKey(key);
			setValue(value);
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		/**
		 * Sets this key to given key
		 * @param key to be set
		 * @return this key
		 */
		public K setKey(K key) {
			this.key = key;
			return this.key;
		}

		@Override
		public V setValue(V value) {
			V original = this.value;
			this.value = value;
			return original;
		}
	}

	/**
	 * Represents an Iterator to traverse the keys of entries
	 * @author Matthew Kierski
	 *
	 */
	protected class KeyIterator implements Iterator<K> {
		
		/** Instance of Iterator of entries */
		private Iterator<Entry<K, V>> it;
		
		/**
		 * Constructs a KeyIterator, setting given iterator to this iterator
		 * @param iterator iterator to be set
		 */
		public KeyIterator(Iterator<Entry<K, V>> iterator) {
			it = iterator;
		}

		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		@Override
		public K next() {
			return it.next().getKey();
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException("The remove operation is not supported yet");
		}
	}
	
	/**
	 * Represents iterator to traverse values of entries
	 * @author Matthew Kierski
	 *
	 */
	protected class ValueIterator implements Iterator<V> {
		
		/** Private instance of entry iterator */
		private Iterator<Entry<K, V>> it;
		
		/**
		 * Constructs an iterator, setting given iterator to this iterator
		 * @param iterator iterator to be used
		 */
		public ValueIterator(Iterator<Entry<K, V>> iterator) {
			it = iterator;
		}

		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		@Override
		public V next() {
			return it.next().getValue();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("The remove operation is not supported yet");
		}
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
	
	@Override
	public Iterator<K> iterator() {
		return new KeyIterator(entrySet().iterator());
	}
	
	@Override
	public Iterable<V> values() {
		return new ValueIterable();
	}
	
	/**
	 * Represents an iterable for traversing values
	 * @author Matthew Kierski
	 *
	 */
	private class ValueIterable implements Iterable<V> {

		@Override
		public Iterator<V> iterator() {
			return new ValueIterator(entrySet().iterator());
		}
	}
}