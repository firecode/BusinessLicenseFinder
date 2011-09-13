/*
 * @author Raghu Ram Bongula
 */
package com.util;

import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class GlobalStuff.
 */
public class GlobalStuff {

	/** The object map. */
	public static HashMap<String, Object> objectMap = new HashMap<String, Object>();

	/**
	 * Gets the object map.
	 * 
	 * @return the object map
	 */
	public static HashMap<String, Object> getObjectMap() {
		return objectMap;
	}

	/**
	 * Sets the object map.
	 * 
	 * @param objectMap
	 *            the object map
	 */
	public static void setObjectMap(HashMap<String, Object> objectMap) {
		GlobalStuff.objectMap = objectMap;
	}

}