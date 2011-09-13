package com.biz;

import java.util.ArrayList;
import java.util.Iterator;

import com.biz.domain.BusinessType;
import com.biz.domain.Result;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class BizLicenserActivity extends Activity {
	/** Called when the activity is first created. */

	private ArrayList<String> bizTypes = new ArrayList<String>();
	private Spinner mbizTypeSpinner;

	private EditText zipcodeText;

	private Button mSearchButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.biz_selector);

		for (String it : BusinessType.LICENSETYPES) {
			bizTypes.add(it);
		}

		// Obtain handles to UI objects
		mbizTypeSpinner = (Spinner) findViewById(R.id.bizlist);
		zipcodeText = (EditText) findViewById(R.id.zipcode);

		// Populate list of account types for phone
		ArrayAdapter<String> adapter;
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		for (String it : BusinessType.LICENSETYPES) {
			adapter.add(it);
		}

		mbizTypeSpinner.setAdapter(adapter);
		mbizTypeSpinner.setPrompt(getString(R.string.selectlabel));

		mSearchButton = (Button) findViewById(R.id.searchlicenses);

		mSearchButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				onSearchButtonClicked();
			}
		});
	}

	public void onSearchButtonClicked() {
		String zipcode = zipcodeText.getText().toString();
		String bizType = bizTypes
				.get(mbizTypeSpinner.getSelectedItemPosition());
		try{
			Result result = BizLicenseRetriever.retrieveLicense(zipcode, bizType);
			BizLicenseRetriever.result = result;
		}catch(Exception e){
			Log.e(e.getMessage(), e.getMessage(),e);
		}
		
		Intent i = new Intent(this, BizLicenseDetailsActivity.class);
        startActivity(i);
	}
}