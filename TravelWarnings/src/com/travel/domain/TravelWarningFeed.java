/**
 * 
 */
package com.travel.domain;

import java.util.ArrayList;
import java.util.List;


/**
 * @author raghurambongula 
 * @since Sep 10, 2011
 */
public class TravelWarningFeed {
	private String title;
	private String link;
	private String description;
	private String language;
	private String copyright;
	private String pubDate;
	private List<TravelWarning> travelWarnings = new ArrayList<TravelWarning>();

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link
	 *            the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language
	 *            the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the copyright
	 */
	public String getCopyright() {
		return copyright;
	}

	/**
	 * @param copyright
	 *            the copyright to set
	 */
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	/**
	 * @return the pubDate
	 */
	public String getPubDate() {
		return pubDate;
	}

	/**
	 * @param pubDate
	 *            the pubDate to set
	 */
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public void addItem(TravelWarning item) {
		travelWarnings.add(item);
	}

	/**
	 * @return the travelWarnings
	 */
	public List<TravelWarning> getTravelWarnings() {
		return travelWarnings;
	}

	/**
	 * @param travelWarnings the travelWarnings to set
	 */
	public void setTravelWarnings(List<TravelWarning> travelWarnings) {
		this.travelWarnings = travelWarnings;
	}
}
