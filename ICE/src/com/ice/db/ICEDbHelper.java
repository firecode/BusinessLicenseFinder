/*
 * @author Raghu Ram Bongula
 */
package com.db;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.dto.Contact;
import com.dto.ICEContact;
import com.util.PhoneContactRetriever;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

// TODO: Auto-generated Javadoc
/**
 * The Class ICEDbHelper.
 */
public class ICEDbHelper {

	/** The Constant DATABASE_NAME. */
	private static final String DATABASE_NAME = "ICE";

	/** The Constant DATABASE_TABLE. */
	private static final String DATABASE_TABLE = "ICECONTACTSTEST";

	/** The Constant TABLE_COLUMNS. */
	private static final String[] TABLE_COLUMNS = new String[] { "_id", "name",
			"contactId", "contactName" };

	/** The Constant TABLE_CREATE. */
	private static final String TABLE_CREATE = "create table " + DATABASE_TABLE
			+ "(_id integer primary key autoincrement, "
			+ "name text not null," + "contactId integer not null,"
			+ "contactName text" + ");";

	/** The Constant TABLE_SELECT. */
	private static final String TABLE_SELECT = "select * from "
			+ DATABASE_TABLE;

	/** The Constant DATABASE_VERSION. */
	private static final int DATABASE_VERSION = 1;

	/** The db. */
	private SQLiteDatabase db;

	/**
	 * Instantiates a new iCE db helper.
	 * 
	 * @param ctx
	 *            the ctx
	 */
	public ICEDbHelper(Context ctx) {
		try {
			db = ctx.openDatabase(DATABASE_NAME, null);
			try {
				db.query(DATABASE_TABLE, null, null, null, null, null, null);
			} catch (SQLException e) {
				db.execSQL(TABLE_CREATE);
			}
		} catch (FileNotFoundException e) {
			try {
				db = ctx.createDatabase(DATABASE_NAME, DATABASE_VERSION, 0,
						null);
				db.execSQL(TABLE_CREATE);
			} catch (FileNotFoundException e1) {
				db = null;
			}
		}
	}

	/**
	 * Instantiates a new iCE db helper.
	 * 
	 * @param ctx
	 *            the ctx
	 * @param string
	 *            the string
	 */
	public ICEDbHelper(Context ctx, String string) {
		try {
			db = ctx.openDatabase(DATABASE_NAME, null);
			try {
				db.execSQL(TABLE_SELECT);
			} catch (SQLException e) {
				db.execSQL(TABLE_CREATE);
			}
		} catch (FileNotFoundException e) {
			Log.e("Exception on opening database", e.toString());
		}
	}

	/**
	 * Close.
	 */
	public void close() {
		db.close();
	}

	/**
	 * Creates the row.
	 * 
	 * @param name
	 *            the name
	 * @param contactId
	 *            the contact id
	 * @param contactName
	 *            the contact name
	 */
	public void createRow(String name, long contactId, String contactName) {
		ContentValues initialValues = new ContentValues();
		initialValues.put("name", name);
		initialValues.put("contactId", contactId);
		initialValues.put("contactName", contactName);
		db.insert(DATABASE_TABLE, null, initialValues);
	}

	/**
	 * Delete row.
	 * 
	 * @param rowId
	 *            the row id
	 */
	public void deleteRow(long rowId) {
		db.delete(DATABASE_TABLE, "_id=" + rowId, null);
	}

	/**
	 * Fetch all rows.
	 * 
	 * @return the list< persistent row ice obj>
	 */
	public List<PersistentRowICEObj> fetchAllRows() {
		List<PersistentRowICEObj> ret = new ArrayList<PersistentRowICEObj>();
		try {
			Cursor c = db.query(DATABASE_TABLE, TABLE_COLUMNS, null, null,
					null, null, null);
			int numRows = c.count();
			c.first();
			for (int i = 0; i < numRows; ++i) {
				PersistentRowICEObj row = new PersistentRowICEObj();
				row._id = c.getLong(0);
				row.name = c.getString(1);
				row.contactId = c.getLong(2);
				row.contactName = c.getString(3);
				ret.add(row);
				c.next();
			}
			c.close();
		} catch (SQLException e) {
			Log.e("Exception on query", e.toString());
		}
		return ret;
	}

	/**
	 * Fetch row.
	 * 
	 * @param rowId
	 *            the row id
	 * 
	 * @return the persistent row ice obj
	 */
	public PersistentRowICEObj fetchRow(long rowId) {
		PersistentRowICEObj row = new PersistentRowICEObj();
		Cursor c = db.query(true, DATABASE_TABLE, TABLE_COLUMNS,
				"_id=" + rowId, null, null, null, null);
		if (c.count() > 0) {
			c.first();
			row._id = c.getLong(0);
			row.name = c.getString(1);
			row.contactId = c.getLong(2);
			row.contactName = c.getString(3);
			return row;
		} else {
			row._id = -1;
			row.name = row.contactName = null;
			row.contactId = 0;
		}
		c.close();
		return row;
	}

	/**
	 * Gets the iCE contact by name.
	 * 
	 * @param name
	 *            the name
	 * 
	 * @return the iCE contact by name
	 */
	public PersistentRowICEObj getICEContactByName(String name) {
		PersistentRowICEObj row = new PersistentRowICEObj();
		Cursor c = db.query(true, DATABASE_TABLE, TABLE_COLUMNS, "name= '"
				+ name + "' ", null, null, null, null);
		if (c.count() > 0) {
			c.first();
			row._id = c.getLong(0);
			row.name = c.getString(1);
			row.contactId = c.getLong(2);
			row.contactName = c.getString(3);
			return row;
		} else {
			row._id = -1;
			row.name = row.contactName = null;
			row.contactId = 0;
		}
		c.close();
		return row;
	}

	/**
	 * Gets the iCE contact.
	 * 
	 * @param iceContactName
	 *            the ice contact name
	 * 
	 * @return the iCE contact
	 */
	public ICEContact getICEContact(String iceContactName) {
		PersistentRowICEObj rowObj = getICEContactByName(iceContactName);
		ICEContact iceContact = new ICEContact();
		iceContact.setId(rowObj.get_id());
		iceContact.setContactId(rowObj.getContactId());
		iceContact.setContactName(rowObj.getContactName());
		iceContact.setIceName(rowObj.getName());
		return iceContact;
	}

	/**
	 * Update row.
	 * 
	 * @param rowId
	 *            the row id
	 * @param name
	 *            the name
	 * @param contactId
	 *            the contact id
	 * @param contactName
	 *            the contact name
	 */
	public void updateRow(long rowId, String name, long contactId,
			String contactName) {
		ContentValues args = new ContentValues();
		args.put("name", name);
		args.put("contactId", contactId);
		args.put("contactName", contactName);
		db.update(DATABASE_TABLE, args, "_id=" + rowId, null);
	}

	/**
	 * Insert row.
	 * 
	 * @param rowId
	 *            the row id
	 * @param name
	 *            the name
	 * @param contactId
	 *            the contact id
	 * @param contactName
	 *            the contact name
	 */
	public void insertRow(long rowId, String name, long contactId,
			String contactName) {
		ContentValues args = new ContentValues();
		args.put("name", name);
		args.put("contactId", contactId);
		args.put("contactName", contactName);
		db.replace(DATABASE_TABLE, null, args);
	}

	/**
	 * Gets the all rows.
	 * 
	 * @return the all rows
	 */
	public Cursor getAllRows() {
		try {
			return db.query(DATABASE_TABLE, TABLE_COLUMNS, null, null, null,
					null, null);
		} catch (SQLException e) {
			Log.e("Exception on query", e.toString());
			return null;
		}
	}

	/**
	 * Gets the all ice contacts.
	 * 
	 * @return the all ice contacts
	 */
	public List<ICEContact> getAllICEContacts() {
		Cursor cursor = getAllRows();
		// Cursor cursor = db.query(DATABASE_TABLE, TABLE_COLUMNS, null, null,
		// null, null, null);
		if (cursor != null && cursor.count() > 0) {
			List<ICEContact> iceContacts = new ArrayList<ICEContact>();
			while (cursor.next()) {
				ICEContact iceContact = new ICEContact();
				iceContact.setId(cursor.getLong(0));
				iceContact.setIceName(cursor.getString(1));
				iceContact.setContactId(cursor.getLong(2));
				iceContact.setContactName(cursor.getString(3));
				iceContacts.add(iceContact);
			}
			cursor.close();
			return iceContacts;
		} else {
			return new ArrayList<ICEContact>();
		}
	}

	/**
	 * Gets the all ice contact strings.
	 * 
	 * @return the all ice contact strings
	 */
	public String[] getAllICEContactStrings() {
		List<ICEContact> iceContacts = getAllICEContacts();
		if (iceContacts != null && iceContacts.size() > 0) {
			String[] iceNames = new String[iceContacts.size()];
			for (int i = 0; i < iceContacts.size(); i++) {
				String its = iceContacts.get(i).getIceName();
				iceNames[i] = its;
			}
			return iceNames;
		} else {
			return new String[] {};
		}
	}

	/**
	 * Gets the phone num.
	 * 
	 * @param activity
	 *            the activity
	 * @param iceContactName
	 *            the ice contact name
	 * 
	 * @return the phone num
	 */
	public String getPhoneNum(Activity activity, String iceContactName) {
		ICEContact iceContact = getICEContact(iceContactName);
		Contact contact = PhoneContactRetriever.getContact(activity, iceContact
				.getContactName());
		if (contact != null) {
			return contact.getPhone();
		} else {
			return "";
		}
	}
}