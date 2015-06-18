package at.fhj.itm.util;

public class Config {
	
	// Charset
	public static String CHARSET_UTF8 = "UTF-8";

	// ReST service
	public static String REST_RESULT_STATUS = "status";
	public static String REST_RESULT_DATA = "data";
	public static String REST_RESULT_DESCRIPTION = "description";
	
	public static String REST_RESULT_OK = "ok";
	public static String REST_RESULT_ERROR = "error";
	
	public static String REST_RESULT_CREATED = "CREATED";
	public static String REST_RESULT_ERROR_NOT_FOUND = "NOT_FOUND";
	
	// Flight stats API
	public static String FLIGHT_STATS_ROOT = "https://api.flightstats.com/flex/flightstatus/rest/v2/json";
	
	public static String FLIGHT_STATS_APP_ID = "appId";
	public static String FLIGHT_STATS_APP_KEY = "appKey";
	public static String FLIGHT_STATS_MAX_FLIGHTS = "maxFlights";
	
	public static String FLIGHT_STATS_SUB_TOP_LAT = "{topLat}";
	public static String FLIGHT_STATS_SUB_LEFT_LON = "{leftLon}";
	public static String FLIGHT_STATS_SUB_BOTTOM_LAT = "{bottomLat}";
	public static String FLIGHT_STATS_SUB_RIGHT_LON = "{rightLon}";
	public static String FLIGHT_STATS_SUB_MAX_FLIGHTS = "{maxFlights}";
	
	public static String FLIGHT_STATS_APPLICATION_ID = "815510a8";
	public static String FLIGHT_STATS_APPLICATION_KEY = "ebf1f664f49605b141fd8166c4b04007";
	
	// get flights within bounding box request
	public static String FLIGHT_STATS_REQUEST_FLIGHTS_NEAR = FLIGHT_STATS_ROOT + "/flightsNear/" + 
															FLIGHT_STATS_SUB_TOP_LAT + "/" + 
															FLIGHT_STATS_SUB_LEFT_LON + "/" + 
															FLIGHT_STATS_SUB_BOTTOM_LAT + "/" +
															FLIGHT_STATS_SUB_RIGHT_LON + "?" +
															FLIGHT_STATS_APP_ID + "=" + FLIGHT_STATS_APPLICATION_ID + "&" +
															FLIGHT_STATS_APP_KEY + "=" + FLIGHT_STATS_APPLICATION_KEY + "&" +
															FLIGHT_STATS_MAX_FLIGHTS + "=" + FLIGHT_STATS_SUB_MAX_FLIGHTS;
	
}
