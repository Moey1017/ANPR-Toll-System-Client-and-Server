/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CA6;

import Exceptions.DaoException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * D00217017 Jing Sheng Moey 
 * SD2A
 */
public interface TollEventDAOInterface
{

    /**
     * Get all toll events in database
     * @return a hashMap of String, arrayList of TollEvents from Database 
     * @throws DaoException
     */
    public Map<String, ArrayList<TollEvent>> getAllTollEvents() throws DaoException;

    /**
     * insert one Toll Event into database
     * @param regNum Registration Number of the toll Event  
     * @param image_Id Image Id of the toll Event  
     * @param timeStamp timestamp of the toll Event  s
     * @return true if insert successfully, or false
     * @throws DaoException
     */
    public boolean insertTollEvent(String regNum, long image_Id, Instant timeStamp) throws DaoException;

    /**
     * Insert All toll events into Database
     * @param tollEvents a map of string, arrayList of toll Events 
     * @return true if insert successfully, or false
     * @throws DaoException
     */
    public boolean insertAllTollEvents(Map<String, ArrayList<TollEvent>> tollEvents) throws DaoException;

    /**
     * Truncate the toll event table in database
     * @throws DaoException
     */
    public void cleanUpTollEvents()throws DaoException;

    /**
     * Get toll event with Registration number
     * @param inputRegNum registration number to search for toll event
     * @return a Set of toll events with Registration number
     * @throws DaoException
     */
    public Set<TollEvent> getTollEventWithReg(String inputRegNum) throws DaoException;

    /**
     * Get toll event since time of 
     * @param timeSince toll event since time of 
     * @return a Set of toll events since time of 
     * @throws DaoException
     */
    public Set<TollEvent> getTollEventSinceTimeOf(java.sql.Timestamp timeSince)throws DaoException;

    /**
     * Get toll event between time of
     * @param timeAfter Start Date
     * @param timeBefore Finish Date
     * @return a Set of toll Events between time of
     * @throws DaoException
     */
    public Set<TollEvent> getTollEventBetweenTimeOf(java.sql.Timestamp timeAfter, java.sql.Timestamp timeBefore)throws DaoException;

    /**
     * Get registration number that has passed toll 
     * @return a String Set of registration that has pass toll 
     * @throws DaoException
     */
    public Set<String> getRegPassToll()throws DaoException;
    
    /**
     * Get all toll events into a list from database
     * @return a List of all toll events in database
     * @throws DaoException
     */
    public List<TollEvent> getAllTollEventsIntoList()throws DaoException;
    
    public boolean deleteATollEvent(TollEvent t)throws DaoException;
}
