/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PartB;

import Exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;

/**
 *
 * D00217017 Jing Sheng Moey SD2A
 */
public class MySqlAllDao extends MySqlDao implements AllDAOInterface
{

    /**
     *
     * @param customerId
     * @return a list a toll event by customer id 
     * @throws DaoException
     */
    @Override
    public ArrayList<TollEvent> getAllTollEventsByCustomerId(int customerId) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<TollEvent> eventList = new ArrayList<>();
        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT event_id, vehicle_id, vehicle_type, vehicle_reg,  image_id, timestamp, cost from toll_event natural join customer_vehicle natural join customer natural join vehicle natural join vehicle_type_cost where customer_id = ? AND timestamp BETWEEN SUBDATE(CURDATE(), INTERVAL 1 MONTH) AND NOW();";
            ps = con.prepareStatement(query);
            ps.setInt(1, customerId);

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next())
            {
                int eventId = rs.getInt("event_id");
                int vehicleId = rs.getInt("vehicle_id");
                long imageId = rs.getInt("image_id");
                String vehicleType = rs.getString("vehicle_type");
                String vehicleReg = rs.getString("vehicle_reg");
                java.sql.Timestamp ts = rs.getTimestamp("timestamp");
                Instant instant = ts.toInstant();
                double cost = rs.getDouble("cost");
                
                TollEvent t = new TollEvent(eventId, vehicleId, imageId, instant, vehicleType, cost, vehicleReg);
                eventList.add(t);
            }
        } catch (SQLException e)
        {
            throw new DaoException("getAllTollEventsWithCustomerId() " + e.getMessage());
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
                throw new DaoException("getAllTollEventsWithCustomerId() " + e.getMessage());
            }
        }
        return eventList;
    }
    
    /**
     *
     * @param customerId
     * @return get customer details by customer id 
     * @throws DaoException
     */
    @Override
    public double getTotalBill(int customerId) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        double total = 0;
        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT SUM(cost) from toll_event natural join customer_vehicle natural join customer natural join vehicle natural join vehicle_type_cost where customer_id = ? AND timestamp BETWEEN SUBDATE(CURDATE(), INTERVAL 1 MONTH) AND NOW();";
            ps = con.prepareStatement(query);
            ps.setInt(1, customerId);

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next())
            {
                total = rs.getDouble("SUM(cost)");
            }
        } catch (SQLException e)
        {
            throw new DaoException("getTotalBill() " + e.getMessage());
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
                throw new DaoException("getTotalBill() " + e.getMessage());
            }
        }
        return total;
    }

    /**
     *
     * @param customerId
     * @return
     * @throws DaoException
     */
    @Override
    public Customer getCustomerDetailLogIn(int customerId) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Customer c = new Customer();
        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT customer_id, customer_name, customer_address FROM customer WHERE customer_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, customerId);

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next())
            {
                //read all attributes
                int CID = rs.getInt("customer_id");
                String customer_name = rs.getString("customer_name");
                String address = rs.getString("customer_address");
                c.setCustomer_id(CID);
                c.setCustomer_name(customer_name);
                c.setCustomer_address(address);
            }
        } catch (SQLException e)
        {
            throw new DaoException("getCustomerDetailLogIn(int customerId) " + e.getMessage());
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
                throw new DaoException("getCustomerDetailLogIn(int customerId) " + e.getMessage());
            }
        }
        return c;
    }
}
