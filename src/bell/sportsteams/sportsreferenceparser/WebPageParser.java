package bell.sportsteams.sportsreferenceparser;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;


/**
 * @author Michael Bell
 * class designed to parse a web page to find information on basketball statistics
 */
public class WebPageParser {
	
	private URL url;
	
	//Store the webpage in the object
	private String dataDump;
	
	/**
	 * constructor will setup connection and add the page to dataDump for later parsing
	 * @throws MalformedURLException
	 */
	public WebPageParser () throws MalformedURLException{
	
		// load in URL to parse feed from, could later add external read in
		url = new URL("http://widgets.sports-reference.com/wg.fcgi?css=1&site=cbb&url=%2Fcbb%2Fseasons%2F2017-school-stats.html&div=div_basic_school_stats");
		try {
			URLConnection conn = url.openConnection();
			
			InputStream in = conn.getInputStream();
			String encoding = conn.getContentEncoding();
			
			//write the web page to our string dataDump
			dataDump = IOUtils.toString(in, encoding);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * this will return the starting index of the search term for a given
	 * school.
	 * @param schoolName the school who's statistics we are searching for
	 * @param searchTerm the specific term to search for, ex: "fg_pct"
	 * @return the found search result
	 */
	public float getSearchStart(String schoolName, String searchTerm){
		
		//find index to start search at
		int startIndex = this.getSchoolIndex(schoolName);
		
		//need to determine length of search term for parsing
		int searchTermSize = (searchTerm.length() + 2);
		
		//return value following term
		int startLocation = (dataDump.indexOf(searchTerm, startIndex)+ searchTermSize);
		int endLocation = dataDump.indexOf("<", startLocation);
		
		float searchValue = Float.parseFloat(dataDump.substring(startLocation, endLocation));
		
		return searchValue;
		
	}
	
	/**
	 * this will parse the webpage and return the number of wins
	 * for a given school
	 * @param schoolName the school to search for
	 * @return the number of wins
	 */
	public int getSchoolWins(String schoolName) {
		
		//find index to start search at
		int startIndex = this.getSchoolIndex(schoolName);
		
		//return value following wins
		int startLocation = (dataDump.indexOf("wins", startIndex) + 6);
		int endLocation = dataDump.indexOf("<", startLocation);
		
		//find the substring integer value
		int wins = Integer.parseInt(dataDump.substring(startLocation, endLocation));
		
		//System.out.println("result of wins search is: " + wins);
		return wins;
	}
	
	/**
	 * this will parse the webpage and return the number of losses
	 * for a given school
	 * @param schoolName the school to search for
	 * @return the number of losses
	 */
	public int getSchoolLosses(String schoolName) {
		
		//find index to start search at
		int startIndex = this.getSchoolIndex(schoolName);
		
		//return value following losses
		int startLocation = (dataDump.indexOf("losses", startIndex) + 8);
		int endLocation = dataDump.indexOf("<", startLocation);
		
		//find the substring integer value
		int losses = Integer.parseInt(dataDump.substring(startLocation, endLocation));
		
		return losses;
	}
	
	/**
	 * Method to find the index position of the school
	 * @param schoolName the school to search for
	 * @return int the starting index of the school
	 */
	private int getSchoolIndex(String schoolName){
		return dataDump.indexOf(">" + schoolName + "<");
	}
	
	
	
}
