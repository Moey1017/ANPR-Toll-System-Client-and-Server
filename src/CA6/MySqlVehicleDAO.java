/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CA6;

import Exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * D00217017 Jing Sheng Moey 
 * SD2A
 */
public class MySqlVehicleDAO extends MySqlDao implements VehicleDAOInterface
{

    /**
     *
     * @return
     * @throws DaoException
     */
    @Override
    public Set<Vehicle> getAllVehicle() throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Set<Vehicle> vehicles = new HashSet<>();

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM Vehicles";
            ps = con.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next())
            {
                String regNum = rs.getString("registration_number");
                Vehicle v = new Vehicle(regNum);
                vehicles.add(v);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findAllUsers() " + e.getMessage());
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
                throw new DaoException("getAllVehicle() " + e.getMessage());
            }
        }
        return vehicles;
    }

    /**
     *
     * @param regNum
     * @return
     * @throws DaoException
     */
    @Override
    public boolean insertVehicle(String regNum) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean insertSuccessfully = false;
        try
        {
            con = this.getConnection();

            String query = "INSERT INTO Vehicles VALUES(?)";
            ps = con.prepareStatement(query);
            ps.setString(1, regNum);

            if (ps.executeUpdate() > 0)
            {
                insertSuccessfully = true;
            }
        } catch (SQLException e)
        {
            throw new DaoException("insertVehicle() " + e.getMessage());
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
                throw new DaoException("insertVehicle() " + e.getMessage());
            }
        }
        return insertSuccessfully;
    }

    /**
     *
     * @param vehicles
     * @return
     * @throws DaoException
     */
    @Override
    public boolean insertAllVehicles(HashSet<Vehicle> vehicles) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean insertSuccessfully = true;
        try
        {
            con = this.getConnection();

            for (Vehicle v : vehicles)
            {
                String regNum = v.getRegistrationNumber();
                String query = "INSERT INTO Vehicles VALUES(?)";
                ps = con.prepareStatement(query);
                ps.setString(1, regNum);
                if (ps.executeUpdate() == 0)
                {
                    return false;
                }
            }
        } catch (SQLException e)
        {
            throw new DaoException("insertVehicle() " + e.getMessage());
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
                throw new DaoException("insertVehicle() " + e.getMessage());
            }
        }
        return insertSuccessfully;
    }
}
