/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Billing_System;

import Exceptions.DaoException;
import java.util.ArrayList;

/**
 *
 * D00217017 Jing Sheng Moey SD2A
 */
public interface AllDAOInterface
{

    /**
     *
     * @param customerId
     * @return a list a toll event by customer id 
     * @throws DaoException
     */
    public ArrayList<TollEvent> getAllTollEventsByCustomerId(int customerId) throws DaoException;

    /**
     *
     * @param customerId
     * @return get customer details by customer id 
     * @throws DaoException
     */
    public Customer getCustomerDetailLogIn(int customerId) throws DaoException;

    /**
     *
     * @param customerId 
     * @return get total bill by customer id 
     * @throws DaoException
     */
    public double getTotalBill(int customerId) throws DaoException;
}
