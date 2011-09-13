/*
 * @author Raghu Ram Bongula
 */
package com.nav;

import java.io.IOException;
import java.util.Locale;

import android.location.Address;

import com.dto.LocationDTO;
import com.google.android.location.GmmGeocoder;

// TODO: Auto-generated Javadoc
/**
 * The Class LocationFinder.
 */
public class LocationFinder {

	/**
	 * Gets the location.
	 * 
	 * @param zipcode
	 *            the zipcode
	 * 
	 * @return the location
	 */
	public static LocationDTO getLocation(String zipcode) {
		GmmGeocoder gmmGeo = new GmmGeocoder(Locale.getDefault());
		LocationDTO location = null;
		try {
			Address[] addresses = gmmGeo.query(zipcode,
					GmmGeocoder.QUERY_TYPE_LOCATION, 0, 0, 180, 360);
			if (addresses != null && addresses.length > 0) {
				Address address = addresses[0];
				location = new LocationDTO();
				location.setLatitude(address.getLatitude());
				location.setLongitude(address.getLongitude());
				location.setLatitudeE6((int) (address.getLatitude() * 1E6));
				location.setLongitudeE6((int) (address.getLongitude() * 1E6));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return location;
	}

}
