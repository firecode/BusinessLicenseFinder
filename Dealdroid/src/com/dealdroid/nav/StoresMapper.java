/*
 * @author Raghu Ram Bongula
 */
package com.nav;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import android.app.Activity;
import android.location.Address;

import com.browse.BrowseMap;
import com.browse.R;
import com.dto.DealDTO;
import com.google.android.location.GmmGeocoder;
import com.thread.ComputationGroup;
import com.util.StringUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class StoresMapper.
 */
public class StoresMapper {

	/** The activity. */
	private Activity activity;
	
	/** The deals. */
	private List<DealDTO> deals;

	/**
	 * Instantiates a new stores mapper.
	 * 
	 * @param activity
	 *            the activity
	 * @param deals
	 *            the deals
	 */
	public StoresMapper(Activity activity, List<DealDTO> deals) {
		this.activity = activity;
		this.deals = deals;
	}

	/**
	 * Map deal stores.
	 */
	public void mapDealStores() {
		String[] stores = activity.getResources()
				.getStringArray(R.array.stores);

		if (stores != null && stores.length > 0 && deals != null
				&& deals.size() > 0) {
			for (int i = 0; i < stores.length; i++) {

				boolean dealPresent = false;
				for (DealDTO it : deals) {
					if (it.getTitle() != null && stores[i] != null
							&& stores[i] != "") {

						dealPresent = StringUtil.contains(it.getTitle(),
								stores[i]);
						if (dealPresent) {
							mapDeal(stores[i], it);
						}

					}

				}

			}
		}
	}

	/**
	 * Map deal.
	 * 
	 * @param store
	 *            the store
	 * @param dealDTO
	 *            the deal dto
	 */
	public void mapDeal(String store, DealDTO dealDTO) {
		StoresMap.addDeal(dealDTO, store);
	}

	/**
	 * Search stores.
	 * 
	 * @param zipcode
	 *            the zipcode
	 */
	public void searchStores(String zipcode) {
		Set<String> stores = StoresMap.getStores().keySet();
		if (stores != null && stores.size() > 0) {
			spunThreads(stores, zipcode);
		}
	}

	/**
	 * Search stores without threads.
	 * 
	 * @param zipcode
	 *            the zipcode
	 */
	public void searchStoresWithoutThreads(String zipcode) {
		Set<String> stores = StoresMap.getStores().keySet();
		if (stores != null && stores.size() > 0) {
			loopThruData(stores, zipcode);
		}
	}

	/**
	 * Loop thru data.
	 * 
	 * @param stores
	 *            the stores
	 * @param zipcode
	 *            the zipcode
	 */
	public void loopThruData(Set<String> stores, String zipcode) {
		for (String it : stores) {
			try {
				search(zipcode, it);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Spun threads.
	 * 
	 * @param stores
	 *            the stores
	 * @param zipcode
	 *            the zipcode
	 */
	public void spunThreads(final Set<String> stores, final String zipcode) {
		ComputationGroup compGroup = new ComputationGroup() {
			public void onFinish() {
				if (activity instanceof BrowseMap) {
					((BrowseMap) activity).putStoresOnTheMap();
				}
				// else if (activity instanceof DealListActivity) {
				// ((DealListActivity) activity).putStoresOnTheList();
				// }

			}
		};

		for (String it : stores) {
			final String temp = it;
			Runnable retrieveStoreData = new Runnable() {

				public void run() {
					try {
						search(zipcode, temp);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			};

			compGroup.add(retrieveStoreData);
		}
		compGroup.startComputations();
	}

	/**
	 * Search.
	 * 
	 * @param zipcode
	 *            the zipcode
	 * @param store
	 *            the store
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void search(String zipcode, String store) throws IOException {
		GmmGeocoder geocoder = new GmmGeocoder(Locale.getDefault());
		String query = store + " , " + zipcode;
		Address[] addresses = geocoder.query(query,
				GmmGeocoder.QUERY_TYPE_BUSINESS, 0, 0, 180, 360);
		StoresMap.addAddress(addresses, store);
	}

}
