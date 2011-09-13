package com.biz;

import java.text.SimpleDateFormat;
import java.util.List;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.text.Html;
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

import com.biz.domain.Result;
import com.biz.domain.Site;

public class BizLicenseDetailsActivity extends ExpandableListActivity {

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

		// List<TravelWarning> travelWarnings = new ArrayList<TravelWarning>();
		// Set up our adapter
		mAdapter = new MyExpandableListAdapter();
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

		public MyExpandableListAdapter() {
			Result result = BizLicenseRetriever.result;

			children = new String[5][];

			String[] stateChildrenItem = getChildArray(result.getState_sites());
			children[0] = stateChildrenItem;

			String[] bizTypeChildrenItem = getChildArray(result
					.getSites_for_business_type());
			children[1] = bizTypeChildrenItem;

			String[] catChildrenItem = getChildArray(result
					.getSites_for_category());
			children[2] = catChildrenItem;

			String[] countyChildrenItem = getChildArray(result
					.getCounty_sites());
			children[3] = countyChildrenItem;

			String[] cityChildrenItem = getChildArray(result.getLocal_sites());
			children[4] = cityChildrenItem;

		}

		/**
		 * @param result
		 * @param countyChildrenItem
		 */
		private String[] getChildArray(List<Site> sites) {
			String[] countyChildrenItem = new String[sites.size()];
			for (int i = 0; i < sites.size(); i++) {
				String description = "";

				if (sites.get(i).getCounty() != null
						&& !sites.get(i).getCounty().trim().equals("")) {
					description = description + "County: "
							+ sites.get(i).getCounty() + "ì<br/>";
				}

				if (sites.get(i).getState() != null
						&& !sites.get(i).getState().trim().equals("")) {
					description = description + "State: "
							+ sites.get(i).getState() + "ì<br/>";
				}

				if (sites.get(i).getUrl() != null
						&& !sites.get(i).getUrl().trim().equals("")) {
					String title = sites.get(i).getUrl();
					if (sites.get(i).getLink_title() != null
							&& !sites.get(i).getLink_title().trim().equals("")) {
						title = sites.get(i).getLink_title();
					}

					String link = "Website: <a href='" + sites.get(i).getUrl()
							+ "'>" + title + "</a> <br/>";

					description = description + link + "<br/>";
				}

				if (sites.get(i).getResource_group_description() != null
						&& !sites.get(i).getResource_group_description().trim()
								.equals("")) {
					String resourceDesc = "";
					if (sites.get(i).getBusiness_type() != null
							&& !sites.get(i).getBusiness_type().trim()
									.equals("")) {
						resourceDesc = "License Type: "
								+ sites.get(i).getBusiness_type() + " <br/>";
					}

					if (sites.get(i).getCategory() != null
							&& !sites.get(i).getCategory().trim().equals("")) {
						resourceDesc = resourceDesc + "Category: "
								+ sites.get(i).getCategory() + " <br/>";
					}

					description = description + resourceDesc + "<br/>"
							+ sites.get(i).getResource_group_description();
				}

				countyChildrenItem[i] = description;
			}
			return countyChildrenItem;
		}

		private String[] groups = { "State Requirements",
				"Business Type Requirements", "Business Category Requirements",
				"County Requirements", "City Requirements" };
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
					ViewGroup.LayoutParams.MATCH_PARENT, 40);

			TextView textView = new TextView(BizLicenseDetailsActivity.this);
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
					ViewGroup.LayoutParams.MATCH_PARENT, 250);

			TextView textView = new TextView(BizLicenseDetailsActivity.this);
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