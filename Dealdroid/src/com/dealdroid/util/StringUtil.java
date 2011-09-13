/*
 * @author Raghu Ram Bongula
 */
package com.util;

// TODO: Auto-generated Javadoc
/**
 * The Class StringUtil.
 */
public class StringUtil {

	/**
	 * Contains.
	 * 
	 * @param str
	 *            the str
	 * @param searchStr
	 *            the search str
	 * 
	 * @return true, if successful
	 */
	public static boolean contains(String str, String searchStr) {
		if (str == null || searchStr == null)
			return false;
		else
			return str.indexOf(searchStr) >= 0;
	}
	
	 /**
	 * Checks if is numeric.
	 * 
	 * @param str
	 *            the str
	 * 
	 * @return true, if is numeric
	 */
 	public static boolean isNumeric(String str)
	    {
	        if(str == null)
	            return false;
	        int sz = str.length();
	        for(int i = 0; i < sz; i++)
	            if(!Character.isDigit(str.charAt(i)))
	                return false;

	        return true;
	    }


}
