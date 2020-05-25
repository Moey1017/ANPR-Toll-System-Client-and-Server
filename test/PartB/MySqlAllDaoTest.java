/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PartB;

import Billing_System.TollEvent;
import Billing_System.Customer;
import Billing_System.MySqlAllDao;
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
 * D00217017 Jing Sheng Moey SD2A
 */
public class MySqlAllDaoTest
{
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    public MySqlAllDaoTest()
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
     * Test of getAllTollEventsByCustomerId method, of class MySqlAllDao.
     */
    @Test
    public void testGetAllTollEventsByCustomerId() throws Exception
    {
        System.out.println("getAllTollEventsByCustomerId");
        int customerId = 1;
        MySqlAllDao instance = new MySqlAllDao();
        ArrayList<TollEvent> expResult = new ArrayList<>();
        TollEvent t1 = new TollEvent(1,1,123424, Instant.parse("2020-02-16T11:16:50Z"), "Car", 3.0, "152DL345");
        TollEvent t2 = new TollEvent(2,1,32939, Instant.parse("2020-02-14T22:15:38Z"), "Car", 3.0, "152DL345");
        TollEvent t3 = new TollEvent(3,2,655218, Instant.parse("2020-02-17T18:20:06Z"), "Van", 5.2, "161C3457");
        expResult.add(t1);
        expResult.add(t2);
        expResult.add(t3);
        ArrayList<TollEvent> result = instance.getAllTollEventsByCustomerId(customerId);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCustomerDetailLogIn method, of class MySqlAllDao.
     */
    @Test
    public void testGetCustomerDetailLogIn() throws Exception
    {
        System.out.println("getCustomerDetailLogIn");
        int customerId = 1;
        MySqlAllDao instance = new MySqlAllDao();
        Customer expResult = new Customer(1, "Moey", "15b setanta ");
        Customer result = instance.getCustomerDetailLogIn(customerId);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getCustomerDetailLogIn method, of class MySqlAllDao.
     */
    @Test
    public void testGetCustomerDetailLogIn1() throws Exception
    {
        System.out.println("getCustomerDetailLogIn");
        int customerId = 2;
        MySqlAllDao instance = new MySqlAllDao();
        Customer expResult = new Customer(2, "Alicia", "12 orchar road, dublin");
        Customer result = instance.getCustomerDetailLogIn(customerId);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTotalBill method, of class MySqlAllDao.
     */
    @Test
    public void testGetTotalBill() throws Exception
    {
        System.out.println("getTotalBill");
        int customerId = 1;
        MySqlAllDao instance = new MySqlAllDao();
        double expResult = 11.2;
        double result = instance.getTotalBill(customerId);
        assertEquals(expResult, result, 0.02);
    }
    
    @Test
    public void testGetTotalBill1() throws Exception
    {
        System.out.println("getTotalBill");
        int customerId = 5;
        MySqlAllDao instance = new MySqlAllDao();
        double expResult = 0;
        double result = instance.getTotalBill(customerId);
        assertEquals(expResult, result, 0.02);
    }
    
}
