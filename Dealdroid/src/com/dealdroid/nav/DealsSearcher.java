/*
 * @author Raghu Ram Bongula
 */
package com.nav;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

import com.browse.R;
import com.dto.DealDTO;
import com.rss.RssParser;
import com.rss.RssParser.Item;
import com.rss.RssParser.RssFeed;

// TODO: Auto-generated Javadoc
/**
 * The Class DealsSearcher.
 */
public class DealsSearcher {

	/** The map activity. */
	private Activity mapActivity;
	
	/** The deals. */
	private List<DealDTO> deals = new ArrayList<DealDTO>();

	/**
	 * Instantiates a new deals searcher.
	 * 
	 * @param mapActivity
	 *            the map activity
	 */
	public DealsSearcher(Activity mapActivity) {
		this.mapActivity = mapActivity;
	}

	/**
	 * Gets the deals.
	 * 
	 * @return the deals
	 */
	public List<DealDTO> getDeals() {
		String[] dealSources = mapActivity.getResources()
				.getStringArray(R.array.dealSources);
		if (dealSources != null && dealSources.length > 0) {
			for( int i=0; i < dealSources.length ; i ++){
				List<DealDTO> dealDTOs = getDealContent(dealSources[i]);
				if(dealDTOs != null && dealDTOs.size() > 0){
					deals.addAll(dealDTOs);
				}
			}
		}
		return deals;
	}

	/**
	 * Gets the deal content.
	 * 
	 * @param dealSource
	 *            the deal source
	 * 
	 * @return the deal content
	 */
	public List<DealDTO> getDealContent(String dealSource) {
		List<DealDTO> deals = new ArrayList<DealDTO>();
		try {
//			HttpClient client = new HttpClient();
//			client.getHostConfiguration().setHost(dealSource);
//			GetMethod getMethod = new GetMethod(dealSource);
//			String response = "";
//			client.executeMethod(getMethod);
//			response = getMethod.getResponseBodyAsString();
			
//			RSSParser rssParser = new RSSParser();
//			deals = rssParser.parseXml(response);
//			String cool = response;
			RssParser parser = new RssParser(dealSource);
			parser.parse();
			RssFeed rssFeed = parser.getFeed();
			List<Item> items = rssFeed.getItems();
			deals = mapDealItems(items);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deals;
	}
	
	
	/**
	 * Map deal items.
	 * 
	 * @param items
	 *            the items
	 * 
	 * @return the list< deal dt o>
	 */
	public List<DealDTO> mapDealItems(List<Item> items){
	
		List<DealDTO> dealItems = new ArrayList<DealDTO>();
		if(items != null && items.size() > 0 ){
			for(Item it: items){
				DealDTO deal = new DealDTO();
				deal.setDesc(it.description);
				deal.setTitle(it.title);
				deal.setLink(it.link);
				dealItems.add(deal);
			}
		}
		return dealItems;
	}

}
