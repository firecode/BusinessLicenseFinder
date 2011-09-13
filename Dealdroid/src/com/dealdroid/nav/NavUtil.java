/*
 * @author Raghu Ram Bongula
 */
package com.nav;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;

import com.google.googlenav.map.MapPoint;

// TODO: Auto-generated Javadoc
/**
 * The Class NavUtil.
 */
public class NavUtil {

	/**
	 * Gets the current location.
	 * 
	 * @param activity
	 *            the activity
	 * 
	 * @return the current location
	 */
	public static Location getCurrentLocation(Activity activity) {
		Object systemService = activity
				.getSystemService(Context.LOCATION_SERVICE);

		LocationManager locationManager = (LocationManager) systemService;
		List<LocationProvider> locationProviders = locationManager
				.getProviders();
		Location location = null;

		if (locationProviders != null && locationProviders.size() > 0) {

			location = locationManager.getCurrentLocation(locationProviders
					.get(0).getName());

		}
		return location;
	}

	/**
	 * Gets the current map point.
	 * 
	 * @param activity
	 *            the activity
	 * 
	 * @return the current map point
	 */
	public static MapPoint getCurrentMapPoint(Activity activity) {
		MapPoint mapPoint = null;
		Location location = NavUtil.getCurrentLocation(activity);
		if (location != null) {
			double latitude = location.getLatitude();
			double longitude = location.getLongitude();
			int lat = (int) (latitude * 1000000);
			int longt = (int) (longitude * 1000000);
			mapPoint = new MapPoint(lat, longt);

		}
		return mapPoint;
	}

}
