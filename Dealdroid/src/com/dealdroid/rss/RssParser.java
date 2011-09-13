/*
 * @author Raghu Ram Bongula
 */
package com.rss;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class RssParser.
 */
public class RssParser extends DefaultHandler {
	
	/** The url string. */
	private String urlString;
	
	/** The rss feed. */
	private RssFeed rssFeed;
	
	/** The text. */
	private StringBuilder text;
	
	/** The item. */
	private Item item;
	
	/** The img status. */
	private boolean imgStatus;

	/**
	 * Instantiates a new rss parser.
	 * 
	 * @param url
	 *            the url
	 */
	public RssParser(String url) {
		this.urlString = url;
		this.text = new StringBuilder();
	}

	/**
	 * Parses the.
	 */
	public void parse() {
		InputStream urlInputStream = null;
		SAXParserFactory spf = null;
		SAXParser sp = null;

		try {
			URL url = new URL(this.urlString);
			_setProxy(); // Set the proxy if needed
			urlInputStream = url.openConnection().getInputStream();
			spf = SAXParserFactory.newInstance();
			if (spf != null) {
				sp = spf.newSAXParser();
				sp.parse(urlInputStream, this);
			}
		}

		/*
		 * Exceptions need to be handled MalformedURLException
		 * ParserConfigurationException IOException SAXException
		 */

		catch (Exception e) {
			System.out.println("Exception: " + e);
			e.printStackTrace();
		} finally {
			try {
				if (urlInputStream != null)
					urlInputStream.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Gets the feed.
	 * 
	 * @return the feed
	 */
	public RssFeed getFeed() {
		return (this.rssFeed);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) {
		if (qName.equalsIgnoreCase("channel"))
			this.rssFeed = new RssFeed();
		else if (qName.equalsIgnoreCase("item") && (this.rssFeed != null)) {
			this.item = new Item();
			this.rssFeed.addItem(this.item);
		} else if (qName.equalsIgnoreCase("image") && (this.rssFeed != null))
			this.imgStatus = true;
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void endElement(String uri, String localName, String qName) {
		if (this.rssFeed == null)
			return;

		if (qName.equalsIgnoreCase("item"))
			this.item = null;

		else if (qName.equalsIgnoreCase("image"))
			this.imgStatus = false;

		else if (qName.equalsIgnoreCase("title")) {
			if (this.item != null)
				this.item.title = this.text.toString().trim();
			else if (this.imgStatus)
				this.rssFeed.imageTitle = this.text.toString().trim();
			else
				this.rssFeed.title = this.text.toString().trim();
		}

		else if (qName.equalsIgnoreCase("link")) {
			if (this.item != null)
				this.item.link = this.text.toString().trim();
			else if (this.imgStatus)
				this.rssFeed.imageLink = this.text.toString().trim();
			else
				this.rssFeed.link = this.text.toString().trim();
		}

		else if (qName.equalsIgnoreCase("description")) {
			if (this.item != null)
				this.item.description = this.text.toString().trim();
			else
				this.rssFeed.description = this.text.toString().trim();
		}

		else if (qName.equalsIgnoreCase("url") && this.imgStatus)
			this.rssFeed.imageUrl = this.text.toString().trim();

		else if (qName.equalsIgnoreCase("language"))
			this.rssFeed.language = this.text.toString().trim();

		else if (qName.equalsIgnoreCase("generator"))
			this.rssFeed.generator = this.text.toString().trim();

		else if (qName.equalsIgnoreCase("copyright"))
			this.rssFeed.copyright = this.text.toString().trim();

		else if (qName.equalsIgnoreCase("pubDate") && (this.item != null))
			this.item.pubDate = this.text.toString().trim();

		else if (qName.equalsIgnoreCase("category") && (this.item != null))
			this.rssFeed.addItem(this.text.toString().trim(), this.item);

		this.text.setLength(0);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	public void characters(char[] ch, int start, int length) {
		this.text.append(ch, start, length);
	}

	/**
	 * _set proxy.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void _setProxy() throws IOException {
		Properties sysProperties = System.getProperties();
		sysProperties.put("proxyHost", "<Proxy IP Address>");
		sysProperties.put("proxyPort", "<Proxy Port Number>");
		System.setProperties(sysProperties);
	}

	/**
	 * The Class RssFeed.
	 */
	public static class RssFeed {
		
		/** The title. */
		public String title;
		
		/** The description. */
		public String description;
		
		/** The link. */
		public String link;
		
		/** The language. */
		public String language;
		
		/** The generator. */
		public String generator;
		
		/** The copyright. */
		public String copyright;
		
		/** The image url. */
		public String imageUrl;
		
		/** The image title. */
		public String imageTitle;
		
		/** The image link. */
		public String imageLink;

		/** The items. */
		private ArrayList<Item> items;
		
		/** The category. */
		private HashMap<String, ArrayList<Item>> category;

		/**
		 * Adds the item.
		 * 
		 * @param item
		 *            the item
		 */
		public void addItem(Item item) {
			if (this.items == null)
				this.items = new ArrayList<Item>();
			this.items.add(item);
		}
		
		/**
		 * Gets the items.
		 * 
		 * @return the items
		 */
		public ArrayList<Item> getItems(){
			return items;
		}

		/**
		 * Adds the item.
		 * 
		 * @param category
		 *            the category
		 * @param item
		 *            the item
		 */
		public void addItem(String category, Item item) {
			if (this.category == null)
				this.category = new HashMap<String, ArrayList<Item>>();
			if (!this.category.containsKey(category))
				this.category.put(category, new ArrayList<Item>());
			this.category.get(category).add(item);
		}
	}

	/**
	 * The Class Item.
	 */
	public static class Item {
		
		/** The title. */
		public String title;
		
		/** The description. */
		public String description;
		
		/** The link. */
		public String link;
		
		/** The pub date. */
		public String pubDate;

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			return (this.title + ":" + this.pubDate + "n " + this.description);
		}
	}

}