/*
 * @author Raghu Ram Bongula
 */
package com.dto;

import android.os.Parcel;

// TODO: Auto-generated Javadoc
/**
 * The Class ICEContact.
 */
public class ICEContact {
	
	/** The id. */
	private long id;
	
	/** The ice name. */
	private String iceName;
	
	/** The contact id. */
	private long contactId;
	
	/** The contact name. */
	private String contactName;

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the ice name.
	 * 
	 * @return the ice name
	 */
	public String getIceName() {
		return iceName;
	}

	/**
	 * Sets the ice name.
	 * 
	 * @param iceName
	 *            the new ice name
	 */
	public void setIceName(String iceName) {
		this.iceName = iceName;
	}

	/**
	 * Gets the contact id.
	 * 
	 * @return the contact id
	 */
	public long getContactId() {
		return contactId;
	}

	/**
	 * Sets the contact id.
	 * 
	 * @param contactId
	 *            the new contact id
	 */
	public void setContactId(long contactId) {
		this.contactId = contactId;
	}

	/**
	 * Gets the contact name.
	 * 
	 * @return the contact name
	 */
	public String getContactName() {
		return contactName;
	}

	/**
	 * Sets the contact name.
	 * 
	 * @param contactName
	 *            the new contact name
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	/**
	 * Write to parcel.
	 * 
	 * @param out
	 *            the out
	 */
	public void writeToParcel(Parcel out) {
		out.writeLong(id);
		out.writeString(iceName);
		out.writeLong(contactId);
		out.writeString(contactName);
	}

}
