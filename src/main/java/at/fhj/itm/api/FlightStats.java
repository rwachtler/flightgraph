package at.fhj.itm.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import at.fhj.itm.util.Config;

public class FlightStats {
	
	
	
	public FlightStats() {
		
	}
	
	public void getNearest(float topLat, float leftLon, float bottomLat, float rightLon, int maxFlights) {
		String url;
		
		url = Config.FLIGHT_STATS_REQUEST_FLIGHTS_NEAR;
		
		// replace placeholders with actual parameters
		url = url.replace(Config.FLIGHT_STATS_SUB_TOP_LAT, String.valueOf(topLat))
				 .replace(Config.FLIGHT_STATS_SUB_LEFT_LON, String.valueOf(leftLon))
				 .replace(Config.FLIGHT_STATS_SUB_BOTTOM_LAT, String.valueOf(bottomLat))
				 .replace(Config.FLIGHT_STATS_SUB_RIGHT_LON, String.valueOf(rightLon))
				 .replace(Config.FLIGHT_STATS_SUB_MAX_FLIGHTS, String.valueOf(maxFlights));
		
		try {
			URLConnection connection = new URL(url).openConnection();
			connection.setRequestProperty("Accept-Charset", Config.CHARSET_UTF8);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			String inputLine;
			
			while((inputLine = in.readLine()) != null)
				System.out.println(inputLine);
			
			in.close();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
