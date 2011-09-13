/*
 * @author Raghu Ram Bongula
 */
package com.browse;

import java.util.Collection;
import java.util.Hashtable;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.RectF;
import android.location.Address;

import com.dealdroid.dto.DealAddressObj;
import com.dealdroid.dto.LocationDTO;
import com.dealdroid.nav.StoresMap;

// TODO: Auto-generated Javadoc
/**
 * The Class MyLocationOverlay.
 */
public class MyLocationOverlay extends Overlay {

	/** The activity. */
	private Activity activity;
	
	/** The location. */
	private LocationDTO location;
	
	/** The m map view. */
	private MapView mMapView;

	/**
	 * Instantiates a new my location overlay.
	 * 
	 * @param activity
	 *            the activity
	 * @param location
	 *            the location
	 * @param mMapView
	 *            the m map view
	 */
	public MyLocationOverlay(Activity activity, LocationDTO location,
			MapView mMapView) {
		this.activity = activity;
		this.location = location;
		this.mMapView = mMapView;
	}

	/* (non-Javadoc)
	 * @see com.google.android.maps.Overlay#draw(android.graphics.Canvas, com.google.android.maps.Overlay.PixelCalculator, boolean)
	 */
	@Override
	public void draw(Canvas canvas, PixelCalculator calculator, boolean shadow) {
		super.draw(canvas, calculator, shadow);
		Paint paint = new Paint();
		paint.setTextSize(10);

		Double lat = location.getLatitude() * 1E6;
		Double lng = location.getLongitude() * 1E6;
		Point point = new Point(lat.intValue(), lng.intValue());

		int[] myScreenCoords = new int[2];
		calculator.getPointXY(point, myScreenCoords);

		RectF oval = new RectF(myScreenCoords[0] - 3, myScreenCoords[1] + 3,
				myScreenCoords[0] + 3, myScreenCoords[1] - 3);

		paint.setStyle(Style.FILL);
		paint.setARGB(255, 80, 150, 30); // Nice strong Android-Green

		canvas.drawText(activity.getString(R.string.your_location),
				myScreenCoords[0] + 6, myScreenCoords[1], paint);

		paint.setARGB(80, 156, 192, 36);
		paint.setStrokeWidth(1);
		canvas.drawOval(oval, paint);

		paint.setARGB(255, 0, 0, 0);
		paint.setStyle(Style.STROKE);
		canvas.drawCircle(myScreenCoords[0], myScreenCoords[1], 3, paint);

		Hashtable<String, DealAddressObj> dealsMap = StoresMap.getStores();
		if (dealsMap != null) {
			Collection<DealAddressObj> dealAddressObjs = dealsMap.values();

			if (dealAddressObjs != null && dealAddressObjs.size() > 0) {

				for (DealAddressObj it : dealAddressObjs) {

					if (it != null && it.getAddresses() != null
							&& it.getAddresses().length > 0) {
						Address[] addresses = it.getAddresses();
						for (int i = 0; i < addresses.length; i++) {
							if (addresses[i] != null) {
								putitonMap(addresses[i], calculator, paint,
										canvas, it.getStore());
							}

						}

					}

				}

			}

		}

	}

	/**
	 * Putiton map.
	 * 
	 * @param address
	 *            the address
	 * @param calculator
	 *            the calculator
	 * @param paint
	 *            the paint
	 * @param canvas
	 *            the canvas
	 * @param store
	 *            the store
	 */
	public void putitonMap(Address address, PixelCalculator calculator,
			Paint paint, Canvas canvas, String store) {
		int[] screenCoords = new int[2];
		Double lat = address.getLatitude() * 1E6;
		Double lng = address.getLongitude() * 1E6;
		Point point = new Point(lat.intValue(), lng.intValue());

		calculator.getPointXY(point, screenCoords);
		if (Math.abs(screenCoords[0]) < 2000
				&& Math.abs(screenCoords[1]) < 2000) {

			RectF oval = new RectF(screenCoords[0] - 3, screenCoords[1] + 3,
					screenCoords[0] + 3, screenCoords[1] - 3);

			paint.setStyle(Style.FILL);
			paint.setARGB(255, 255, 0, 0); // Nice red
			canvas.drawText(store, screenCoords[0] + 6, screenCoords[1], paint);

			canvas.drawOval(oval, paint);

			paint.setARGB(255, 0, 0, 0);
			paint.setStyle(Style.STROKE);
			canvas.drawCircle(screenCoords[0], screenCoords[1], 3, paint);

		}
	}

	/* (non-Javadoc)
	 * @see com.google.android.maps.Overlay#onTap(com.google.android.maps.MapView.DeviceType, com.google.android.maps.Point, com.google.android.maps.Overlay.PixelCalculator)
	 */
	@Override
	public boolean onTap(DeviceType deviceType, Point p,
			PixelCalculator calculator) {
		mMapView.displayZoomDialog(8, 0);
		int latE6 = p.getLatitudeE6();
		int longE6 = p.getLongitudeE6();
		Hashtable<String, DealAddressObj> dealsMap = StoresMap.getStores();
		if (dealsMap != null) {
			Collection<DealAddressObj> dealAddressObjs = dealsMap.values();

			if (dealAddressObjs != null && dealAddressObjs.size() > 0) {

				for (DealAddressObj it : dealAddressObjs) {

					if (it != null && it.getAddresses() != null
							&& it.getAddresses().length > 0) {
						Address[] addresses = it.getAddresses();
						for (int i = 0; i < addresses.length; i++) {
							if (addresses[i] != null) {
								int latAdd = (int) (addresses[i].getLatitude() * 1E6);
								int longAdd = (int) (addresses[i]
										.getLongitude() * 1E6);

								int latDiff = latAdd - latE6;
								int longDiff = longAdd - longE6;
								
//								double latDiffFloat = latDiff;
//								double longDiffFloat = longDiff;

								if (latDiff > -3000 && latDiff < 3000
										&& longDiff > -3000 && longDiff < 3000) {

									String store = it.getStore();
									Intent listIntent = new Intent(activity,
											StoreListActivity.class);
									listIntent.putExtra("store", store);
									activity.startSubActivity(listIntent, 0);

								}
							}

						}

					}

				}

			}

		}

		return super.onTap(deviceType, p, calculator);
	}

}