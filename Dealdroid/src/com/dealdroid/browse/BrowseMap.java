/*
 * @author Raghu Ram Bongula
 */
package com.dealdroid.browse;

import java.util.Hashtable;
import java.util.List;

import android.app.ProgressDialog;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Menu;

import com.dealdroid.dto.DealAddressObj;
import com.dealdroid.dto.DealDTO;
import com.dealdroid.dto.LocationDTO;
import com.dealdroid.nav.DealsSearcher;
import com.dealdroid.nav.LocationMap;
import com.dealdroid.nav.StoresMap;
import com.dealdroid.nav.StoresMapper;
import com.dealdroid.rss.RssParser.Item;

// TODO: Auto-generated Javadoc
/**
 * The Class BrowseMap.
 */
public class BrowseMap extends MapActivity {
	
	/** The m map view. */
	private MapView mMapView;
	
	/** The location. */
	private LocationDTO location;

	/* (non-Javadoc)
	 * @see com.google.android.maps.MapActivity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		mMapView = new MapView(this);
		// mMapView.displayZoomDialog(8, 0);
		setContentView(mMapView);
		StoresMap.resetStores();
		// location = NavUtil.getCurrentLocation(this);

		location = LocationMap.getLocationDTO();

		final String zipCode = getIntent().getStringExtra("zipCode");
		if (location != null && zipCode != null) {
			final ProgressDialog lProgressBar = ProgressDialog.show(this,
					"Retrieving Deals Data... ", "", true, false);
			new Thread(new Runnable() {
				public void run() {
					try {
						retrieveData(zipCode);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					lProgressBar.dismiss();
				}
			}).start();

		}

	}

	/**
	 * Retrieve data.
	 * 
	 * @param zipCode
	 *            the zip code
	 */
	private void retrieveData(String zipCode) {
		DealsSearcher dealsSearcher = new DealsSearcher(this);
		List<DealDTO> deals = dealsSearcher.getDeals();

		StoresMapper mapper = new StoresMapper(this, deals);
		mapper.mapDealStores();
		mapper.searchStores(zipCode);
	}

	/**
	 * Put stores on the map.
	 */
	public void putStoresOnTheMap() {
		Hashtable<String, DealAddressObj> stores = StoresMap.getStores();
		if (location != null) {
			int lat = (int) (location.getLatitude() * 1000000);
			int longt = (int) (location.getLongitude() * 1000000);
			Point p = new Point(lat, longt);

			MapController mc = mMapView.getController();
			mc.zoomTo(13);
			mc.centerMapTo(p, false);

			MyLocationOverlay myLocationOverlay = new MyLocationOverlay(this,
					location, mMapView);
			OverlayController overlaycontroller = mMapView
					.createOverlayController();
			overlaycontroller.add(myLocationOverlay, true);
		}
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 1, getString(R.string.menu_satellite_view));
		menu.add(0, 2, getString(R.string.menu_street_view));
		menu.add(0, 3, getString(R.string.menu_traffic_view));
		return super.onCreateOptionsMenu(menu);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.Menu.Item)
	 */
	public boolean onOptionsItemSelected(Item item) {
		switch (item.getId()) {
		case 1:
			mMapView.toggleSatellite();
			return true;
		case 2:
			mMapView.toggleStreetView();
			return true;

		case 3:
			mMapView.toggleTraffic();
			return true;
		case 4:
			mMapView.toggleShowMyLocation();
			return true;
		case 5:
			mMapView.toggleEdgeZooming();
			return true;

		}
		return false;
	}

}