/*
 * @author Raghu Ram Bongula
 */
package com.emer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.Menu.Item;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.db.ICEDbHelper;
import com.db.PersistentRowICEObj;
import com.dto.Contact;
import com.util.ICEConstants;
import com.util.PhoneContactRetriever;

// TODO: Auto-generated Javadoc
/**
 * The Class IceViewActivity.
 */
public class IceViewActivity extends Activity {

	/** The ice db helper. */
	private ICEDbHelper iceDbHelper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.iceeditor);
		iceDbHelper = new ICEDbHelper(this);
		String[] names = PhoneContactRetriever
				.getAllContactNamesWithPhoneNumber(this);
		if (names == null) {
			names = new String[] { " " };
		}

		Spinner s1 = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
				this, android.R.layout.simple_spinner_item, names);
		adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		s1.setAdapter(adapter);
		s1.setClickable(false);
		EditText e1 = (EditText) findViewById(R.id.editText1);
		String iceName = getIntent().getStringExtra(
				ICEConstants.MAIN_SELECTED_CONTACT);
		e1.setText(iceName);

		String contactName = iceDbHelper.getICEContactByName(iceName)
				.getContactName();
		setSelectedItem(contactName, names, s1);

		e1.setEnabled(false);
		s1.setEnabled(false);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean supRetVal = super.onCreateOptionsMenu(menu);
		menu.add(0, 5, getString(R.string.main_menu_call));
		menu.add(0, 3, getString(R.string.main_menu_edit));
		menu.add(0, 4, getString(R.string.main_menu_delete));
		menu.add(0, 7, getString(R.string.main_menu_exit));
		return supRetVal;
	}

	/**
	 * Sets the selected item.
	 * 
	 * @param contactName
	 *            the contact name
	 * @param names
	 *            the names
	 * @param s1
	 *            the s1
	 */
	public void setSelectedItem(String contactName, String[] names, Spinner s1) {

		if (names != null && names.length > 0) {

			for (int i = 0; i < names.length; i++) {
				if (names[i].equalsIgnoreCase(contactName)) {

					s1.setSelection(i, true);
				}
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onOptionsItemSelected(android.view.Menu.Item)
	 */
	@Override
	public boolean onOptionsItemSelected(Item item) {

		Spinner s1 = (Spinner) findViewById(R.id.spinner1);

		EditText e1 = (EditText) findViewById(R.id.editText1);

		String iceName = e1.getText().toString();
		Object obj = s1.getSelectedItem();
		String contactName = (String) obj;

		PersistentRowICEObj row = iceDbHelper.getICEContactByName(iceName);

		Contact contact = PhoneContactRetriever.getContact(this, contactName);

		switch (item.getId()) {
		case 3:
			return performEditActivity(iceName);

		case 4:
			return performDeleteActivity(row);

		case 5:
			return performCallActivity(contact.getPhone());
			
		case 7:
			return performExitActivity();

		}
		return false;
	}

	private boolean performExitActivity() {
		this.finish();
		iceDbHelper.close();
		return true;
	}
	/**
	 * Perform delete activity.
	 * 
	 * @param row
	 *            the row
	 * 
	 * @return true, if successful
	 */
	private boolean performDeleteActivity(PersistentRowICEObj row) {
		iceDbHelper.deleteRow(row.get_id());
		this.finish();
		return true;
	}

	/**
	 * Perform edit activity.
	 * 
	 * @param iceContactName
	 *            the ice contact name
	 * 
	 * @return true, if successful
	 */
	private boolean performEditActivity(String iceContactName) {
		Intent editIntent = new Intent(this, IceEditActivity.class);
		editIntent.putExtra(ICEConstants.MAIN_SELECTED_CONTACT, iceContactName);
		startSubActivity(editIntent, 0);
		return true;
	}

	/**
	 * Perform call activity.
	 * 
	 * @param phone
	 *            the phone
	 * 
	 * @return true, if successful
	 */
	private boolean performCallActivity(String phone) {
		Intent callIntent = new Intent(Intent.CALL_ACTION, Uri.parse("tel:"
				+ phone));
		this.startActivity(callIntent);
		this.finish();
		return true;
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_CALL) {
			performCallActivity(getSeletedContact().getPhone());
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	/**
	 * Gets the seleted contact.
	 * 
	 * @return the seleted contact
	 */
	private Contact getSeletedContact() {
		Spinner s1 = (Spinner) findViewById(R.id.spinner1);
		Object obj = s1.getSelectedItem();
		String contactName = (String) obj;

		Contact contact = PhoneContactRetriever.getContact(this, contactName);
		return contact;
	}

}
