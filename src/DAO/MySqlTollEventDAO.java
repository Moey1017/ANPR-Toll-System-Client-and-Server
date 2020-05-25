/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Server_Client.TollEvent;
import Exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * D00217017 Jing Sheng Moey 
 * SD2A
 */
public class MySqlTollEventDAO extends MySqlDao implements TollEventDAOInterface
{

    /**
     * Get all toll events in database
     *
     * @return a hashMap of String, arrayList of TollEvents from Database
     * @throws DaoException
     */
    @Override
    public Map<String, ArrayList<TollEvent>> getAllTollEvents() throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Map<String, ArrayList<TollEvent>> tollEvents = new TreeMap<>();

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM toll_events";
            ps = con.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next())
            {
                //read all attributes
                String regNum = rs.getString("registration_number");
                long image_id = rs.getLong("image_id");
                java.sql.Timestamp ts = rs.getTimestamp("timestamp");
                Instant instant = ts.toInstant();
                TollEvent t = new TollEvent(regNum, image_id, instant);

                // add into tolleventList associates with reg number,recreate an object if null 
                ArrayList<TollEvent> tempTollEventsList = tollEvents.get(regNum);
                if (tempTollEventsList == null)
                {
                    tempTollEventsList = new ArrayList<>();
                }
                tempTollEventsList.add(t);
                tollEvents.put(regNum, tempTollEventsList);
            }
        } catch (SQLException e)
        {
            throw new DaoException("getAllTollEvents() " + e.getMessage());
        } finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    freeConnection(con);
                }
            } catch (SQLException e)
            {
                throw new DaoException("getAllTollEvents() " + e.getMessage());
            }
        }
        return tollEvents;
    }

    /**
     * insert one Toll Event into database
     *
     * @param regNum Registration Number of the toll Event
     * @param image_Id Image Id of the toll Event
     * @param timeStamp timestamp of the toll Event s
     * @return true if insert successfully, or false
     * @throws DaoException
     */
    @Override
    public boolean insertTollEvent(String regNum, long image_Id, Instant timeStamp) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean insertSuccessfully = true;
        try
        {
            con = this.getConnection();
            java.sql.Timestamp ts_now = java.sql.Timestamp.from(timeStamp);
            String query = "INSERT INTO toll_events VALUES(?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setString(1, regNum);
            ps.setLong(2, image_Id);
            ps.setTimestamp(3, ts_now);

            if (ps.executeUpdate() == 0)
            {
                return false;
            }
        } catch (SQLException e)
        {
            throw new DaoException("insertTollEvent() " + e.getMessage());
        } finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    freeConnection(con);
                }
            } catch (SQLException e)
            {
                throw new DaoException("insertTollEvent() " + e.getMessage());
            }
        }
        return insertSuccessfully;
    }

    /**
     * Insert All toll events into Database
     *
     * @param tollEvents a map of string, arrayList of toll Events
     * @return true if insert successfully, or false
     * @throws DaoException
     */
    @Override
    public boolean insertAllTollEvents(Map<String, ArrayList<TollEvent>> tollEvents) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean insertSuccessfully = true;
        try
        {
            con = this.getConnection();
            Set<String> keySet = tollEvents.keySet(); // get all the keys in tollEvents
            for (String key : keySet)
            {
                String regNum = key;
                ArrayList<TollEvent> t = tollEvents.get(key); // get the value associates with key
                for (int i = 0; i < t.size(); i++)
                {
                    long image_Id = t.get(i).getImageID();
                    Instant timeStamp = t.get(i).getTimestamp();
                    java.sql.Timestamp ts_now = java.sql.Timestamp.from(timeStamp);
                    String query = "INSERT INTO toll_events VALUES(?, ?, ?)";
                    ps = con.prepareStatement(query);
                    ps.setString(1, regNum);
                    ps.setLong(2, image_Id);
                    ps.setTimestamp(3, ts_now);
                    if (ps.executeUpdate() == 0)
                    {
                        return false;
                    }
                }
            }
        } catch (SQLException e)
        {
            throw new DaoException("insertAllTollEvents()" + e.getMessage());
        } finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    freeConnection(con);
                }
            } catch (SQLException e)
            {
                throw new DaoException("insertAllTollEvents()" + e.getMessage());
            }
        }
        return insertSuccessfully;
    }

    /**
     * Truncate the toll event table in database
     *
     * @throws DaoException
     */
    @Override
    public void cleanUpTollEvents() throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            con = this.getConnection();
            String query = "truncate table toll_events";
            ps = con.prepareStatement(query);
            ps.executeUpdate();

        } catch (SQLException e)
        {
            throw new DaoException("cleanUpTollEvents() " + e.getMessage());
        } finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    freeConnection(con);
                }
            } catch (SQLException e)
            {
                throw new DaoException("cleanUpTollEvents() " + e.getMessage());
            }
        }
    }

    /**
     * Get toll event with Registration number
     *
     * @param inputRegNum registration number to search for toll event
     * @return a Set of toll events with Registration number
     * @throws DaoException
     */
    @Override
    public Set<TollEvent> getTollEventWithReg(String inputRegNum) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Set<TollEvent> tollSet = new HashSet<>();
        try
        {
            con = this.getConnection();
            String query = "SELECT * FROM toll_events WHERE registration_number = ?;";
            ps = con.prepareStatement(query);
            ps.setString(1, inputRegNum);
            rs = ps.executeQuery();
            while (rs.next())
            {
                String registrationNum = rs.getString("registration_number");
                long imageID = rs.getLong("image_id");
                java.sql.Timestamp ts = rs.getTimestamp("timestamp");
                Instant timeStamp = ts.toInstant();
                TollEvent t = new TollEvent(registrationNum, imageID, timeStamp);
                tollSet.add(t);
            }

        } catch (SQLException e)
        {
            throw new DaoException("getTollEventWithReg() " + e.getMessage());
        } finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    freeConnection(con);
                }
            } catch (SQLException e)
            {
                throw new DaoException("getTollEventWithReg() " + e.getMessage());
            }
        }
        return tollSet;
    }

    /**
     * Get toll event since time of
     *
     * @param timeSince toll event since time of
     * @return a Set of toll events since time of
     * @throws DaoException
     */
    @Override
    public Set<TollEvent> getTollEventSinceTimeOf(java.sql.Timestamp timeSince) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Set<TollEvent> tollSet = new HashSet<>();
        try
        {
            con = this.getConnection();
            String query = "SELECT * FROM toll_events WHERE timestamp >= (?);";
            ps = con.prepareStatement(query);
            ps.setTimestamp(1, timeSince);
            rs = ps.executeQuery();
            while (rs.next())
            {
                String registrationNum = rs.getString("registration_number");
                long imageID = rs.getLong("image_id");
                java.sql.Timestamp ts = rs.getTimestamp("timestamp");
                Instant timeStamp = ts.toInstant();
                TollEvent t = new TollEvent(registrationNum, imageID, timeStamp);
                tollSet.add(t);
            }

        } catch (SQLException e)
        {
            throw new DaoException("getTollEventSinceTimeOf() " + e.getMessage());
        } finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    freeConnection(con);
                }
            } catch (SQLException e)
            {
                throw new DaoException("getTollEventSinceTimeOf() " + e.getMessage());
            }
        }
        return tollSet;
    }

    /**
     * Get toll event between time of
     *
     * @param timeAfter Start Date
     * @param timeBefore Finish Date
     * @return a Set of toll Events between time of
     * @throws DaoException
     */
    @Override
    public Set<TollEvent> getTollEventBetweenTimeOf(java.sql.Timestamp timeAfter, java.sql.Timestamp timeBefore) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Set<TollEvent> tollSet = new HashSet<>();
        try
        {
            con = this.getConnection();
            String query = "SELECT * FROM toll_events WHERE timestamp BETWEEN ? AND ?;";
            ps = con.prepareStatement(query);
            ps.setTimestamp(1, timeAfter);
            ps.setTimestamp(2, timeBefore);
            rs = ps.executeQuery();
            while (rs.next())
            {
                String registrationNum = rs.getString("registration_number");
                long imageID = rs.getLong("image_id");
                java.sql.Timestamp ts = rs.getTimestamp("timestamp");
                Instant timeStamp = ts.toInstant();
                TollEvent t = new TollEvent(registrationNum, imageID, timeStamp);
                tollSet.add(t);
            }

        } catch (SQLException e)
        {
            throw new DaoException("getTollEventBetweenTimeOf() " + e.getMessage());
        } finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    freeConnection(con);
                }
            } catch (SQLException e)
            {
                throw new DaoException("getTollEventBetweenTimeOf() " + e.getMessage());
            }
        }
        return tollSet;
    }

    /**
     * Get registration number that has passed toll
     *
     * @return a String Set of registration that has pass toll
     * @throws DaoException
     */
    @Override
    public Set<String> getRegPassToll() throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Set<String> regSet = new TreeSet<>();
        try
        {
            con = this.getConnection();
            String query = "SELECT DISTINCT registration_number FROM toll_events;";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next())
            {
                String registrationNum = rs.getString("registration_number");
                regSet.add(registrationNum);
            }

        } catch (SQLException e)
        {
            throw new DaoException("getRegPassToll() " + e.getMessage());
        } finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    freeConnection(con);
                }
            } catch (SQLException e)
            {
                throw new DaoException("getRegPassToll() " + e.getMessage());
            }
        }
        return regSet;
    }

    /**
     * Get all toll events into a list from database
     *
     * @return a List of all toll events in database
     * @throws DaoException
     */
    @Override
    public List<TollEvent> getAllTollEventsIntoList() throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TollEvent> tollEvents = new ArrayList<>();

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM toll_events";
            ps = con.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next())
            {
                //read all attributes
                String regNum = rs.getString("registration_number");
                long image_id = rs.getLong("image_id");
                java.sql.Timestamp ts = rs.getTimestamp("timestamp");
                Instant instant = ts.toInstant();
                TollEvent t = new TollEvent(regNum, image_id, instant);

                tollEvents.add(t);
            }
        } catch (SQLException e)
        {
            throw new DaoException("getAllTollEvents() " + e.getMessage());
        } finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    freeConnection(con);
                }
            } catch (SQLException e)
            {
                throw new DaoException("getAllTollEvents() " + e.getMessage());
            }
        }
        return tollEvents;
    }

    @Override
    public boolean deleteATollEvent(TollEvent t) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean deleteSuccessfully = true;
        try
        {
            con = this.getConnection();
            String regNum = t.getRegistration();
            long image_Id = t.getImageID();
            java.sql.Timestamp ts = java.sql.Timestamp.from(t.getTimestamp());
            String query = "DELETE FROM toll_events WHERE registration_number = ? AND image_id = ? AND timestamp = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, regNum);
            ps.setLong(2, image_Id);
            ps.setTimestamp(3, ts);
            if (ps.executeUpdate() == 0)
            {
                return false;
            }

        } catch (SQLException e)
        {
            throw new DaoException("insertAllTollEvents()" + e.getMessage());
        } finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    freeConnection(con);
                }
            } catch (SQLException e)
            {
                throw new DaoException("insertAllTollEvents()" + e.getMessage());
            }
        }
        return deleteSuccessfully;
    }
}
