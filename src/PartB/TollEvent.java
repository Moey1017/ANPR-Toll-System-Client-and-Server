/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PartB;

import CA6.*;
import java.util.Objects;
import java.time.Instant;

/**
 *
 * D00217017 Jing Sheng Moey 
 * SD2A
 */
public class TollEvent
{
    private int eventId;
    private int vehicleId;
    private long    ImageID;
    private Instant timestamp;
    private String vehicleType; 
    private double cost;
    private String registrationNumber;

    public TollEvent(int eventId, int vehicleId, long ImageID, Instant timestamp, String vehicleType, double cost, String registrationNumber)
    {
        this.eventId = eventId;
        this.vehicleId = vehicleId;
        this.ImageID = ImageID;
        this.timestamp = timestamp;
        this.vehicleType = vehicleType;
        this.cost = cost;
        this.registrationNumber = registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber)
    {
        this.registrationNumber = registrationNumber;
    }

    public void setEventId(int eventId)
    {
        this.eventId = eventId;
    }

    public void setVehicleId(int vehicleId)
    {
        this.vehicleId = vehicleId;
    }

    public void setVehicleType(String vehicleType)
    {
        this.vehicleType = vehicleType;
    }

    public void setCost(double cost)
    {
        this.cost = cost;
    }

    public void seteventId(int eventId)
    {
        this.eventId = eventId;
    }

    public void setVehicle_id(String vehicle_id)
    {
        this.vehicleId = vehicleId;
    }

    /**
     *
     * @param ImageID
     */
    public void setImageID(long ImageID)
    {
        this.ImageID = ImageID;
    }

    /**
     *
     * @param timestamp
     */
    public void setTimestamp(Instant timestamp)
    {
        this.timestamp = timestamp;
    }

    public String getVehicleType()
    {
        return vehicleType;
    }

    public double getCost()
    {
        return cost;
    }

    public int getEventId()
    {
        return eventId;
    }

    public int getVehicleId()
    {
        return vehicleId;
    }

    /**
     *
     * @return
     */
    public long getImageID()
    {
        return ImageID;
    }

    /**
     *
     * @return
     */
    public Instant getTimestamp()
    {
        return timestamp;
    }
    
    public String getRegistrationNumber()
    {
        return registrationNumber;
    }

    @Override
    public String toString()
    {
        return "TollEvent{" + "eventId=" + eventId + ", vehicleId=" + vehicleId + ", ImageID=" + ImageID + ", timestamp=" + timestamp + ", vehicleType=" + vehicleType + ", cost=" + cost + ", registrationNumber=" + registrationNumber + '}';
    }

 
        

}
