package at.fhj.itm.rest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Response.Status;

import at.fhj.itm.api.FlightStats;
import at.fhj.itm.model.Airport;
import at.fhj.itm.model.Flight;
import at.fhj.itm.util.Config;

/**
 * 
 */
@Stateless
@Path("/setup")
public class MySQLSetup
{
   @PersistenceContext(unitName = "flightgraph-persistence-unit")
   private EntityManager em;
   
   public static String airportFile = "../setup/airports.dat";

   @GET
   @Produces("application/json")
   public Response initDatabase()
   {
	   int airportsCreated = 0;
	   
	   try {
			airportsCreated = readAirportFile();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
      Map<String, Object> restResponse = new HashMap<String, Object>();
      
      restResponse.put(Config.REST_RESULT_STATUS, Config.REST_RESULT_OK);
      restResponse.put(Config.REST_RESULT_DESCRIPTION, "created " + airportsCreated + " airports"); 
     
      return Response.ok(restResponse).build();
      
   }
   
   private int readAirportFile() throws FileNotFoundException, IOException {
	   int count = 0;
	   
		InputStream in = MySQLSetup.class.getResourceAsStream(airportFile);
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String line;
			
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(",");
				
				Airport airport = new Airport();
				
				airport.setAirportName(getTextBetweenQuotes(parts[1]));
				airport.setAirportCity(getTextBetweenQuotes(parts[2]));
				airport.setAirportCountry(getTextBetweenQuotes(parts[3]));
				airport.setAirportCode(getTextBetweenQuotes(parts[4]));
				airport.setAirportICAO(getTextBetweenQuotes(parts[5]));
				airport.setLat(Float.parseFloat(parts[6]));
				airport.setLon(Float.parseFloat(parts[7]));
				
				em.persist(airport);
				
				count++;
			}
			
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("read " + count + " lines");
		
		return count;
	}
   
   private String getTextBetweenQuotes(String text) {
	   if (text.length() > 2) {
		   return text.substring(1, text.length() - 1);
	   }
	   
	   return "";
   }
}
