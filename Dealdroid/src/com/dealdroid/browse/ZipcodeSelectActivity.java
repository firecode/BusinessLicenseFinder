/*
 * @author Raghu Ram Bongula
 */
package com.browse;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.dealdroid.dto.LocationDTO;
import com.dealdroid.dto.ProgressBarHolder;
import com.dealdroid.nav.LocationFinder;
import com.dealdroid.nav.LocationMap;
import com.dealdroid.util.StringUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class ZipcodeSelectActivity.
 */
public class ZipcodeSelectActivity extends Activity {

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.locationselecter);
		LocationMap.setLocationDTO(null);
		postCreate();

	}

	/**
	 * Post create.
	 */
	private void postCreate() {

		String[] options = this.getResources().getStringArray(R.array.options);
		if (options == null) {
			options = new String[] { " " };
		}

		Spinner s1 = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
				this, android.R.layout.simple_spinner_item, options);
		adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		s1.setAdapter(adapter);
		EditText e1 = (EditText) findViewById(R.id.editText1);

		Button button = (Button) findViewById(R.id.find);

		OnClickListener test = new OnClickListener() {
			public void onClick(View v) {
				onClickButton();
			}
		};
		button.setOnClickListener(test);

	}

	/**
	 * On click button.
	 */
	public void onClickButton() {
		LocationMap.setLocationDTO(null);
		EditText e1 = (EditText) findViewById(R.id.editText1);
		Editable textEdit = e1.getText();
		if (textEdit != null) {
			String zipcodeText = textEdit.toString();

			if (zipcodeText != null && StringUtil.isNumeric(zipcodeText.trim())
					&& zipcodeText.trim().length() == 5) {
				zipcodeText = zipcodeText.trim();
				LocationDTO location = LocationFinder.getLocation(zipcodeText);

				if (location != null) {
					LocationMap.setLocationDTO(location);

					Spinner s1 = (Spinner) findViewById(R.id.spinner1);
					String selected = (String) s1.getSelectedItem();

					if (selected.equalsIgnoreCase("Map")) {

						Intent mapIntent = new Intent(this, BrowseMap.class);
						mapIntent.putExtra("zipCode", zipcodeText);
						startSubActivity(mapIntent, 0);
					} else if (selected.equalsIgnoreCase("List")) {

						startListIntent(zipcodeText);
					}

				}
			}
		}
	}

	/**
	 * Start list intent.
	 * 
	 * @param zipcodeText
	 *            the zipcode text
	 */
	private void startListIntent(String zipcodeText) {
		ProgressBarHolder.setProgressBar(null);
		final String zipcode = zipcodeText;
		final ProgressDialog lProgressBar = ProgressDialog.show(this,
				"Retrieving Deals Data... ", "", true, false);
		new Thread(new Runnable() {
			public void run() {
				try {
					processListIntent(zipcode, lProgressBar);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}).start();
	}

	/**
	 * Process list intent.
	 * 
	 * @param zipcode
	 *            the zipcode
	 * @param lProgressBar
	 *            the l progress bar
	 */
	private void processListIntent(String zipcode, ProgressDialog lProgressBar) {
		ProgressBarHolder.setProgressBar(lProgressBar);
		Intent listIntent = new Intent(this, DealListActivity.class);
		listIntent.putExtra("zipCode", zipcode);
		startSubActivity(listIntent, 0);
	}

}
