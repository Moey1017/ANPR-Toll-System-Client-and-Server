/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Billing_System;

import java.util.ArrayList;

/**
 *
 * D00217017 Jing Sheng Moey SD2A
 */
public class Customer
{
    private int customer_id;
    private String customer_name;
    private String customer_address;
    private ArrayList<Integer> carId_List;

    /**
     *
     * @param customer_id
     * @param customer_name
     * @param customer_address
     */
    public Customer(int customer_id, String customer_name, String customer_address)
    {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.customer_address = customer_address;
        this.carId_List = new ArrayList<>();
    }

    /**
     *
     */
    public Customer()
    {
        this.customer_id = -1;
        this.customer_name = "";
        this.customer_address = "";
        this.carId_List = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public ArrayList<Integer> getCarId_List()
    {
        return carId_List;
    }

    /**
     *
     * @return
     */
    public int getCustomer_id()
    {
        return customer_id;
    }

    /**
     *
     * @return
     */
    public String getCustomer_name()
    {
        return customer_name;
    }

    /**
     *
     * @return
     */
    public String getCustomer_address()
    {
        return customer_address;
    }

    /**
     *
     * @param customer_id
     */
    public void setCustomer_id(int customer_id)
    {
        this.customer_id = customer_id;
    }

    /**
     *
     * @param customer_name
     */
    public void setCustomer_name(String customer_name)
    {
        this.customer_name = customer_name;
    }

    /**
     *
     * @param customer_address
     */
    public void setCustomer_address(String customer_address)
    {
        this.customer_address = customer_address;
    }

    /**
     *
     * @param carId_List
     */
    public void setCarId_List(ArrayList<Integer> carId_List)
    {
        this.carId_List = carId_List;
    }

    @Override
    public String toString()
    {
        return "Customer{" + "customer_id=" + customer_id + ", customer_name=" + customer_name + ", customer_address=" + customer_address + '}';
    }
}
