package at.fhj.itm.util;

public class Config {
	
	// Debugging
	public static boolean DEBUG = false;
	
	// Charset
	public static String CHARSET_UTF8 = "UTF-8";
	public static int FLIGHT_REQUEST_MAX = 20;
	
	// JPA
	public static String PERSISTENCE_UNIT = "flightgraph-persistence-unit";

	// ReST service
	public static String REST_RESULT_STATUS = "status";
	public static String REST_RESULT_DATA = "data";
	public static String REST_RESULT_CODE = "code";
	public static String REST_RESULT_DESCRIPTION = "description";
	
	public static String REST_RESULT_OK = "ok";
	public static String REST_RESULT_ERROR = "error";
	
	public static String REST_RESULT_CREATED = "CREATED";
	public static String REST_RESULT_ERROR_NOT_FOUND = "NOT_FOUND";
	public static String REST_RESULT_ERROR_ILLEGAL_REQUEST = "ILLEGAL_REQUEST";
	
	// Flight stats API
	public static String FLIGHT_STATS_ROOT = "https://api.flightstats.com/flex/flightstatus/rest/v2/json";
	
	public static String FLIGHT_STATS_APP_ID = "appId";
	public static String FLIGHT_STATS_APP_KEY = "appKey";
	public static String FLIGHT_STATS_FLIGHT_ID = "flightId";
	public static String FLIGHT_STATS_FLIGHT_STATUS = "flightStatus";
	public static String FLIGHT_STATS_MAX_FLIGHTS = "maxFlights";
	public static String FLIGHT_STATS_FLIGHT_POSITIONS = "flightPositions";
	public static String FLIGHT_STATS_CALLSIGN = "callsign";
	public static String FLIGHT_STATS_POSITIONS = "positions";
	public static String FLIGHT_STATS_DATE = "date";
	public static String FLIGHT_STATS_LAT = "lat";
	public static String FLIGHT_STATS_LON = "lon";
	public static String FLIGHT_STATS_SPEED = "speedMph";
	public static String FLIGHT_STATS_ALTITUDE = "altitudeFt";
	public static String FLIGHT_STATS_TAIL_NUMBER = "tailNumber";
	public static String FLIGHT_STATS_DELAYS = "delays";
	public static String FLIGHT_STATS_DELAY_ARRIVAL = "arrivalGateDelayMinutes";
	public static String FLIGHT_STATS_DEPARTURE_AIRPORT = "departureAirportFsCode";
	public static String FLIGHT_STATS_ARRIVAL_AIRPORT = "arrivalAirportFsCode";
	public static String FLIGHT_STATS_ARRIVAL_DATE = "arrivalDate";
	public static String FLIGHT_STATS_DEPARTURE_DATE = "departureDate";
	public static String FLIGHT_STATS_DATE_LOCAL = "dateLocal";
	public static String FLIGHT_STATS_DATE_UTC = "dateUtc";
	
	public static String FLIGHT_STATS_ERROR = "error";
	public static String FLIGHT_STATS_ERROR_CODE = "errorCode";
	public static String FLIGHT_STATS_ERROR_MESSAGE = "errorMessage";
	
	public static String FLIGHT_STATS_SUB_FLIGHT_ID = "{flightId}";
	public static String FLIGHT_STATS_SUB_TOP_LAT = "{topLat}";
	public static String FLIGHT_STATS_SUB_LEFT_LON = "{leftLon}";
	public static String FLIGHT_STATS_SUB_BOTTOM_LAT = "{bottomLat}";
	public static String FLIGHT_STATS_SUB_RIGHT_LON = "{rightLon}";
	public static String FLIGHT_STATS_SUB_MAX_FLIGHTS = "{maxFlights}";
	
	public static String FLIGHT_STATS_APPLICATION_ID = "815510a8";
	public static String FLIGHT_STATS_APPLICATION_KEY = "ebf1f664f49605b141fd8166c4b04007";
	
	public static String FLIGHT_STATS_AUTHENTICATION = 	FLIGHT_STATS_APP_ID + "=" + FLIGHT_STATS_APPLICATION_ID + "&" +
														FLIGHT_STATS_APP_KEY + "=" + FLIGHT_STATS_APPLICATION_KEY;
	
	// get flights within bounding box request
	public static String FLIGHT_STATS_REQUEST_FLIGHTS_NEAR = FLIGHT_STATS_ROOT + "/flightsNear/" + 
															FLIGHT_STATS_SUB_TOP_LAT + "/" + 
															FLIGHT_STATS_SUB_LEFT_LON + "/" + 
															FLIGHT_STATS_SUB_BOTTOM_LAT + "/" +
															FLIGHT_STATS_SUB_RIGHT_LON + "?" +
															FLIGHT_STATS_AUTHENTICATION + "&" +
															FLIGHT_STATS_MAX_FLIGHTS + "=" + FLIGHT_STATS_SUB_MAX_FLIGHTS;
	
	// get flight status (via FlightStats flightId)
	public static String FLIGHT_STATS_REQUEST_FLIGHT_STATUS = FLIGHT_STATS_ROOT + "/flight/status/" +
															FLIGHT_STATS_SUB_FLIGHT_ID + "?" + 
															FLIGHT_STATS_AUTHENTICATION;
	
}
