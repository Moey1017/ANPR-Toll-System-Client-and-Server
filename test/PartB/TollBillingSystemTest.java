/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PartB;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

/**
 *
 * @author HP
 */
public class TollBillingSystemTest
{

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    public TollBillingSystemTest()
    {
    }
    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown()
    {
    }
    /**
     * Test of displayAllFees method, of class TollBillingSystem.
     */
    @Test
    public void testDisplayAllFees()
    {
        System.out.println("displayAllFees");
        TollBillingSystem instance = new TollBillingSystem();
        instance.displayAllFees();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCustomerDetails method, of class TollBillingSystem.
     */
    @Test
    public void testGetCustomerDetails()
    {
        System.out.println("getCustomerDetails");
        int id = 0;
        TollBillingSystem instance = new TollBillingSystem();
        instance.getCustomerDetails(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of run method, of class TollBillingSystem.
     */
    @Test
    public void testRun()
    {
        System.out.println("run");
        TollBillingSystem instance = new TollBillingSystem();
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loginMenu method, of class TollBillingSystem.
     */
    @Test
    public void testLoginMenu()
    {
        System.out.println("loginMenu");
        TollBillingSystem instance = new TollBillingSystem();
        instance.loginMenu();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayLoginMenu method, of class TollBillingSystem.
     */
    @Test
    public void testDisplayLoginMenu()
    {
        System.out.println("displayLoginMenu");
        TollBillingSystem instance = new TollBillingSystem();
        instance.displayLoginMenu();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of runMainMenu method, of class TollBillingSystem.
     */
    @Test
    public void testRunMainMenu()
    {
        System.out.println("runMainMenu");
        TollBillingSystem instance = new TollBillingSystem();
        instance.runMainMenu();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayMainMenu method, of class TollBillingSystem.
     */
    @Test
    public void testDisplayMainMenu()
    {
        System.out.println("displayMainMenu");
        TollBillingSystem instance = new TollBillingSystem();
        instance.displayMainMenu();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllTollEventsAndSumByCustomerIdFromDatabase method, of class
     * TollBillingSystem.
     */
    @Test
    public void testGetAllTollEventsAndSumByCustomerIdFromDatabase()
    {
        System.out.println("getAllTollEventsAndSumByCustomerIdFromDatabase");
        TollBillingSystem instance = new TollBillingSystem();
        instance.getAllTollEventsAndSumByCustomerIdFromDatabase();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkBillStatus method, of class TollBillingSystem.
     */
    @Test
    public void testCheckBillStatus()
    {
        System.out.println("checkBillStatus");
        TollBillingSystem instance = new TollBillingSystem();
        instance.checkBillStatus();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayAllBills method, of class TollBillingSystem.
     */
    @Test
    public void testDisplayAllBills()
    {
        System.out.println("displayAllBills");
        TollBillingSystem instance = new TollBillingSystem();
        instance.displayAllBills();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of payBill method, of class TollBillingSystem.
     */
    @Test
    public void testPayBill()
    {
        System.out.println("payBill");
        TollBillingSystem instance = new TollBillingSystem();
        instance.payBill();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
