package at.fhj.itm.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;
import at.fhj.itm.model.Airport;
import javax.persistence.ManyToOne;

@Entity
public class Flight implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @Column
   private String callsign;

   @Column
   private long departureTime;

   @Column
   private long arrivalTime;

   @ManyToOne
   private Airport departureAirport;

   @ManyToOne
   private Airport arrivalAirport;

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
      if (!(obj instanceof Flight))
      {
         return false;
      }
      Flight other = (Flight) obj;
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

   public String getCallsign()
   {
      return callsign;
   }

   public void setCallsign(String callsign)
   {
      this.callsign = callsign;
   }

   public long getDepartureTime()
   {
      return departureTime;
   }

   public void setDepartureTime(long departureTime)
   {
      this.departureTime = departureTime;
   }

   public long getArrivalTime()
   {
      return arrivalTime;
   }

   public void setArrivalTime(long arrivalTime)
   {
      this.arrivalTime = arrivalTime;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (callsign != null && !callsign.trim().isEmpty())
         result += "callsign: " + callsign;
      result += ", departureTime: " + departureTime;
      result += ", arrivalTime: " + arrivalTime;
      return result;
   }

   public Airport getDepartureAirport()
   {
      return this.departureAirport;
   }

   public void setDepartureAirport(final Airport departureAirport)
   {
      this.departureAirport = departureAirport;
   }

   public Airport getArrivalAirport()
   {
      return this.arrivalAirport;
   }

   public void setArrivalAirport(final Airport arrivalAirport)
   {
      this.arrivalAirport = arrivalAirport;
   }
}