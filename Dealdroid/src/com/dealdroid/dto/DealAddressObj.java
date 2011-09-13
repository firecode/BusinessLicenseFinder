/*
 * @author Raghu Ram Bongula
 */
package com.dto;

import java.util.List;

import android.location.Address;

// TODO: Auto-generated Javadoc
/**
 * The Class DealAddressObj.
 */
public class DealAddressObj {

	/** The store. */
	private String store;
	
	/** The addresses. */
	private Address[] addresses;
	
	/** The deals. */
	private List<DealDTO> deals;

	/**
	 * Gets the store.
	 * 
	 * @return the store
	 */
	public String getStore() {
		return store;
	}

	/**
	 * Sets the store.
	 * 
	 * @param store
	 *            the new store
	 */
	public void setStore(String store) {
		this.store = store;
	}

	/**
	 * Gets the addresses.
	 * 
	 * @return the addresses
	 */
	public Address[] getAddresses() {
		return addresses;
	}

	/**
	 * Sets the addresses.
	 * 
	 * @param addresses
	 *            the new addresses
	 */
	public void setAddresses(Address[] addresses) {
		this.addresses = addresses;
	}

	/**
	 * Gets the deals.
	 * 
	 * @return the deals
	 */
	public List<DealDTO> getDeals() {
		return deals;
	}

	/**
	 * Sets the deals.
	 * 
	 * @param deals
	 *            the new deals
	 */
	public void setDeals(List<DealDTO> deals) {
		this.deals = deals;
	}

}
