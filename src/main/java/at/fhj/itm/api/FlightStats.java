package at.fhj.itm.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import at.fhj.itm.model.Airport;
import at.fhj.itm.model.Flight;
import at.fhj.itm.util.Config;

public class FlightStats {
	
	public FlightStats() {
		
	}
	
	public String getFlightsInAreaFromApi(float topLat, float leftLon, float bottomLat, float rightLon, int maxFlights) {
		// url for FlightStats api request
		String url;
		
		url = Config.FLIGHT_STATS_REQUEST_FLIGHTS_NEAR;
		
		// replace placeholders with actual parameters
		url = url.replace(Config.FLIGHT_STATS_SUB_TOP_LAT, String.valueOf(topLat))
				 .replace(Config.FLIGHT_STATS_SUB_LEFT_LON, String.valueOf(leftLon))
				 .replace(Config.FLIGHT_STATS_SUB_BOTTOM_LAT, String.valueOf(bottomLat))
				 .replace(Config.FLIGHT_STATS_SUB_RIGHT_LON, String.valueOf(rightLon))
				 .replace(Config.FLIGHT_STATS_SUB_MAX_FLIGHTS, String.valueOf(maxFlights));
		
		// for api response
		StringBuilder sb = new StringBuilder();
		
		try {
			if (Config.DEBUG)
				System.out.println("requesting " + url);
			
			URLConnection connection = new URL(url).openConnection();
			connection.setRequestProperty("Accept-Charset", Config.CHARSET_UTF8);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			String inputLine;
			
			while((inputLine = in.readLine()) != null)
				sb.append(inputLine);
			
			in.close();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	public List<Flight> parseFlightsInAreaResponse(String response) {
		List<Flight> flightList = new ArrayList<Flight>();
		
		try {
			if (Config.DEBUG)
				System.out.println("parsing response " + response);
			
			JSONObject json = new JSONObject(response);
			
			JSONArray flightsInArea = json.getJSONArray(Config.FLIGHT_STATS_FLIGHT_POSITIONS);
			
			for (int i = 0; i < flightsInArea.length(); i++) {
				Flight flight = new Flight();
				
				// single flight json object
				JSONObject flightObject = flightsInArea.getJSONObject(i);
				
				// all positions for this flight
				JSONArray flightPositionArray = flightObject.getJSONArray(Config.FLIGHT_STATS_POSITIONS);
				
				flight.setFlightStatsId(flightObject.getInt(Config.FLIGHT_STATS_FLIGHT_ID));
				flight.setCallsign(flightObject.getString(Config.FLIGHT_STATS_CALLSIGN));
				
				try {
					flight.setTailNumber(flightObject.getString(Config.FLIGHT_STATS_TAIL_NUMBER));
				} catch (Exception e) {
					flight.setTailNumber("");
				}
				
				if (flightPositionArray.length() > 0) {
					JSONObject flightPosition = flightPositionArray.getJSONObject(flightPositionArray.length() - 1);
					
					flight.setAltitude(flightPosition.getInt(Config.FLIGHT_STATS_ALTITUDE));
					flight.setLat(flightPosition.getDouble(Config.FLIGHT_STATS_LAT));
					flight.setLon(flightPosition.getDouble(Config.FLIGHT_STATS_LON));
					flight.setSpeed(flightPosition.getInt(Config.FLIGHT_STATS_SPEED));
				}
				
				flightList.add(flight);
			}
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return flightList;
	}
	
	public List<Flight> addFlightDetailInformation(List<Flight> flightList) {
		List<Flight> returnList = new ArrayList<Flight>();
		
		// get detailed information about this flight
		for (Flight flight : flightList) {
			returnList.add(getFlightDetail(flight));
		}
		
		return returnList;
	}
	
	public Flight getFlightDetail(Flight flight) {
		Flight flightDetail = flight;
		
		// url for FlightStats api request
		String url;
		
		url = Config.FLIGHT_STATS_REQUEST_FLIGHT_STATUS;
		
		// replace placeholders with actual parameters
		url = url.replace(Config.FLIGHT_STATS_SUB_FLIGHT_ID, String.valueOf(flight.getFlightStatsId()));
		
		// for api response
		StringBuilder sb = new StringBuilder();
		
		try {
			if (Config.DEBUG)
				System.out.println("requesting " + url);
			
			URLConnection connection = new URL(url).openConnection();
			connection.setRequestProperty("Accept-Charset", Config.CHARSET_UTF8);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			String inputLine;
			
			while((inputLine = in.readLine()) != null)
				sb.append(inputLine);
			
			in.close();
			
			// parse response
			String response = sb.toString();
			
			try {
				JSONObject json = new JSONObject(response);
				
				JSONObject flightStatus = json.getJSONObject(Config.FLIGHT_STATS_FLIGHT_STATUS);
				
				// get departure details
				String departureAirportCode = flightStatus.getString(Config.FLIGHT_STATS_DEPARTURE_AIRPORT); 
				
				flight.setDepartureAirportCode(departureAirportCode);
				flight.setDepartureLocal(flightStatus.getJSONObject(Config.FLIGHT_STATS_DEPARTURE_DATE).getString(Config.FLIGHT_STATS_DATE_LOCAL));
				flight.setDepartureUtc(flightStatus.getJSONObject(Config.FLIGHT_STATS_DEPARTURE_DATE).getString(Config.FLIGHT_STATS_DATE_UTC));
				
				// get arrival details
				String arrivalAirportCode = flightStatus.getString(Config.FLIGHT_STATS_ARRIVAL_AIRPORT);
				
				flight.setArrivalAirportCode(arrivalAirportCode);
				flight.setArrivalLocal(flightStatus.getJSONObject(Config.FLIGHT_STATS_ARRIVAL_DATE).getString(Config.FLIGHT_STATS_DATE_LOCAL));
				flight.setArrivalUtc(flightStatus.getJSONObject(Config.FLIGHT_STATS_ARRIVAL_DATE).getString(Config.FLIGHT_STATS_DATE_UTC));
				
				// get delay
				try {
					flight.setDelay(flightStatus.getJSONObject(Config.FLIGHT_STATS_DELAYS).getInt(Config.FLIGHT_STATS_DELAY_ARRIVAL));
				} catch (Exception e) {
					flight.setDelay(0);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flightDetail;
	}
	
	public List<Flight> getFlightsInArea(float topLat, float leftLon, float bottomLat, float rightLon, int maxFlights) {
		List<Flight> flightList = new ArrayList<Flight>();
		
		// get flights in area from api (json response)
		String apiResponse = getFlightsInAreaFromApi(topLat, leftLon, bottomLat, rightLon, maxFlights);
		
		// check if request was successful
		if (checkApiResponseForSuccess(apiResponse)) {
			
		} else {
			// an error occurred
			
		}
		
		// parse response
		flightList = parseFlightsInAreaResponse(apiResponse);
		
		return flightList;
	}
	
	public boolean checkApiResponseForSuccess(String response) {
		boolean successfulRequest = true;
		
		try {
			JSONObject json = new JSONObject(response);
			
			// check if error object exists, otherwise the request was successful
			try {
				json.getJSONObject(Config.FLIGHT_STATS_ERROR);
				successfulRequest = false;
			} catch (Exception e) {
				successfulRequest = true;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return successfulRequest;
	}
	
	public String getErrorCodeFromResponse(String response) {
		try {
			JSONObject json = new JSONObject(response);
			
			return json.getJSONObject(Config.FLIGHT_STATS_ERROR).getString(Config.FLIGHT_STATS_ERROR_CODE);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
	
	public String getErrorMessageFromResponse(String response) {
		try {
			JSONObject json = new JSONObject(response);
			
			return json.getJSONObject(Config.FLIGHT_STATS_ERROR).getString(Config.FLIGHT_STATS_ERROR_MESSAGE);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
	
}
