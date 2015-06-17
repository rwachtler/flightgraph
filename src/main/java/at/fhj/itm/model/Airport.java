package at.fhj.itm.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Airport implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @Column
   private String airportName;

   @Column
   private String airportCity;

   @Column
   private String airportCountry;

   @Column
   private String airportCode;

   @Column
   private String airportICAO;

   @Column
   private float lat;

   @Column
   private float lon;

   public Long getId()
   {
      return this.id;
   }

   public void setId(final Long id)
   {
      this.id = id;
   }

   public int getVersion()
   {
      return this.version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (!(obj instanceof Airport))
      {
         return false;
      }
      Airport other = (Airport) obj;
      if (id != null)
      {
         if (!id.equals(other.id))
         {
            return false;
         }
      }
      return true;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
   }

   public String getAirportName()
   {
      return airportName;
   }

   public void setAirportName(String airportName)
   {
      this.airportName = airportName;
   }

   public String getAirportCity()
   {
      return airportCity;
   }

   public void setAirportCity(String airportCity)
   {
      this.airportCity = airportCity;
   }

   public String getAirportCountry()
   {
      return airportCountry;
   }

   public void setAirportCountry(String airportCountry)
   {
      this.airportCountry = airportCountry;
   }

   public String getAirportCode()
   {
      return airportCode;
   }

   public void setAirportCode(String airportCode)
   {
      this.airportCode = airportCode;
   }

   public String getAirportICAO()
   {
      return airportICAO;
   }

   public void setAirportICAO(String airportICAO)
   {
      this.airportICAO = airportICAO;
   }

   public float getLat()
   {
      return lat;
   }

   public void setLat(float lat)
   {
      this.lat = lat;
   }

   public float getLon()
   {
      return lon;
   }

   public void setLon(float lon)
   {
      this.lon = lon;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (airportName != null && !airportName.trim().isEmpty())
         result += "airportName: " + airportName;
      if (airportCity != null && !airportCity.trim().isEmpty())
         result += ", airportCity: " + airportCity;
      if (airportCountry != null && !airportCountry.trim().isEmpty())
         result += ", airportCountry: " + airportCountry;
      if (airportCode != null && !airportCode.trim().isEmpty())
         result += ", airportCode: " + airportCode;
      if (airportICAO != null && !airportICAO.trim().isEmpty())
         result += ", airportICAO: " + airportICAO;
      result += ", lat: " + lat;
      result += ", lon: " + lon;
      return result;
   }
}