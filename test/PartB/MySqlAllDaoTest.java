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
        int customerId = 0;
        MySqlAllDao instance = new MySqlAllDao();
        ArrayList<TollEvent> expResult = null;
        ArrayList<TollEvent> result = instance.getAllTollEventsByCustomerId(customerId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalBill method, of class MySqlAllDao.
     */
    @Test
    public void testGetTotalBill() throws Exception
    {
        System.out.println("getTotalBill");
        MySqlAllDao instance = new MySqlAllDao();
        double expResult = 0.0;
        double result = instance.getTotalBill();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCustomerDetailLogIn method, of class MySqlAllDao.
     */
    @Test
    public void testGetCustomerDetailLogIn() throws Exception
    {
        System.out.println("getCustomerDetailLogIn");
        int customerId = 0;
        MySqlAllDao instance = new MySqlAllDao();
        Customer expResult = null;
        Customer result = instance.getCustomerDetailLogIn(customerId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
