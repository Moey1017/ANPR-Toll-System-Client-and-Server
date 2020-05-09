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
 * D00217017 Jing Sheng Moey SD2A
 */
public class TollBillingSystem
{

    //variable of the class
    private Customer customer;
    private Customer temp;
    private double totalFee;
    private ArrayList<TollEvent> tollEvents;
    private boolean PayStatus = false;

    //DAO 
    private final AllDAOInterface IAllDAO = new MySqlAllDao();

    //initialize customer 

    /**
     *
     * @param c
     */
    public TollBillingSystem(Customer c)
    {
        this.customer = c;
        this.totalFee = 0;
        this.tollEvents = new ArrayList<>();
        this.PayStatus = false;
    }

    /**
     *
     */
    public TollBillingSystem()
    {
        this.customer = new Customer();
        this.totalFee = 0;
        this.tollEvents = new ArrayList<>();
        this.PayStatus = false;
    }

    /**
     *setCustomer
     * @param customer
     */
    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    /**
     *setPayStatus
     * @param PayStatus
     */
    public void setPayStatus(boolean PayStatus)
    {
        this.PayStatus = PayStatus;
    }

    /**
     * setTotalFee
     * @param totalFee
     */
    public void setTotalFee(double totalFee)
    {
        this.totalFee = totalFee;
    }

    /**
     * setTollEvents
     * @param tollEvents
     */
    public void setTollEvents(ArrayList<TollEvent> tollEvents)
    {
        this.tollEvents = tollEvents;
    }

    /**
     * check pay status
     * @return
     */
    public boolean isPayStatus()
    {
        return PayStatus;
    }

    /**
     * getCustomer
     * @return
     */
    public Customer getCustomer()
    {
        return customer;
    }

    /**
     * getTotalFee
     * @return
     */
    public double getTotalFee()
    {
        return totalFee;
    }

    /** 
     * getTollEvents
     * @return
     */
    public ArrayList<TollEvent> getTollEvents()
    {
        return tollEvents;
    }

    /**
     * displayAllFees
     */
    public void displayAllFees()
    {
        int totalFee = 0;
        for (TollEvent te : this.tollEvents)
        {
            System.out.println(te);
            totalFee += te.getCost();
        }
        System.out.println("Total Fee :" + totalFee);
    }

    /**
     * getCustomerDetails by id
     * @param id
     */
    public void getCustomerDetails(int id)
    {
        ArrayList<TollEvent> tollEventLists = new ArrayList<>();
        try
        {
            this.temp = IAllDAO.getCustomerDetailLogIn(id);
        } catch (DaoException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * run system
     */
    public void run()
    {
        loginMenu();
    }

    /**
     * loginMenu
     */
    public void loginMenu()
    {
        boolean running = true;
        System.out.println("Hello, welcome to toll billig system.");
        displayLoginMenu();
        int option = Utilities.getValidInt(1, 2, "Please select an option ");
        while (running)
        {
            if (option == 1)
            {
                int CID = Utilities.getValidInt(0, "Please enter your ID");
                getCustomerDetails(CID);
                if (this.temp.getCustomer_id() != -1)
                {
                    System.out.println("ID:" + this.temp.getCustomer_id());
                    System.out.println("Name:" + this.temp.getCustomer_name());
                    System.out.println("Address:" + this.temp.getCustomer_address());
                    boolean isCustomer = Utilities.getValidBoolean("Is this you? Enter Y to Login.(Y/N)");
                    if (isCustomer)
                    {
                        this.customer = this.temp;
                        getAllTollEventsAndSumByCustomerIdFromDatabase();
                        runMainMenu();
                    }
                }
                else
                {
                    System.out.println("ID not found.");
                    Utilities.awaitForEnter();
                }
            }
            else if (option == 2)
            {
                running = false;
                System.out.println("GoodBye.");
            }
            if (running)
            {
                displayLoginMenu();
                option = Utilities.getValidInt(1, 2, "Please select an option ");
            }
        }
    }

    /**
     * login menu 
     */
    public void displayLoginMenu()
    {
        ArrayList<String> menu = new ArrayList<>();
        menu.add("\n******************************************************************");
        menu.add("Log in Menu");
        menu.add("1. Log in with customer ID");
        menu.add("2. Quit");
        menu.add("******************************************************************");
        for (String m : menu)
        {
            System.out.println(m);
        }
    }

    /**
     * main menu for paying bills
     */
    public void runMainMenu()
    {
        System.out.println("\nWelcome " + this.customer.getCustomer_name() + ", You have been logged in successfully.");
        boolean running = true;
        displayMainMenu();
        int option = Utilities.getValidInt(1, 4, "Please enter an option(1~3)");
        while (running)
        {
            switch (option)
            {
                case 1:
                    checkBillStatus();
                    break;
                case 2:
                    displayAllTollEvents();
                    break;
                case 3:
                    payBill();
                    break;
                default:
                    running = false;
                    System.out.println("Quit Main Menu");
                    System.out.println("You have been logged out\n");
                    break;
            }
            if (running)
            {
                displayMainMenu();
                option = Utilities.getValidInt(1, 4, "Please enter an option(1~3)");
            }
        }

    }

    /**
     * displaying main menu 
     */
    public void displayMainMenu()
    {
        ArrayList<String> menu = new ArrayList<>();
        menu.add("******************************************************************");
        menu.add("Main Menu");
        menu.add("1. Check Bills Status");
        menu.add("2. Display All Toll Event");
        menu.add("3. Pay Bills");
        menu.add("4. Logout");
        menu.add("******************************************************************");
        for (String m : menu)
        {
            System.out.println(m);
        }
    }

    /** 
     * getAllTollEventsAndSumByCustomerIdFromDatabase
     */
    public void getAllTollEventsAndSumByCustomerIdFromDatabase()
    {
        try
        {
            this.tollEvents = IAllDAO.getAllTollEventsByCustomerId(this.customer.getCustomer_id());
            this.totalFee = IAllDAO.getTotalBill(this.customer.getCustomer_id());
        } catch (DaoException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * checkBillStatus
     */
    public void checkBillStatus()
    {
        if (this.PayStatus)
        {
            System.out.println("You have paid all bills");
        }
        else
        {
            if(this.totalFee != 0)
            {
                System.out.println("You have owned " + this.totalFee + " Fees.");
            }
            else
            {
                System.out.println("There is no owned amount.");
            }
        }
        Utilities.awaitForEnter();
    }

    /**
     * displayAllTollEvents
     */
    public void displayAllTollEvents()
    {
        if(this.tollEvents.size() != 0)
        {
            for (TollEvent t : this.tollEvents)
            {
                System.out.printf("%-10s%-15s%-15s%-18s%-10s%-12s%-10s\n", "EventID", "VehicleID", "VehicleReg", "VehicleType", "Cost", " ImageID", "Time");
                System.out.printf("%-10s%-15s%-15s%-18s%-11.2f%-12d%-10s\n\n", t.getEventId(), t.getVehicleId(), t.getRegistrationNumber(), t.getVehicleType(), t.getCost(), t.getImageID(), t.getTimestamp().toString());
            }
            System.out.println("Total Fees owned : " + this.totalFee);
        }
        else
        {
            System.out.println("No Toll Event has been found recently.");
        }
        Utilities.awaitForEnter();
    }

    /**
     * payBill
     */
    public void payBill()
    {
        if(this.totalFee != 0)
        {
            System.out.println("Total Fees : " + this.totalFee);
            boolean pay = Utilities.getValidBoolean("Do you want to pay the fee now? Enter Y to pay.(Y/N)");
            if (pay)
            {
                this.PayStatus = true;
                this.totalFee = 0;
                System.out.println("You have paid the bill.");
            }
            else
            {
                System.out.println("The bill is not paid yet.");
            }
        }
        else
            System.out.println("There is no owned amount.");
        Utilities.awaitForEnter();
    }

}
