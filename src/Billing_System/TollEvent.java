/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Billing_System;

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

    /**
     *
     * @param eventId
     * @param vehicleId
     * @param ImageID
     * @param timestamp
     * @param vehicleType
     * @param cost
     * @param registrationNumber
     */
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

    /**
     * setRegistrationNumbers
     * @param registrationNumber
     */
    public void setRegistrationNumber(String registrationNumber)
    {
        this.registrationNumber = registrationNumber;
    }

    /**
     * setEventId
     * @param eventId
     */
    public void setEventId(int eventId)
    {
        this.eventId = eventId;
    }

    /**
     * setVehicleId
     * @param vehicleId
     */
    public void setVehicleId(int vehicleId)
    {
        this.vehicleId = vehicleId;
    }

    /**
     * setVehicleType
     * @param vehicleType
     */
    public void setVehicleType(String vehicleType)
    {
        this.vehicleType = vehicleType;
    }

    /**
     * setCost
     * @param cost
     */
    public void setCost(double cost)
    {
        this.cost = cost;
    }

    /**
     * seteventId
     * @param eventId
     */
    public void seteventId(int eventId)
    {
        this.eventId = eventId;
    }

    /**
     * setVehicle_id
     * @param vehicle_id
     */
    public void setVehicle_id(String vehicle_id)
    {
        this.vehicleId = vehicleId;
    }

    /**
     * setImageID
     * @param ImageID
     */
    public void setImageID(long ImageID)
    {
        this.ImageID = ImageID;
    }

    /**
     * setTimestamp
     * @param timestamp
     */
    public void setTimestamp(Instant timestamp)
    {
        this.timestamp = timestamp;
    }

    /**
     * getVehicleType
     * @return
     */
    public String getVehicleType()
    {
        return vehicleType;
    }

    /**
     * getCost
     * @return cost
     */
    public double getCost()
    {
        return cost;
    }

    /**
     * getEventId
     * @return eventId
     */
    public int getEventId()
    {
        return eventId;
    }

    /**
     * getVehicleId
     * @return vehicleId
     */
    public int getVehicleId()
    {
        return vehicleId;
    }

    /**
     * getImageID
     * @return vehicleId
     */
    public long getImageID()
    {
        return ImageID;
    }

    /**
     * getTimestamp
     * @return timestamp
     */
    public Instant getTimestamp()
    {
        return timestamp;
    }
    
    /**
     * getRegistrationNumber
     * @return registrationNumber
     */
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
