package edu.ncsu.csc316.dsa.queue;

import java.util.NoSuchElementException;

/**
 * A circular array based implementation of a queue
 * 
 * @author Matthew Kierski
 *
 * @param <E> generic element in queue
 */
public class ArrayBasedQueue<E> extends AbstractQueue<E> {

	// Private instance of array
	private E[] data;
	// Private instance of the front of the array
	private int front;
	// Private instance of the rear of the array
	private int rear;
	// Private instance of the size of the array
	private int size;
	// Private instance of the initial capacity
	private int capacity;

	// Default capacity of newly constructed array
	private static final int DEFAULT_CAPACITY = 10;

	/**
	 * Constructs a new array with custom capacity
	 * @param initialCapacity initial capacity of new array
	 */
	@SuppressWarnings("unchecked")
	public ArrayBasedQueue(int initialCapacity) {
		data = (E[]) (new Object[initialCapacity]);
		size = 0;
		capacity = initialCapacity;
	}

	/**
	 * Constructs a new array with default capacity
	 */
	public ArrayBasedQueue() {
		this(DEFAULT_CAPACITY);
		capacity = DEFAULT_CAPACITY;
	}

	@Override
	public void enqueue(E value) {
		ensureCapacity(size + 1);
		data[rear] = value;
		rear = rear + 1 % capacity;
		size++;
	}

	@Override
	public E dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		E removed = data[front];
		front = front + 1 % capacity;
		size--;
		return removed;
	}

	@Override
	public E front() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return data[front];
	}

	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Helper method to ensure capacity by growing array
	 * @param minCapacity minimum capacity needed to add a new value
	 */
	private void ensureCapacity(int minCapacity) {
		int oldCapacity = data.length - 1;
		if (minCapacity > oldCapacity) {
			int newCapacity = (oldCapacity * 2) + 1;
			if (newCapacity < minCapacity) {
				newCapacity = minCapacity;
			}
			
			@SuppressWarnings("unchecked")
			E[] newArray = (E[]) (new Object[newCapacity]);
			for (int i = 0; i < size; i++) {
				newArray[i] = data[front + i % capacity];
			}
			front = 0;
			rear = size;
			data = newArray;
		}
	}
}