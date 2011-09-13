/*
 * @author Raghu Ram Bongula
 */
package com.browse;

import java.util.Hashtable;
import java.util.List;

import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dealdroid.dto.DealAddressObj;
import com.dealdroid.dto.DealDTO;
import com.dealdroid.nav.StoresMap;

// TODO: Auto-generated Javadoc
/**
 * The Class StoreListActivity.
 */
public class StoreListActivity extends ListActivity {

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		Hashtable<String, DealAddressObj> storesMap = StoresMap.getStores();

		String store = getIntent().getStringExtra("store");

		DealAddressObj deals = storesMap.get(store);

		List<DealDTO> dealDTOs = deals.getDeals();

		String[] storeDeals = new String[0];

		if (dealDTOs != null && dealDTOs.size() > 0) {

			storeDeals = new String[dealDTOs.size()];
			int i = 0;
			for (DealDTO it : dealDTOs) {
				storeDeals[i] = it.getTitle();
				i++;

			}
			
		}

		ArrayAdapter<String> arrayAdap = getArrayAdapter(storeDeals);
		setListAdapter(arrayAdap);
		getListView().setSelection(0);

	}
	
	/**
	 * Gets the array adapter.
	 * 
	 * @param storeDeals
	 *            the store deals
	 * 
	 * @return the array adapter
	 */
	public ArrayAdapter<String> getArrayAdapter(String[] storeDeals) {
		ArrayAdapter<String> arrayAdap = new ArrayAdapter<String>(
				this, android.R.layout.simple_list_item_1,
				storeDeals) {

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
