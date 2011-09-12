/**
 * 
 */
package com.travel.reader;

import java.util.List;

import com.travel.domain.TravelWarning;

/**
 * @author raghurambongula
 * Sep 10, 2011
 */

public interface FeedParser {
    List<TravelWarning> parse();
}