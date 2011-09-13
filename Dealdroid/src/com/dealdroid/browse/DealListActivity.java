/*
 * @author Raghu Ram Bongula
 */
package com.browse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dealdroid.dto.DealAddressObj;
import com.dealdroid.dto.DealDTO;
import com.dealdroid.dto.LocationDTO;
import com.dealdroid.dto.ProgressBarHolder;
import com.dealdroid.nav.DealsSearcher;
import com.dealdroid.nav.LocationMap;
import com.dealdroid.nav.StoresMap;
import com.dealdroid.nav.StoresMapper;

// TODO: Auto-generated Javadoc
/**
 * The Class DealListActivity.
 */
public class DealListActivity extends ListActivity {

	/** The location. */
	private LocationDTO location;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		StoresMap.resetStores();

		location = LocationMap.getLocationDTO();

		final String zipCode = getIntent().getStringExtra("zipCode");

		if (location != null && zipCode != null) {

			retrieveData(zipCode);

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
		putStoresOnTheList();
	}

	/**
	 * Put stores on the list.
	 */
	public void putStoresOnTheList() {

		Hashtable<String, DealAddressObj> stores = StoresMap.getStores();
		List<String> deals = new ArrayList<String>();
		if (stores != null) {
			Collection<DealAddressObj> dealsObjCollection = stores.values();
			if (dealsObjCollection != null && dealsObjCollection.size() > 0) {
				for (DealAddressObj it : dealsObjCollection) {
					List<DealDTO> dealDTOs = it.getDeals();
					if (dealDTOs != null && dealDTOs.size() > 0) {
						for (DealDTO it1 : dealDTOs) {
							deals.add(it1.getTitle());
						}
					}

				}

			}
		}
		if (deals != null && deals.size() > 0) {
			String[] dealArray = new String[deals.size()];
			int i = 0;
			for (String it : deals) {
				dealArray[i] = it;
				i++;
			}

			ArrayAdapter<String> arrayAdap = getArrayAdapter(dealArray);
			setListAdapter(arrayAdap);

			this.getListView().setSelection(0);

		}
		ProgressDialog progressBar = ProgressBarHolder.getProgressBar();

		if (progressBar != null) {
			progressBar.dismiss();
			ProgressBarHolder.setProgressBar(null);
		}
	}

	/**
	 * Gets the array adapter.
	 * 
	 * @param dealArray
	 *            the deal array
	 * 
	 * @return the array adapter
	 */
	public ArrayAdapter<String> getArrayAdapter(String[] dealArray) {
		ArrayAdapter<String> arrayAdap = new ArrayAdapter<String>(
				DealListActivity.this, android.R.layout.simple_list_item_1,
				dealArray) {

			public View getView(int position, View convertView, ViewGroup parent) {
				TextView txt = new TextView(this.getContext());
				txt.setTextColor(Color.WHITE);
				txt.setTextSize(12);
				txt.setText(this.getItem(position));
				return txt;

			}

		};

		return arrayAdap;
	}

}