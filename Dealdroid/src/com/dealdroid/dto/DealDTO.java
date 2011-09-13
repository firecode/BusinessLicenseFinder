/*
 * @author Raghu Ram Bongula
 */
package com.dto;

import java.util.StringTokenizer;

// TODO: Auto-generated Javadoc
/**
 * The Class DealDTO.
 */
public class DealDTO {

	/** The title. */
	private String title;
	
	/** The desc. */
	private String desc;
	
	/** The link. */
	private String link;
	
	/** The store. */
	private String store;
	
	/** The price. */
	private String price;

	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 * 
	 * @param title
	 *            the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the desc.
	 * 
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * Sets the desc.
	 * 
	 * @param desc
	 *            the new desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * Gets the link.
	 * 
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * Sets the link.
	 * 
	 * @param link
	 *            the new link
	 */
	public void setLink(String link) {
		this.link = link;
	}

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
	 * Gets the price.
	 * 
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 * 
	 * @param price
	 *            the new price
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * Process price.
	 * 
	 * @param title
	 *            the title
	 */
	public void processPrice(String title) {
		try {
			if (title != null) {
				StringTokenizer sTokenizer = new StringTokenizer(title, "$");
				sTokenizer.nextToken();
				String token1 = sTokenizer.nextToken();
				StringTokenizer sTokenizer1 = new StringTokenizer(token1, " ");
				this.price = sTokenizer1.nextToken();
			} else {
				this.price = "0.00";
			}
		} catch (Exception e) {
			this.price = "0.00";
		}
	}

	/**
	 * Process store.
	 * 
	 * @param title
	 *            the title
	 */
	public void processStore(String title) {
		try {
			if (title != null) {
				StringTokenizer sTokenizer = new StringTokenizer(title, " at ");
				String token = sTokenizer.nextToken();
				String token1 = sTokenizer.nextToken();
				// StringTokenizer sTokenizer1 = new StringTokenizer(token1,"
				// ");
				this.store = token1;
			} else {
				this.store = " ";
			}
		} catch (Exception e) {
			this.store = " ";
		}
	}

}
