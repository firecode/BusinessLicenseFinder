package com.travel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.travel.domain.TravelWarning;
import com.travel.reader.TravelWarningReader;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.ExpandableListContextMenuInfo;
import android.widget.TextView;
import android.widget.Toast;

public class TravelWarningsActivity extends ExpandableListActivity {

	ExpandableListAdapter mAdapter;

	SimpleDateFormat fullDateFormat = new SimpleDateFormat(
			"EEE, dd MMM yyyy HH:mm:ss z");

	SimpleDateFormat regularDateFormat = new SimpleDateFormat("MM/dd/yy");

	/** Called when the activity is first created. */
	// @Override
	// public void onCreate(Bundle savedInstanceState) {
	// super.onCreate(savedInstanceState);
	//
	// mAdapter = new MyExpandableListAdapter();
	//
	// TravelWarningReader tr = new TravelWarningReader();
	// List<TravelWarning> travelWarnings = tr.parse();
	//
	// setContentView(R.layout.main);
	// }

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String travelAlertsUrl = "http://travel.state.gov/_res/rss/TAs.xml";
		TravelWarningReader ta = new TravelWarningReader(travelAlertsUrl);
		List<TravelWarning> travelAlerts = ta.parse();

		String travelWarningsUrl = "http://travel.state.gov/_res/rss/TWs.xml";
		TravelWarningReader tw = new TravelWarningReader(travelWarningsUrl);
		List<TravelWarning> travelWarnings = tw.parse();

		travelAlerts.addAll(travelWarnings);

		// List<TravelWarning> travelWarnings = new ArrayList<TravelWarning>();
		// Set up our adapter
		mAdapter = new MyExpandableListAdapter(travelAlerts);
		setListAdapter(mAdapter);
		registerForContextMenu(getExpandableListView());
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.setHeaderTitle("Sample menu");
		menu.add(0, 0, 0, R.string.expandable_list_sample_action);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		ExpandableListContextMenuInfo info = (ExpandableListContextMenuInfo) item
				.getMenuInfo();

		String title = ((TextView) info.targetView).getText().toString();

		int type = ExpandableListView
				.getPackedPositionType(info.packedPosition);
		if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
			int groupPos = ExpandableListView
					.getPackedPositionGroup(info.packedPosition);
			int childPos = ExpandableListView
					.getPackedPositionChild(info.packedPosition);
			Toast.makeText(
					this,
					title + ": Child " + childPos + " clicked in group "
							+ groupPos, Toast.LENGTH_SHORT).show();
			return true;
		} else if (type == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
			int groupPos = ExpandableListView
					.getPackedPositionGroup(info.packedPosition);
			Toast.makeText(this, title + ": Group " + groupPos + " clicked",
					Toast.LENGTH_SHORT).show();
			return true;
		}

		return false;
	}

	/**
	 * A simple adapter which maintains an ArrayList of photo resource Ids. Each
	 * photo is displayed as an image. This adapter supports clearing the list
	 * of photos and adding a new photo.
	 * 
	 */
	public class MyExpandableListAdapter extends BaseExpandableListAdapter {
		// Sample data set. children[i] contains the children (String[]) for
		// groups[i].

		private List<TravelWarning> travelWarningsList = new ArrayList<TravelWarning>();

		public MyExpandableListAdapter(List<TravelWarning> travelWarnings) {
			this.travelWarningsList = travelWarnings;
			groups = new String[travelWarnings.size()];
			children = new String[travelWarnings.size()][1];

			for (int i = 0; i < travelWarnings.size(); i++) {

				String title = travelWarnings.get(i).getTitle();

				// if (travelWarnings.get(i).getPubDate() != null) {
				// try {
				// String dateString = travelWarnings.get(i)
				// .getPubDate().trim();
				// SimpleDateFormat fullDateFormat = new SimpleDateFormat(
				// "EEE, dd MMM yyyy HH:mm:ss z");
				// Date date = fullDateFormat.parse(dateString);
				// SimpleDateFormat regularDateFormat = new SimpleDateFormat(
				// "MM/dd/yy");
				// dateString = regularDateFormat.format(date);
				//
				// title = title + "(" + dateString+ ")";
				// } catch (ParseException e) {
				// Log.e(e.getMessage(), e.getMessage(), e);
				// }
				//
				//
				// }
				groups[i] = title;

				String description = "<div style='font-size: 11px;'>"
						+ travelWarnings.get(i).getDescription() + "</div>";

				if (travelWarnings.get(i).getPubDate() != null) {
					description = "<div style='font-size: 11px; font-weight: bold;font-style:italic;'>"
							+ travelWarnings.get(i).getPubDate()
							+ "</div><br/>" + description;
				}

				children[i][0] = description;
			}

		}

		private String[] groups = { "People Names", "Dog Names", "Cat Names",
				"Fish Names" };
		private String[][] children = {
				{ "Arnold", "Barry", "Chuck", "David" },
				{ "Ace", "Bandit", "Cha-Cha", "Deuce" },
				{ "Fluffy", "Snuggles" }, { "Goldy", "Bubbles" } };

		public Object getChild(int groupPosition, int childPosition) {
			return children[groupPosition][childPosition];
		}

		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		public int getChildrenCount(int groupPosition) {
			return children[groupPosition].length;
		}

		public TextView getGenericView() {
			// Layout parameters for the ExpandableListView
			AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
					ViewGroup.LayoutParams.FILL_PARENT, 40);

			TextView textView = new TextView(TravelWarningsActivity.this);
			textView.setLayoutParams(lp);
			// Center the text vertically
			textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
			// Set the text starting position
			textView.setPadding(36, 0, 0, 0);
			return textView;
		}

		public TextView getChildTextView() {
			// Layout parameters for the ExpandableListView
			AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
					ViewGroup.LayoutParams.FILL_PARENT, 250);

			TextView textView = new TextView(TravelWarningsActivity.this);
			// textView.setLayoutParams(lp);
			// Center the text vertically
			textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
			// Set the text starting position
			textView.setPadding(36, 0, 0, 0);
			// textView.setMovementMethod(ScrollingMovementMethod.getInstance());
			return textView;
		}

		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			TextView textView = getChildTextView();
			textView.invalidate();
			textView.setText(Html.fromHtml(getChild(groupPosition,
					childPosition).toString()));
			// int height_in_pixels = (int) (textView.getLineCount() *
			// textView.getLineHeight() *
			// getResources().getDisplayMetrics().density);
			// AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
			// ViewGroup.LayoutParams.MATCH_PARENT, 250);
			//
			// textView.setLayoutParams(lp);
			// Center the text vertically
			textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
			// Set the text starting position
			textView.setPadding(36, 0, 0, 0);
			textView.setTextSize(11);

			// textView.setHeight(height_in_pixels);
			return textView;
		}

		public Object getGroup(int groupPosition) {
			return groups[groupPosition];
		}

		public int getGroupCount() {
			return groups.length;
		}

		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			TextView textView = getGenericView();
			textView.setText(getGroup(groupPosition).toString());
			return textView;
		}

		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}

		public boolean hasStableIds() {
			return true;
		}

	}
}