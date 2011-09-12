/**
 * 
 */
package com.travel.reader;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.travel.domain.TravelWarning;

/**
 * @author raghurambongula Sep 10, 2011
 */
public class TravelWarningReader extends BaseFeedParser {

	public TravelWarningReader(String url) {
//		super("http://173.8.6.218/_res/rss/TAs.xml");
		super(url);
	}

	public List<TravelWarning> parse() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			RssHandler handler = new RssHandler();
			parser.parse(this.getInputStream(), handler);
			return handler.getFeed().getTravelWarnings(); 
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
