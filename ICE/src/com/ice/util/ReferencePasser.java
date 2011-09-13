/*
 * @author Raghu Ram Bongula
 */
package com.util;

import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class ReferencePasser.
 */
public class ReferencePasser {
	
	/** The _objects. */
	private HashMap<Long, Object> _objects;
	
	/** The _next key. */
	private long _nextKey;

	/**
	 * Instantiates a new reference passer.
	 */
	public ReferencePasser() {
		_objects = new HashMap<Long, Object>();
		_nextKey = 1;
	}

	/**
	 * Adds the given object to the map.
	 * 
	 * @param o
	 *            the o
	 * 
	 * @return the key of object inserted.
	 */
	public long put(Object o) {
		long key = _nextKey++;
		_objects.put(Long.valueOf(key), o);
		return key;
	}

	/**
	 * Gets the.
	 * 
	 * @param key
	 *            the key
	 * 
	 * @return the object with the given key, or null if key doesn't exist in
	 *         the map.
	 */
	public Object get(long key) {
		return _objects.get(Long.valueOf(key));
	}

	/**
	 * Removes the object mapped to the given key from the map.
	 * 
	 * @param key
	 *            the key
	 */
	public void remove(long key) {
		_objects.remove(Long.valueOf(key));
	}
}