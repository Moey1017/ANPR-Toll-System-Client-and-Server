/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CA5;

import DAO.MySqlTollEventDAO;
import Server_Client.TollEvent;
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
public class MySqlTollEventDAOTest
{

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    public MySqlTollEventDAOTest()
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
     * Test of getAllTollEvents method, of class MySqlTollEventDAO.
     */
    @Test
    public void testGetAllTollEvents() throws Exception
    {
        System.out.println("getAllTollEvents");
        MySqlTollEventDAO instance = new MySqlTollEventDAO();
        Map<String, ArrayList<TollEvent>> expResult = new TreeMap<>();
        ArrayList<TollEvent> t1 = new ArrayList<>();
        t1.add(new TollEvent("152DL345", 30422, Instant.parse("2020-02-16T11:16:50Z")));
        expResult.put("152DL345", t1);
        ArrayList<TollEvent> t2 = new ArrayList<>();
        t2.add(new TollEvent("161C3457", 30410, Instant.parse("2020-02-14T22:15:38Z")));
        expResult.put("161C3457", t2);
        ArrayList<TollEvent> t3 = new ArrayList<>();
        t3.add(new TollEvent("181MH3456", 30436, Instant.parse("2020-02-17T17:33:05Z")));
        t3.add(new TollEvent("181MH3456", 30437, Instant.parse("2020-02-17T18:20:06Z")));
        expResult.put("181MH3456", t3);
        ArrayList<TollEvent> t4 = new ArrayList<>();
        t4.add(new TollEvent("181MH3458", 30438, Instant.parse("2020-02-17T18:20:07Z")));
        expResult.put("181MH3458", t4);
        ArrayList<TollEvent> t5 = new ArrayList<>();
        t5.add(new TollEvent("181MH3459", 30439, Instant.parse("2020-02-17T18:58:08Z")));
        expResult.put("181MH3459", t5);
        ArrayList<TollEvent> t6 = new ArrayList<>();
        t6.add(new TollEvent("181MH3461", 30441, Instant.parse("2020-02-17T23:25:10Z")));
        expResult.put("181MH3461", t6);
        ArrayList<TollEvent> t7 = new ArrayList<>();
        t7.add(new TollEvent("191LH1111", 30402, Instant.parse("2020-02-14T10:15:30Z")));
        t7.add(new TollEvent("191LH1111", 30411, Instant.parse("2020-02-14T23:15:39Z")));
        t7.add(new TollEvent("191LH1111", 30421, Instant.parse("2020-02-16T11:16:49Z")));
        t7.add(new TollEvent("191LH1111", 30432, Instant.parse("2020-02-17T13:20:01Z")));
        expResult.put("191LH1111", t7);
        ArrayList<TollEvent> t8 = new ArrayList<>();
        t8.add(new TollEvent("191LH1112", 30412, Instant.parse("2020-02-15T12:15:40Z")));
        expResult.put("191LH1112", t8);
        ArrayList<TollEvent> t9 = new ArrayList<>();
        t9.add(new TollEvent("191LH1113", 30413, Instant.parse("2020-02-15T12:15:41Z")));
        expResult.put("191LH1113", t9);
        ArrayList<TollEvent> t10 = new ArrayList<>();
        t10.add(new TollEvent("191LH1114", 30414, Instant.parse("2020-02-15T12:15:42Z")));
        expResult.put("191LH1114", t10);
        ArrayList<TollEvent> t11 = new ArrayList<>();
        t11.add(new TollEvent("192D33457", 30409, Instant.parse("2020-02-14T16:15:37Z")));
        expResult.put("192D33457", t11);
        ArrayList<TollEvent> t12 = new ArrayList<>();
        t12.add(new TollEvent("201CN3456", 30433, Instant.parse("2020-02-17T14:25:02Z")));
        expResult.put("201CN3456", t12);
        ArrayList<TollEvent> t13 = new ArrayList<>();
        t13.add(new TollEvent("201CN3457", 30434, Instant.parse("2020-02-17T16:20:03Z")));
        expResult.put("201CN3457", t13);
        ArrayList<TollEvent> t14 = new ArrayList<>();
        t14.add(new TollEvent("201LH3025", 30408, Instant.parse("2020-02-14T15:15:36Z")));
        expResult.put("201LH3025", t14);
        ArrayList<TollEvent> t15 = new ArrayList<>();
        t15.add(new TollEvent("201LH304", 30405, Instant.parse("2020-02-14T13:15:33Z")));
        t15.add(new TollEvent("201LH304", 30415, Instant.parse("2020-02-15T12:15:43Z")));
        t15.add(new TollEvent("201LH304", 30423, Instant.parse("2020-02-16T11:16:51Z")));
        t15.add(new TollEvent("201LH304", 30435, Instant.parse("2020-02-17T16:20:04Z")));
        expResult.put("201LH304", t15);
        ArrayList<TollEvent> t16 = new ArrayList<>();
        t16.add(new TollEvent("201LH305", 30416, Instant.parse("2020-02-15T12:15:44Z")));
        t16.add(new TollEvent("201LH305", 30424, Instant.parse("2020-02-16T11:16:52Z")));
        expResult.put("201LH305", t16);
        ArrayList<TollEvent> t17 = new ArrayList<>();
        t17.add(new TollEvent("201LH306", 30417, Instant.parse("2020-02-15T12:15:45Z")));
        expResult.put("201LH306", t17);
        ArrayList<TollEvent> t18 = new ArrayList<>();
        t18.add(new TollEvent("201LH3064", 30425, Instant.parse("2020-02-16T11:16:53Z")));
        expResult.put("201LH3064", t18);
        ArrayList<TollEvent> t19 = new ArrayList<>();
        t19.add(new TollEvent("201LH307", 30418, Instant.parse("2020-02-15T12:15:46Z")));
        expResult.put("201LH307", t19);
        ArrayList<TollEvent> t20 = new ArrayList<>();
        t20.add(new TollEvent("201LH3076", 30426, Instant.parse("2020-02-16T11:16:54Z")));
        expResult.put("201LH3076", t20);
        ArrayList<TollEvent> t21 = new ArrayList<>();
        t21.add(new TollEvent("201LH308", 30419, Instant.parse("2020-02-15T21:15:47Z")));
        expResult.put("201LH308", t21);
        ArrayList<TollEvent> t22 = new ArrayList<>();
        t22.add(new TollEvent("201LH3083", 30427, Instant.parse("2020-02-16T11:16:55Z")));
        expResult.put("201LH3083", t22);
        ArrayList<TollEvent> t23 = new ArrayList<>();
        t23.add(new TollEvent("201LH309", 30428, Instant.parse("2020-02-16T11:16:56Z")));
        expResult.put("201LH309", t23);
        ArrayList<TollEvent> t24 = new ArrayList<>();
        t24.add(new TollEvent("201LH310", 30429, Instant.parse("2020-02-16T11:16:57Z")));
        expResult.put("201LH310", t24);
        ArrayList<TollEvent> t25 = new ArrayList<>();
        t25.add(new TollEvent("201LH311", 30430, Instant.parse("2020-02-16T11:16:58Z")));
        expResult.put("201LH311", t25);
        ArrayList<TollEvent> t26 = new ArrayList<>();
        t26.add(new TollEvent("201LH312", 30431, Instant.parse("2020-02-16T11:16:59Z")));
        expResult.put("201LH312", t26);
        Map<String, ArrayList<TollEvent>> result = instance.getAllTollEvents();
        assertEquals(expResult, result);
    }

    /**
     * Test of insertTollEvent method, of class MySqlTollEventDAO.
     */
    @Test
    public void testInsertTollEvent() throws Exception
    {
        System.out.println("insertTollEvent");
        String regNum = "12345";
        long image_Id = 12345;
        Instant timeStamp = Instant.parse("2020-02-18T11:16:59Z");
        TollEvent t = new TollEvent(regNum, image_Id, timeStamp);
        MySqlTollEventDAO instance = new MySqlTollEventDAO();
        boolean expResult = true;
        instance.insertTollEvent(regNum, image_Id, timeStamp);
        Map<String, ArrayList<TollEvent>> tollEvents = instance.getAllTollEvents();
        ArrayList<TollEvent> tList = tollEvents.get(regNum);
        boolean result = tList.contains(t);
        assertEquals(expResult, result);
        
        //delete the record after test is finished
        instance.deleteATollEvent(t);
    }
    
//    /**
//     * Test of deleteATollEvent method, of class MySqlTollEventDAO.
//     */
//    @Test
//    public void testDeleteATollEvent() throws Exception
//    {
//        System.out.println("deleteATollEvent");
//        TollEvent t = null;
//        MySqlTollEventDAO instance = new MySqlTollEventDAO();
//        boolean expResult = false;
//        boolean result = instance.deleteATollEvent(t);
//        assertEquals(expResult, result);
//    }


//    /**
//     * Test of cleanUpTollEvents method, of class MySqlTollEventDAO.
//     */
//    @Test
//    public void testCleanUpTollEvents() throws Exception
//    {
//        System.out.println("cleanUpTollEvents");
//        MySqlTollEventDAO instance = new MySqlTollEventDAO();
//        instance.cleanUpTollEvents();
//    }
    /**
     * Test of getTollEventWithReg method, of class MySqlTollEventDAO.
     */
    @Test
    public void testGetTollEventWithReg() throws Exception
    {
        System.out.println("getTollEventWithReg");
        String inputRegNum = "";
        MySqlTollEventDAO instance = new MySqlTollEventDAO();
        Set<TollEvent> result = instance.getTollEventWithReg(inputRegNum);
        assertEquals(0, result.size());
    }

    @Test
    public void testGetTollEventWithReg1() throws Exception
    {
        System.out.println("getTollEventWithReg");
        String inputRegNum = "1412VJHHJFE";
        MySqlTollEventDAO instance = new MySqlTollEventDAO();
        Set<TollEvent> result = instance.getTollEventWithReg(inputRegNum);
        assertEquals(0, result.size());
    }

    // test single result 
    @Test
    public void testGetTollEventWithReg2() throws Exception
    {
        System.out.println("getTollEventWithReg");
        String inputRegNum = "152DL345";
        MySqlTollEventDAO instance = new MySqlTollEventDAO();
        Set<TollEvent> expResult = new HashSet<>();
        expResult.add(new TollEvent("152DL345", 30422, Instant.parse("2020-02-16T11:16:50Z")));
        Set<TollEvent> result = instance.getTollEventWithReg(inputRegNum);
        assertEquals(expResult, result);
    }

    // test multiple result 
    @Test
    public void testGetTollEventWithReg3() throws Exception
    {
        System.out.println("getTollEventWithReg");
        String inputRegNum = "201LH305";
        MySqlTollEventDAO instance = new MySqlTollEventDAO();
        Set<TollEvent> expResult = new HashSet<>();
        expResult.add(new TollEvent("201LH305", 30416, Instant.parse("2020-02-15T12:15:44Z")));
        expResult.add(new TollEvent("201LH305", 30424, Instant.parse("2020-02-16T11:16:52Z")));
        Set<TollEvent> result = instance.getTollEventWithReg(inputRegNum);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTollEventSinceTimeOf method, of class MySqlTollEventDAO.
     */
    public void testGetTollEventSinceTimeOf() throws Exception
    {
        System.out.println("getTollEventSinceTimeOf");
        Timestamp timeSince = Timestamp.valueOf("2020-03-12 16:20:04");
        MySqlTollEventDAO instance = new MySqlTollEventDAO();
        Set<TollEvent> result = instance.getTollEventSinceTimeOf(timeSince);
        assertEquals(0, result.size());
    }

    @Test
    public void testGetTollEventSinceTimeOf1() throws Exception
    {
        System.out.println("getTollEventSinceTimeOf");
        Timestamp timeSince = Timestamp.valueOf("2020-02-17 16:20:04");
        MySqlTollEventDAO instance = new MySqlTollEventDAO();
        Set<TollEvent> expResult = new HashSet<>();
        expResult.add(new TollEvent("181MH3456", 30437, Instant.parse("2020-02-17T18:20:06Z")));
        expResult.add(new TollEvent("181MH3456", 30436, Instant.parse("2020-02-17T17:33:05Z")));
        expResult.add(new TollEvent("181MH3461", 30441, Instant.parse("2020-02-17T23:25:10Z")));
        expResult.add(new TollEvent("181MH3458", 30438, Instant.parse("2020-02-17T18:20:07Z")));
        expResult.add(new TollEvent("201LH304", 30435, Instant.parse("2020-02-17T16:20:04Z")));
        expResult.add(new TollEvent("181MH3459", 30439, Instant.parse("2020-02-17T18:58:08Z")));
        Set<TollEvent> result = instance.getTollEventSinceTimeOf(timeSince);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTollEventSinceTimeOf method, of class MySqlTollEventDAO.
     */
    @Test
    public void testGetTollEventSinceTimeOf2() throws Exception
    {
        System.out.println("getTollEventSinceTimeOf");
        Timestamp timeSince = Timestamp.valueOf("2020-03-12 00:00:00");
        MySqlTollEventDAO instance = new MySqlTollEventDAO();
        Set<TollEvent> result = instance.getTollEventSinceTimeOf(timeSince);
        assertEquals(0, result.size());
    }

    /**
     * Test of getTollEventSinceTimeOf method, of class MySqlTollEventDAO.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetTollEventSinceTimeOf3() throws Exception
    {
        System.out.println("getTollEventSinceTimeOf");
        Timestamp timeSince = Timestamp.valueOf("2020-03-12");
        MySqlTollEventDAO instance = new MySqlTollEventDAO();
        Set<TollEvent> result = instance.getTollEventSinceTimeOf(timeSince);
        assertEquals(0, result.size());
    }

    /**
     * Test of getTollEventBetweenTimeOf method, of class MySqlTollEventDAO.
     */
    @Test
    public void testGetTollEventBetweenTimeOf() throws Exception
    {
        System.out.println("getTollEventBetweenTimeOf");
        Timestamp timeAfter = Timestamp.valueOf("2020-03-12 16:20:04");
        Timestamp timeBefore = Timestamp.valueOf("2020-03-12 16:20:04");
        MySqlTollEventDAO instance = new MySqlTollEventDAO();
        Set<TollEvent> result = instance.getTollEventBetweenTimeOf(timeAfter, timeBefore);
        assertEquals(0, result.size());
    }

    /**
     * Test of getTollEventBetweenTimeOf method, of class MySqlTollEventDAO.
     */
    public void testGetTollEventBetweenTimeOf1() throws Exception
    {
        System.out.println("getTollEventSinceTimeOf");
        Timestamp timeAfter = Timestamp.valueOf("2020-02-17 16:20:04");
        Timestamp timeBefore = Timestamp.valueOf("2020-02-17 23:24:23");
        MySqlTollEventDAO instance = new MySqlTollEventDAO();
        Set<TollEvent> result = instance.getTollEventBetweenTimeOf(timeAfter, timeBefore);
        Set<TollEvent> expResult = new HashSet<>();
        expResult.add(new TollEvent("181MH3456", 30437, Instant.parse("2020-02-17T18:20:06Z")));
        expResult.add(new TollEvent("181MH3456", 30436, Instant.parse("2020-02-17T17:33:05Z")));
        expResult.add(new TollEvent("181MH3458", 30438, Instant.parse("2020-02-17T18:20:07Z")));
        expResult.add(new TollEvent("201LH304", 30435, Instant.parse("2020-02-17T16:20:04Z")));
        expResult.add(new TollEvent("181MH3459", 30439, Instant.parse("2020-02-17T18:58:08Z")));
        assertEquals(expResult, result);
    }

    /**
     * Test of getTollEventBetweenTimeOf method, of class MySqlTollEventDAO.
     */
    @Test
    public void testGetTollEventBetweenTimeOf2() throws Exception
    {
        System.out.println("getTollEventBetweenTimeOf");
        Timestamp timeAfter = Timestamp.valueOf("2020-02-17 16:20:04");
        Timestamp timeBefore = Timestamp.valueOf("2020-02-17 16:20:04");
        MySqlTollEventDAO instance = new MySqlTollEventDAO();
        Set<TollEvent> expResult = new HashSet<>();
        expResult.add(new TollEvent("201LH304", 30435, Instant.parse("2020-02-17T16:20:04Z")));
        Set<TollEvent> result = instance.getTollEventBetweenTimeOf(timeAfter, timeBefore);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTollEventBetweenTimeOf method, of class MySqlTollEventDAO.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetTollEventBetweenTimeOf3() throws Exception
    {
        System.out.println("getTollEventBetweenTimeOf");
        Timestamp timeAfter = Timestamp.valueOf("2020-03-12");
        Timestamp timeBefore = Timestamp.valueOf("2020-03-12 16:20:04");
        MySqlTollEventDAO instance = new MySqlTollEventDAO();
        Set<TollEvent> result = instance.getTollEventBetweenTimeOf(timeAfter, timeBefore);
        assertEquals(0, result.size());
    }

    /**
     * Test of getRegPassToll method, of class MySqlTollEventDAO.
     */
    @Test
    public void testGetRegPassToll() throws Exception
    {
        System.out.println("getRegPassToll");
        MySqlTollEventDAO instance = new MySqlTollEventDAO();
        Set<String> expResult = new TreeSet<>();
        expResult.add("152DL345");
        expResult.add("161C3457");
        expResult.add("181MH3456");
        expResult.add("181MH3458");
        expResult.add("181MH3459");
        expResult.add("181MH3461");
        expResult.add("191LH1111");
        expResult.add("191LH1112");
        expResult.add("191LH1113");
        expResult.add("191LH1114");
        expResult.add("192D33457");
        expResult.add("201CN3456");
        expResult.add("201CN3457");
        expResult.add("201LH3025");
        expResult.add("201LH304");
        expResult.add("201LH305");
        expResult.add("201LH306");
        expResult.add("201LH3064");
        expResult.add("201LH307");
        expResult.add("201LH3076");
        expResult.add("201LH308");
        expResult.add("201LH3083");
        expResult.add("201LH309");
        expResult.add("201LH310");
        expResult.add("201LH311");
        expResult.add("201LH312");
        Set<String> result = instance.getRegPassToll();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllTollEventsIntoList method, of class MySqlTollEventDAO.
     */
    @Test
    public void testGetAllTollEventsIntoList() throws Exception
    {
        System.out.println("getAllTollEventsIntoList");
        MySqlTollEventDAO instance = new MySqlTollEventDAO();
        List<TollEvent> expResult = new ArrayList<>();
        expResult.add(new TollEvent("152DL345", 30422, Instant.parse("2020-02-16T11:16:50Z")));
        expResult.add(new TollEvent("161C3457", 30410, Instant.parse("2020-02-14T22:15:38Z")));
        expResult.add(new TollEvent("181MH3456", 30436, Instant.parse("2020-02-17T17:33:05Z")));
        expResult.add(new TollEvent("181MH3456", 30437, Instant.parse("2020-02-17T18:20:06Z")));
        expResult.add(new TollEvent("181MH3458", 30438, Instant.parse("2020-02-17T18:20:07Z")));
        expResult.add(new TollEvent("181MH3459", 30439, Instant.parse("2020-02-17T18:58:08Z")));
        expResult.add(new TollEvent("181MH3461", 30441, Instant.parse("2020-02-17T23:25:10Z")));
        expResult.add(new TollEvent("191LH1111", 30402, Instant.parse("2020-02-14T10:15:30Z")));
        expResult.add(new TollEvent("191LH1111", 30411, Instant.parse("2020-02-14T23:15:39Z")));
        expResult.add(new TollEvent("191LH1111", 30421, Instant.parse("2020-02-16T11:16:49Z")));
        expResult.add(new TollEvent("191LH1111", 30432, Instant.parse("2020-02-17T13:20:01Z")));
        expResult.add(new TollEvent("191LH1112", 30412, Instant.parse("2020-02-15T12:15:40Z")));
        expResult.add(new TollEvent("191LH1113", 30413, Instant.parse("2020-02-15T12:15:41Z")));
        expResult.add(new TollEvent("191LH1114", 30414, Instant.parse("2020-02-15T12:15:42Z")));
        expResult.add(new TollEvent("192D33457", 30409, Instant.parse("2020-02-14T16:15:37Z")));
        expResult.add(new TollEvent("201CN3456", 30433, Instant.parse("2020-02-17T14:25:02Z")));
        expResult.add(new TollEvent("201CN3457", 30434, Instant.parse("2020-02-17T16:20:03Z")));
        expResult.add(new TollEvent("201LH3025", 30408, Instant.parse("2020-02-14T15:15:36Z")));
        expResult.add(new TollEvent("201LH304", 30405, Instant.parse("2020-02-14T13:15:33Z")));
        expResult.add(new TollEvent("201LH304", 30415, Instant.parse("2020-02-15T12:15:43Z")));
        expResult.add(new TollEvent("201LH304", 30423, Instant.parse("2020-02-16T11:16:51Z")));
        expResult.add(new TollEvent("201LH304", 30435, Instant.parse("2020-02-17T16:20:04Z")));
        expResult.add(new TollEvent("201LH305", 30416, Instant.parse("2020-02-15T12:15:44Z")));
        expResult.add(new TollEvent("201LH305", 30424, Instant.parse("2020-02-16T11:16:52Z")));
        expResult.add(new TollEvent("201LH306", 30417, Instant.parse("2020-02-15T12:15:45Z")));
        expResult.add(new TollEvent("201LH3064", 30425, Instant.parse("2020-02-16T11:16:53Z")));
        expResult.add(new TollEvent("201LH307", 30418, Instant.parse("2020-02-15T12:15:46Z")));
        expResult.add(new TollEvent("201LH3076", 30426, Instant.parse("2020-02-16T11:16:54Z")));
        expResult.add(new TollEvent("201LH308", 30419, Instant.parse("2020-02-15T21:15:47Z")));
        expResult.add(new TollEvent("201LH3083", 30427, Instant.parse("2020-02-16T11:16:55Z")));
        expResult.add(new TollEvent("201LH309", 30428, Instant.parse("2020-02-16T11:16:56Z")));
        expResult.add(new TollEvent("201LH310", 30429, Instant.parse("2020-02-16T11:16:57Z")));
        expResult.add(new TollEvent("201LH311", 30430, Instant.parse("2020-02-16T11:16:58Z")));
        expResult.add(new TollEvent("201LH312", 30431, Instant.parse("2020-02-16T11:16:59Z")));
        List<TollEvent> result = instance.getAllTollEventsIntoList();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of insertAllTollEvents method, of class MySqlTollEventDAO.
     */
    @Test
    public void testInsertAllTollEvents() throws Exception
    {
        MySqlTollEventDAO instance = new MySqlTollEventDAO();
        instance.cleanUpTollEvents();
        System.out.println("insertAllTollEvents");
        Map<String, ArrayList<TollEvent>> tollEvents = new TreeMap<>();
        ArrayList<TollEvent> t1 = new ArrayList<>();
        t1.add(new TollEvent("152DL345", 30422, Instant.parse("2020-02-16T11:16:50Z")));
        tollEvents.put("152DL345", t1);
        ArrayList<TollEvent> t2 = new ArrayList<>();
        t2.add(new TollEvent("161C3457", 30410, Instant.parse("2020-02-14T22:15:38Z")));
        tollEvents.put("161C3457", t2);
        ArrayList<TollEvent> t3 = new ArrayList<>();
        t3.add(new TollEvent("181MH3456", 30436, Instant.parse("2020-02-17T17:33:05Z")));
        t3.add(new TollEvent("181MH3456", 30437, Instant.parse("2020-02-17T18:20:06Z")));
        tollEvents.put("181MH3456", t3);
        ArrayList<TollEvent> t4 = new ArrayList<>();
        t4.add(new TollEvent("181MH3458", 30438, Instant.parse("2020-02-17T18:20:07Z")));
        tollEvents.put("181MH3458", t4);
        ArrayList<TollEvent> t5 = new ArrayList<>();
        t5.add(new TollEvent("181MH3459", 30439, Instant.parse("2020-02-17T18:58:08Z")));
        tollEvents.put("181MH3459", t5);
        ArrayList<TollEvent> t6 = new ArrayList<>();
        t6.add(new TollEvent("181MH3461", 30441, Instant.parse("2020-02-17T23:25:10Z")));
        tollEvents.put("181MH3461", t6);
        ArrayList<TollEvent> t7 = new ArrayList<>();
        t7.add(new TollEvent("191LH1111", 30402, Instant.parse("2020-02-14T10:15:30Z")));
        t7.add(new TollEvent("191LH1111", 30411, Instant.parse("2020-02-14T23:15:39Z")));
        t7.add(new TollEvent("191LH1111", 30421, Instant.parse("2020-02-16T11:16:49Z")));
        t7.add(new TollEvent("191LH1111", 30432, Instant.parse("2020-02-17T13:20:01Z")));
        tollEvents.put("191LH1111", t7);
        ArrayList<TollEvent> t8 = new ArrayList<>();
        t8.add(new TollEvent("191LH1112", 30412, Instant.parse("2020-02-15T12:15:40Z")));
        tollEvents.put("191LH1112", t8);
        ArrayList<TollEvent> t9 = new ArrayList<>();
        t9.add(new TollEvent("191LH1113", 30413, Instant.parse("2020-02-15T12:15:41Z")));
        tollEvents.put("191LH1113", t9);
        ArrayList<TollEvent> t10 = new ArrayList<>();
        t10.add(new TollEvent("191LH1114", 30414, Instant.parse("2020-02-15T12:15:42Z")));
        tollEvents.put("191LH1114", t10);
        ArrayList<TollEvent> t11 = new ArrayList<>();
        t11.add(new TollEvent("192D33457", 30409, Instant.parse("2020-02-14T16:15:37Z")));
        tollEvents.put("192D33457", t11);
        ArrayList<TollEvent> t12 = new ArrayList<>();
        t12.add(new TollEvent("201CN3456", 30433, Instant.parse("2020-02-17T14:25:02Z")));
        tollEvents.put("201CN3456", t12);
        ArrayList<TollEvent> t13 = new ArrayList<>();
        t13.add(new TollEvent("201CN3457", 30434, Instant.parse("2020-02-17T16:20:03Z")));
        tollEvents.put("201CN3457", t13);
        ArrayList<TollEvent> t14 = new ArrayList<>();
        t14.add(new TollEvent("201LH3025", 30408, Instant.parse("2020-02-14T15:15:36Z")));
        tollEvents.put("201LH3025", t14);
        ArrayList<TollEvent> t15 = new ArrayList<>();
        t15.add(new TollEvent("201LH304", 30405, Instant.parse("2020-02-14T13:15:33Z")));
        t15.add(new TollEvent("201LH304", 30415, Instant.parse("2020-02-15T12:15:43Z")));
        t15.add(new TollEvent("201LH304", 30423, Instant.parse("2020-02-16T11:16:51Z")));
        t15.add(new TollEvent("201LH304", 30435, Instant.parse("2020-02-17T16:20:04Z")));
        tollEvents.put("201LH304", t15);
        ArrayList<TollEvent> t16 = new ArrayList<>();
        t16.add(new TollEvent("201LH305", 30416, Instant.parse("2020-02-15T12:15:44Z")));
        t16.add(new TollEvent("201LH305", 30424, Instant.parse("2020-02-16T11:16:52Z")));
        tollEvents.put("201LH305", t16);
        ArrayList<TollEvent> t17 = new ArrayList<>();
        t17.add(new TollEvent("201LH306", 30417, Instant.parse("2020-02-15T12:15:45Z")));
        tollEvents.put("201LH306", t17);
        ArrayList<TollEvent> t18 = new ArrayList<>();
        t18.add(new TollEvent("201LH3064", 30425, Instant.parse("2020-02-16T11:16:53Z")));
        tollEvents.put("201LH3064", t18);
        ArrayList<TollEvent> t19 = new ArrayList<>();
        t19.add(new TollEvent("201LH307", 30418, Instant.parse("2020-02-15T12:15:46Z")));
        tollEvents.put("201LH307", t19);
        ArrayList<TollEvent> t20 = new ArrayList<>();
        t20.add(new TollEvent("201LH3076", 30426, Instant.parse("2020-02-16T11:16:54Z")));
        tollEvents.put("201LH3076", t20);
        ArrayList<TollEvent> t21 = new ArrayList<>();
        t21.add(new TollEvent("201LH308", 30419, Instant.parse("2020-02-15T21:15:47Z")));
        tollEvents.put("201LH308", t21);
        ArrayList<TollEvent> t22 = new ArrayList<>();
        t22.add(new TollEvent("201LH3083", 30427, Instant.parse("2020-02-16T11:16:55Z")));
        tollEvents.put("201LH3083", t22);
        ArrayList<TollEvent> t23 = new ArrayList<>();
        t23.add(new TollEvent("201LH309", 30428, Instant.parse("2020-02-16T11:16:56Z")));
        tollEvents.put("201LH309", t23);
        ArrayList<TollEvent> t24 = new ArrayList<>();
        t24.add(new TollEvent("201LH310", 30429, Instant.parse("2020-02-16T11:16:57Z")));
        tollEvents.put("201LH310", t24);
        ArrayList<TollEvent> t25 = new ArrayList<>();
        t25.add(new TollEvent("201LH311", 30430, Instant.parse("2020-02-16T11:16:58Z")));
        tollEvents.put("201LH311", t25);
        ArrayList<TollEvent> t26 = new ArrayList<>();
        t26.add(new TollEvent("201LH312", 30431, Instant.parse("2020-02-16T11:16:59Z")));
        tollEvents.put("201LH312", t26);

        Map<String, ArrayList<TollEvent>> expResult = tollEvents;
        boolean insert = instance.insertAllTollEvents(tollEvents);
        Map<String, ArrayList<TollEvent>> result = instance.getAllTollEvents();
        assertEquals(expResult, result);
    }
}
