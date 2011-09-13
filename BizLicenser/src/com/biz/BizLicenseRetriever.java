/**
 * 
 */
package com.biz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.biz.domain.Result;

/**
 * @author raghurambongula Sep 10, 2011
 */
public class BizLicenseRetriever {

	public static Result result = null;

	public static Result retrieveLicense(String zip, String licenseName)
			throws IOException {
		licenseName = licenseName.replace(" ", "_");
		
		URL url = new URL(
				"http://api.sba.gov/license_permit/by_zip/"+licenseName+"/"+zip+".xml");
		URLConnection conn = url.openConnection();
		// Get the response
		BufferedReader rd = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		StringBuffer sb = new StringBuffer();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		String result = sb.toString();

		Result resultObj = Result.fromXml(result);
		BizLicenseRetriever.result = resultObj;
		return resultObj;

	}

}
