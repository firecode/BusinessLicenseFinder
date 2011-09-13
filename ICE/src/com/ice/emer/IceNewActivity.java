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
import com.dto.Contact;
import com.google.wireless.gdata.data.StringUtils;
import com.util.PhoneContactRetriever;

// TODO: Auto-generated Javadoc
/**
 * The Class IceNewActivity.
 */
public class IceNewActivity extends Activity {

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
			names = new String[] {};
		}

		Spinner s1 = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
				this, android.R.layout.simple_spinner_item, names);
		adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		s1.setAdapter(adapter);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean supRetVal = super.onCreateOptionsMenu(menu);
		menu.add(0, 6, getString(R.string.main_menu_save));
		menu.add(0, 7, getString(R.string.main_menu_exit));
		return supRetVal;
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

		switch (item.getId()) {
		case 6:
			return performCreateActivity(iceName, contactName);
			
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
	 * Perform create activity.
	 * 
	 * @param iceName
	 *            the ice name
	 * @param contactName
	 *            the contact name
	 * 
	 * @return true, if successful
	 */
	private boolean performCreateActivity(String iceName, String contactName) {

		if (!StringUtils.isEmpty(iceName) && !StringUtils.isEmpty(contactName)) {

			Contact contact = PhoneContactRetriever.getContact(this,
					contactName);
			iceDbHelper.createRow(iceName, contact.getId(), contactName);
			this.finish();
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Perform call activity.
	 * 
	 * @param contact
	 *            the contact
	 * 
	 * @return true, if successful
	 */
	private boolean performCallActivity(Contact contact) {
		Intent callIntent = new Intent(Intent.CALL_ACTION, Uri.parse("tel:"
				+ contact.getPhone()));
		this.startActivity(callIntent);
		return true;
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_CALL) {
			performCallActivity(getSeletedContact());
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