/*
 * @author Raghu Ram Bongula
 */
package com.nav;

import com.dto.LocationDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class LocationMap.
 */
public class LocationMap {

	/** The location dto. */
	public static LocationDTO locationDTO;

	/**
	 * Gets the location dto.
	 * 
	 * @return the location dto
	 */
	public static LocationDTO getLocationDTO() {
		return locationDTO;
	}

	/**
	 * Sets the location dto.
	 * 
	 * @param locationDTO
	 *            the new location dto
	 */
	public static void setLocationDTO(LocationDTO locationDTO) {
		LocationMap.locationDTO = locationDTO;
	}

}
