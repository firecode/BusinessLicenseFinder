/*
 * @author Raghu Ram Bongula
 */
package com.util;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;

import com.dto.Contact;

// TODO: Auto-generated Javadoc
/**
 * The Class PhoneContactRetriever.
 */
public class PhoneContactRetriever {

	/** The Constant PHONE_NUMBER_PROJECTION. */
	private static final String[] PHONE_NUMBER_PROJECTION = new String[] {
			android.provider.BaseColumns._ID,
			android.provider.Contacts.PeopleColumns.NAME,
			android.provider.Contacts.PhonesColumns.NUMBER,
			android.provider.Contacts.PeopleColumns.PHOTO };

	/**
	 * Gets the all contacts with phone number.
	 * 
	 * @param activity
	 *            the activity
	 * 
	 * @return the all contacts with phone number
	 */
	public static List<Contact> getAllContactsWithPhoneNumber(Activity activity) {
		List<Contact> list = new ArrayList<Contact>();
		Cursor peopleCur = activity.managedQuery(
				android.provider.Contacts.Phones.CONTENT_URI,
				PHONE_NUMBER_PROJECTION, null, null);
		if (!peopleCur.first()) // The contacts database is empty
		{
			peopleCur.close();
			return list;
		}

		do {
			Contact contact = new Contact();
			contact.setName(peopleCur.getString(1));
			contact.setPhone(peopleCur.getString(2));
			contact.setPhotoPath(peopleCur.getString(3));
			list.add(contact);
		} while (peopleCur.next());

		return list;
	}

	/**
	 * Gets the all contact names with phone number.
	 * 
	 * @param activity
	 *            the activity
	 * 
	 * @return the all contact names with phone number
	 */
	public static String[] getAllContactNamesWithPhoneNumber(Activity activity) {
		List<Contact> contacts = PhoneContactRetriever
				.getAllContactsWithPhoneNumber(activity);
		if (contacts != null && contacts.size() > 0) {
			String[] names = new String[contacts.size()];
			for (int i = 0; i < contacts.size(); i++) {
				names[i] = contacts.get(i).getName();
			}
			return names;
		} else {
			return null;
		}
	}

	/**
	 * Gets the contact.
	 * 
	 * @param activity
	 *            the activity
	 * @param name
	 *            the name
	 * 
	 * @return the contact
	 */
	public static Contact getContact(Activity activity, String name) {
		Uri contactRecord = android.provider.Contacts.People.CONTENT_URI;
		Builder contactBuilder = new Builder();
		contactBuilder.appendQueryParameter(
				android.provider.Contacts.PeopleColumns.NAME, name);
		// contactRecord.
		Cursor contactCur = activity.managedQuery(contactRecord,
				PHONE_NUMBER_PROJECTION, null, null);

		if (contactCur != null) {
			if (contactCur.count() > 0) {
				while (contactCur.next()) {
					String itName = contactCur.getString(1);
					if(itName.equalsIgnoreCase(name)){
					Contact contact = new Contact();
					contact.setId(contactCur.getLong(0));
					contact.setName(contactCur.getString(1));
					contact.setPhone(contactCur.getString(2));
					contact.setPhotoPath(contactCur.getString(3));
					contactCur.close();
					return contact;
					}
					
				}
			} else {
				contactCur.close();
				
			}

		} 
		return null;
	}
}
