/*
 * @author Raghu Ram Bongula
 */
package com.emer;

import java.util.HashMap;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Menu.Item;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.db.ICEDbHelper;
import com.dto.ICEContact;
import com.google.wireless.gdata.data.StringUtils;
import com.util.GlobalStuff;
import com.util.ICEConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class IceActivity.
 */
public class IceActivity extends ListActivity {

	/** The contact names. */
	private String[] contactNames;

	/** The ice db helper. */
	private ICEDbHelper iceDbHelper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		iceDbHelper = new ICEDbHelper(this);
		retrieveICEList();
	}

	/**
	 * Retrieve ice list.
	 */
	private void retrieveICEList() {
		contactNames = iceDbHelper.getAllICEContactStrings();
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, contactNames));
		if (contactNames != null && contactNames.length > 0) {
			this.getListView().setSelection(0);
		}
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
		retrieveICEList();
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onRestart()
	 */
	@Override
	protected void onRestart() {
		super.onRestart();
		retrieveICEList();

	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean supRetVal = super.onCreateOptionsMenu(menu);

		menuItems(menu);
		return supRetVal;
	}

	/**
	 * Menu items.
	 * 
	 * @param menu
	 *            the menu
	 */
	private void menuItems(Menu menu) {
		menu.add(0, 1, getString(R.string.main_menu_new));
	
		if (contactNames != null && contactNames.length > 0) {
			menu.add(0, 5, getString(R.string.main_menu_call));
			menu.add(0, 2, getString(R.string.main_menu_view));
			menu.add(0, 3, getString(R.string.main_menu_edit));
			menu.add(0, 4, getString(R.string.main_menu_delete));
		}
		menu.add(0, 7, getString(R.string.main_menu_exit));

	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onPrepareOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean supRetVal = super.onPrepareOptionsMenu(menu);
		menu.clear();

		menuItems(menu);
		return supRetVal;
	}

	/* (non-Javadoc)
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 */
	protected void onListItemClick(ListView listview, View view, int i, long l1) {

		super.onListItemClick(listview, view, i, l1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onOptionsItemSelected(android.view.Menu.Item)
	 */
	@Override
	public boolean onOptionsItemSelected(Item item) {
		String selectedName = getContactNameSelected();
		HashMap<String, Object> objectMap = GlobalStuff.getObjectMap();
		objectMap.put(ICEConstants.MAIN_SELECTED_CONTACT, selectedName);

		switch (item.getId()) {
		case 3:
			return performEditActivity(selectedName);

		case 5:
			return performCallActivity(selectedName);

		case 4:
			return performDeleteActivity(selectedName);

		case 2:
			return performViewActivity(selectedName);

		case 1:
			return performNewActivity();
		case 7:
			return performExitActivity();
		

		}
		return false;
	}

	/**
	 * Perform exit activity.
	 * 
	 * @return true, if successful
	 */
	private boolean performExitActivity() {
		this.finish();
		iceDbHelper.close();
		return true;
	}

	/**
	 * Perform delete activity.
	 * 
	 * @param selectedName
	 *            the selected name
	 * 
	 * @return true, if successful
	 */
	private boolean performDeleteActivity(String selectedName) {
		ICEContact iceContact = iceDbHelper.getICEContact(selectedName);
		iceDbHelper.deleteRow(iceContact.getId());
		// iceDbHelper.close();
		this.retrieveICEList();
		return true;
	}

	/**
	 * Perform call activity.
	 * 
	 * @param selectedName
	 *            the selected name
	 * 
	 * @return true, if successful
	 */
	private boolean performCallActivity(String selectedName) {
		String phone = iceDbHelper.getPhoneNum(this, selectedName);
		if(!StringUtils.isEmpty(phone)){
		Intent callIntent = new Intent(Intent.CALL_ACTION, Uri.parse("tel:"
				+ phone));
		this.startActivity(callIntent);
		return true;
		}else{
			return false;
		}
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
	 * Perform view activity.
	 * 
	 * @param iceContactName
	 *            the ice contact name
	 * 
	 * @return true, if successful
	 */
	private boolean performViewActivity(String iceContactName) {
		Intent viewIntent = new Intent(this, IceViewActivity.class);
		viewIntent.putExtra(ICEConstants.MAIN_SELECTED_CONTACT, iceContactName);
		startSubActivity(viewIntent, 0);
		return true;
	}

	/**
	 * Perform new activity.
	 * 
	 * @return true, if successful
	 */
	private boolean performNewActivity() {
		Intent newIntent = new Intent(this, IceNewActivity.class);
		startSubActivity(newIntent, 0);
		return true;
	}

	/**
	 * Gets the contact name selected.
	 * 
	 * @return the contact name selected
	 */
	public String getContactNameSelected() {
		int selectedContact = (int) this.getListView().getSelectedItemId();
		if (contactNames != null && contactNames.length > selectedContact) {
			String contactName = contactNames[selectedContact];
			return contactName;
		} else {
			return "";
		}
	}

	/* (non-Javadoc)
	 * @see android.app.ListActivity#onKeyDown(int, android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_CALL) {
			String selectedICE = getContactNameSelected();
			if (!StringUtils.isEmpty(selectedICE)) {
				this.performCallActivity(selectedICE);

			}
			return true;
		} else {

			return super.onKeyDown(keyCode, event);
		}
	}

}