/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PartB;

import Exceptions.DaoException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public interface AllDAOInterface
{
    public ArrayList<TollEvent> getAllTollEventsByCustomerId(int customerId) throws DaoException;
    public Customer getCustomerDetailLogIn(int customerId) throws DaoException;
    public double getTotalBill() throws DaoException;
}
