/*
 * @author Raghu Ram Bongula
 */
package com.nav;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.dto.DealAddressObj;
import com.dto.DealDTO;

import android.location.Address;

// TODO: Auto-generated Javadoc
/**
 * The Class StoresMap.
 */
public class StoresMap {

	/** The stores. */
	public static Hashtable<String, DealAddressObj> stores = new Hashtable<String, DealAddressObj>();

	/**
	 * Adds the address.
	 * 
	 * @param addresses
	 *            the addresses
	 * @param store
	 *            the store
	 */
	public static synchronized void addAddress(Address[] addresses, String store) {

		DealAddressObj dealAddressObj = stores.get(store);
		if (dealAddressObj == null) {
			dealAddressObj = new DealAddressObj();
			dealAddressObj.setStore(store);
		}
		if (addresses != null && addresses.length > 0) {
			dealAddressObj.setAddresses(addresses);
			stores.put(store, dealAddressObj);
		}

	}

	/**
	 * Adds the deal.
	 * 
	 * @param dealDTO
	 *            the deal dto
	 * @param store
	 *            the store
	 */
	public static void addDeal(DealDTO dealDTO, String store) {
		DealAddressObj dealAddressObj = stores.get(store);
		if (dealAddressObj == null) {
			dealAddressObj = new DealAddressObj();
			dealAddressObj.setStore(store);
		}
		List<DealDTO> dealDTOs = dealAddressObj.getDeals();
		if (dealDTOs == null) {
			dealDTOs = new ArrayList<DealDTO>();
		}
		if (dealDTO != null) {
			dealDTOs.add(dealDTO);
			dealAddressObj.setDeals(dealDTOs);
			stores.put(store, dealAddressObj);
		}
	}

	/**
	 * Gets the stores.
	 * 
	 * @return the stores
	 */
	public static Hashtable<String, DealAddressObj> getStores() {
		return stores;
	}
	
	/**
	 * Reset stores.
	 */
	public static void resetStores() {
		stores = new Hashtable<String, DealAddressObj>();
	}

}
