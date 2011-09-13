/*
 * @author Raghu Ram Bongula
 */
package com.dto;

import android.os.Parcel;
import android.os.Parcelable;


// TODO: Auto-generated Javadoc
/**
 * The Class Contact.
 */
public class Contact implements Parcelable{

	/** The id. */
	private long id;
	
	/** The name. */
	private String name;
	
	/** The phone. */
	private String phone;
	
	/** The email. */
	private String email;
	
	/** The title. */
	private String title;
	
	/** The notes. */
	private String notes;
	
	/** The photo path. */
	private String photoPath;

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the phone.
	 * 
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the phone.
	 * 
	 * @param phone
	 *            the new phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Gets the email.
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 * 
	 * @param email
	 *            the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

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
	 * Gets the notes.
	 * 
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * Sets the notes.
	 * 
	 * @param notes
	 *            the new notes
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * Gets the photo path.
	 * 
	 * @return the photo path
	 */
	public String getPhotoPath() {
		return photoPath;
	}

	/**
	 * Sets the photo path.
	 * 
	 * @param photoPath
	 *            the new photo path
	 */
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

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

	/* (non-Javadoc)
	 * @see android.os.Parcelable#writeToParcel(android.os.Parcel)
	 */
	@Override
	public void writeToParcel(Parcel out) {
		out.writeLong(id);
		out.writeString(name);
		out.writeString(phone);
		out.writeString(email);
		out.writeString(title);
		out.writeString(notes);
		out.writeString(photoPath);
	}

	

}
