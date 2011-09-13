/**
 * 
 */
package com.biz.domain;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

/**
 * @author raghurambongula Sep 10, 2011
 */
public class Result {

	private List<Site> county_sites = new ArrayList<Site>();
	private List<Site> local_sites= new ArrayList<Site>();
	private List<Site> state_sites= new ArrayList<Site>();
	private List<Site> sites_for_business_type= new ArrayList<Site>();
	private List<Site> sites_for_category= new ArrayList<Site>();

	/**
	 * @return the county_sites
	 */
	public List<Site> getCounty_sites() {
		return county_sites;
	}

	/**
	 * @param county_sites
	 *            the county_sites to set
	 */
	public void setCounty_sites(List<Site> county_sites) {
		this.county_sites = county_sites;
	}

	/**
	 * @return the local_sites
	 */
	public List<Site> getLocal_sites() {
		return local_sites;
	}

	/**
	 * @param local_sites
	 *            the local_sites to set
	 */
	public void setLocal_sites(List<Site> local_sites) {
		this.local_sites = local_sites;
	}

	/**
	 * @return the state_sites
	 */
	public List<Site> getState_sites() {
		return state_sites;
	}

	/**
	 * @param state_sites
	 *            the state_sites to set
	 */
	public void setState_sites(List<Site> state_sites) {
		this.state_sites = state_sites;
	}

	/**
	 * @return the sites_for_business_type
	 */
	public List<Site> getSites_for_business_type() {
		return sites_for_business_type;
	}

	/**
	 * @param sites_for_business_type
	 *            the sites_for_business_type to set
	 */
	public void setSites_for_business_type(List<Site> sites_for_business_type) {
		this.sites_for_business_type = sites_for_business_type;
	}

	/**
	 * @return the sites_for_category
	 */
	public List<Site> getSites_for_category() {
		return sites_for_category;
	}

	/**
	 * @param sites_for_category
	 *            the sites_for_category to set
	 */
	public void setSites_for_category(List<Site> sites_for_category) {
		this.sites_for_category = sites_for_category;
	}
	
	public static Result fromXml(String xml){
		XStream xstream = new XStream();
		xstream.alias("result", Result.class);
		xstream.alias("site", Site.class);
		return (Result) xstream.fromXML(xml);
	}

}
