/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server_Client;

import DAO.VehicleDAOInterface;
import DAO.TollEventDAOInterface;
import DAO.MySqlVehicleDAO;
import DAO.MySqlTollEventDAO;
import Exceptions.DaoException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;

/**
 *
 * D00217017 Jing Sheng Moey SD2A
 */
public class TollSystem
{

    private final Scanner sc = new Scanner(System.in);
    private final VehicleDAOInterface IVehicleDao = new MySqlVehicleDAO();
    private final TollEventDAOInterface ITollEventDao = new MySqlTollEventDAO();
    private final Set<String> vehiclesList = new TreeSet<>();
    private ArrayList<TollEvent> invalidList = new ArrayList<>();
    private Map<String, ArrayList<TollEvent>> tollEventsInMemory = new TreeMap<>();

    // File paths
    private final String VEHICLE_FILE_PATH = "Vehicles.csv";
    private final String TOLL_EVENTS_FILE_PATH = "Toll-Events.csv";

    //Error Message
    private final String ERROR = "Something Went Wrong.";
    private final String ERROR_FORMAT_INCORRECT = "Format Incorect.";

    //Message
    private final String MSG_GOOD_BYE = "GoodBye.";
    private final String MSG_READ = "have been read in memory.";
    private final String MSG_NO_RECORD = "No Record has been found.";
    private final String MSG_QUIT = "Do you sure to quit system?(Y/N)";
    private final String MSG_VALID_OPTIONS = "[0-9]";
    private final String MSG_SELECT_OPTION = "Please select an option.";
    private final String MSG_RECORDS_FOUND = "record(s) has been found.";

    /**
     * First Starting point Run Application
     */
    public void run()
    {
        if (!vehiclesList.isEmpty())
        {
            System.out.println("Vehicle Registration numbers " + MSG_READ + "\n");
        }
        printMenuOptions();
        int option = Utilities.getValidInt(0, 9, MSG_VALID_OPTIONS);
        boolean running = true;
        try
        {
            while (running)
            {
                switch (option)
                {
                    case 0:
                        if (Utilities.getValidBoolean(MSG_QUIT))
                        {
                            running = false;
                        }
                        break;
                    case 1:
                        scanTollEventFileToMemory();
                        break;
                    case 2:
                        readTollEventsIntoDatabase(); // read all toll events into database
                        break;
                    case 3:
                        display(readTollEventsFromDatabaseIntoList());
                        break;
                    case 4:
                        TollEventWithReg();
                        break;
                    case 5:
                        TollEventsSinceTimeOf();
                        break;
                    case 6:
                        TollEventsBetweenTimeOf(); // doesnt work 
                        break;
                    case 7:
                        regPassToll();
                        break;
                    case 8:
                        display(readTollEventsFromDatabaseIntoMap());//get all toll events 
                        break;
                    default:
                        display(tollEventsInMemory);//get all toll events 
                        break;
                }

                if (running == true)
                {
                    Utilities.awaitForEnter();
                    printMenuOptions();
                    option = Utilities.getValidInt(0, 9, MSG_VALID_OPTIONS);
                }
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println(MSG_GOOD_BYE);
    }
    
    /**
     * Prints the systems menu options.
     */
    private void printMenuOptions()
    {
        ArrayList<String> menuOptions = new ArrayList<>();
        menuOptions.add("Menu");
        menuOptions.add(Utilities.getStringLine("*", 75));
        menuOptions.add("1. Scan toll events from file into Memory");
        menuOptions.add("2. Store toll events into database");
        menuOptions.add("3. Get all toll events details into List from database");
        menuOptions.add("4. Get Toll Event With Registration Number from database");
        menuOptions.add("5. Get Toll Events since time of from database");
        menuOptions.add("6. Get Toll Events between time of from database");
        menuOptions.add("7. Get Registration passed toll from database");
        menuOptions.add("8. Get all toll events details from database into Map");
        menuOptions.add("9. Get all toll events details in Memory");
        menuOptions.add("0. Quit Toll System ");
        menuOptions.add(Utilities.getStringLine("*", 75));
        menuOptions.add("");

        for (String option : menuOptions)
        {
            System.out.println(option);
        }

        System.out.print(MSG_SELECT_OPTION);
    }

    /**
     *
     * @param filePath vehicle registration file path
     * @return HashSet of Vehicles from file
     */
    private HashSet<Vehicle> scanVehicleFile(String filePath)
    {
        HashSet<Vehicle> vehicles = new HashSet<>();
        String delimiter = "[,\n\r\t]+";
        try (Scanner sc = new Scanner(new File(filePath)))
        {
            sc.useDelimiter(delimiter);
            while (sc.hasNextLine() && sc.hasNext())
            {
                Scanner scanLine = new Scanner(sc.nextLine());
                scanLine.useDelimiter(delimiter);
                while (scanLine.hasNext())
                {
                    String regNum = scanLine.next();
                    Vehicle v = new Vehicle(regNum);
                    vehicles.add(v);
                }
            }
            sc.close();
        } catch (IOException e)
        {
            System.out.println(e);
        }
        return vehicles;
    }

    /**
     * Read Vehicle object into database
     */
    private void readVehicleRegIntoDatabase()
    {
        //read files
        HashSet<Vehicle> vehicles = scanVehicleFile(VEHICLE_FILE_PATH);
        try
        {
            boolean insertVehicles = IVehicleDao.insertAllVehicles(vehicles);
            if (insertVehicles == true)
            {
                System.out.println("Insert Successfully");
            }
            else
            {
                System.out.println("Fail to insert");
            }
        } catch (DaoException e)
        {
            System.out.println(e.getMessage());
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @return a set of vehicle object from database
     */
    private Set<Vehicle> readVehicleRegFromDatabase()
    {
        Set<Vehicle> vehicles = new HashSet<>();
        try
        {
            vehicles = IVehicleDao.getAllVehicle();
        } catch (DaoException e)
        {
            System.out.println(e.getMessage());
        }
        return vehicles;
    }

    /**
     * getAllVehicleRegInString
     *
     * @return a set vehicle registration numbers
     */
    private Set<String> getAllVehicleRegInString()
    {
        Set<Vehicle> vehicles = readVehicleRegFromDatabase();
        Set<String> vReg = new TreeSet<>();
        for (Vehicle v : vehicles)
        {
            vReg.add(v.getRegistrationNumber());
        }
        return vReg;
    }

    /**
     * Check if this registration is existed
     *
     * @param regNum vehicle registration number
     * @return true of false
     */
    private boolean ifRegExist(String regNum)
    {
        return vehiclesList.contains(regNum);
    }

    /**
     * Get all toll events from file
     *
     * @param filePath toll events file path
     * @return a Map of String, ArrayList of TollEvent
     */
    private Map<String, ArrayList<TollEvent>> scanTollEventsFile(String filePath)
    {
        Map<String, ArrayList<TollEvent>> tollEvents = new TreeMap<>();

        String delimiter = "[;\n\r\t]+";
        try (Scanner sc = new Scanner(new File(filePath)))
        {
            sc.useDelimiter(delimiter);
            while (sc.hasNextLine() && sc.hasNext())
            {
                Scanner scanLine = new Scanner(sc.nextLine()); // scan each thing in line 
                scanLine.useDelimiter(delimiter);
                while (scanLine.hasNext())
                {
                    String regNum = scanLine.next();
                    long imageID = scanLine.nextLong();
                    String s_timeStamp = scanLine.next();
                    Instant timeStamp = Instant.parse(s_timeStamp);
                    TollEvent t = new TollEvent(regNum, imageID, timeStamp);
                    if (ifRegExist(regNum))
                    {
                        ArrayList<TollEvent> tempTollEventsList = tollEvents.get(regNum);
                        if (tempTollEventsList == null)
                        {
                            tempTollEventsList = new ArrayList<>();
                        }
                        tempTollEventsList.add(t);
                        tollEvents.put(regNum, tempTollEventsList);
                    }
                    else
                    {
                        if (!invalidList.contains(t))
                        {
                            invalidList.add(t);
                        }
                    }
                }
            }
            sc.close();
        } catch (IOException e)
        {
            System.out.println(e);
        }
        return tollEvents;
    }

    /**
     * Store all tollEvents into Memory
     */
    private void scanTollEventFileToMemory()
    {
        tollEventsInMemory = scanTollEventsFile("Toll-Events.csv"); // read toll system into memory
        if (!tollEventsInMemory.isEmpty())
        {
            int count = 0;

            Set<String> keys = tollEventsInMemory.keySet();
            for (String k : keys)
            {
                count += tollEventsInMemory.get(k).size();
            }
            System.out.println("Toll Events have been Store in Memory");
            System.out.println("Total Record(s)         : " + count);
            System.out.println("Total Invalid Records(s): " + invalidList.size());
        }
    }

    /**
     * Read all toll Events in memory into database;
     */
    private void readTollEventsIntoDatabase()
    {
        if (!tollEventsInMemory.isEmpty())
        {
            try
            {
                boolean insertTollEvents = ITollEventDao.insertAllTollEvents(tollEventsInMemory);
                if (insertTollEvents == true)
                {
                    System.out.println("Insert Successfully");
                }
                else
                {
                    System.out.println("Fail to insert");
                }
            } catch (DaoException e)
            {
                System.out.println("Some duplicate found.");
            }
        }
        else
        {
            System.out.println("It's Empty!Fail to insert.");
        }
    }

    /**
     * read all toll events into a Map from database into memory
     *
     * @return Map of String, ArrayList of TollEvent
     */
    private Map<String, ArrayList<TollEvent>> readTollEventsFromDatabaseIntoMap()
    {
        Map<String, ArrayList<TollEvent>> tollEvents = new HashMap<>();
        try
        {
            tollEvents = ITollEventDao.getAllTollEvents();
        } catch (DaoException e)
        {
            System.out.println(e.getMessage());
        }
        return tollEvents;
    }

    /**
     * read all toll events into a list from database into memory
     *
     * @return List of tollEvents
     */
    private List<TollEvent> readTollEventsFromDatabaseIntoList() // this
    {
        List<TollEvent> tollEvents = new ArrayList<>();
        try
        {
            tollEvents = ITollEventDao.getAllTollEventsIntoList();
        } catch (DaoException e)
        {
            System.out.println(e.getMessage());
        }
        return tollEvents;
    }

    /**
     * Display a list of toll Events
     */
    private void display(List<TollEvent> tollEvents)
    {
        int count = 0;
        if (tollEvents.isEmpty())
        {
            System.out.println(MSG_NO_RECORD);
        }
        else
        {
            Utilities.printLine("*", 75);
            System.out.printf("%-20s%-15s%-10s\n", "Registration", "Image ID", "Date and Time");
            Utilities.printLine("*", 75);
            for (int i = 0; i < tollEvents.size(); i++)
            {
                System.out.printf("%-20s%-15s%-10s\n", tollEvents.get(i).getRegistration(), tollEvents.get(i).getImageID(), tollEvents.get(i).getTimestamp());
                count++;
            }
            System.out.println(count + MSG_RECORDS_FOUND);
            Utilities.printLine("*", 75);
        }
    }

    /**
     * Display a Map of toll Events
     */
    private void display(Map<String, ArrayList<TollEvent>> tollEvents)
    {
        int count = 0;
        if (tollEvents.isEmpty())
        {
            System.out.println(MSG_NO_RECORD);
        }
        else
        {
            Utilities.printLine("*", 75);
            System.out.printf("%-20s%-15s%-10s\n", "Registration", "Image ID", "Date and Time");
            Utilities.printLine("*", 75);
            Set<String> keys = tollEvents.keySet();
            for (String key : keys)
            {
                List<TollEvent> temp = tollEvents.get(key);
                int i = 0;
                while (i < temp.size())
                {
                    System.out.printf("%-20s%-15s%-10s\n", temp.get(i).getRegistration(), temp.get(i).getImageID(), temp.get(i).getTimestamp());
                    i++;
                    count++;
                }
            }
            System.out.println(count + MSG_RECORDS_FOUND + "(Memory)");
            System.out.println(invalidList.size() + "record(s) are invalid");
            Utilities.printLine("*", 75);
        }
    }

    /**
     * Display a set of toll Events
     */
    private void display(Set<TollEvent> tollEventSet)
    {
        int count = 0;
        if (tollEventSet.isEmpty())
        {
            System.out.println(MSG_NO_RECORD);
        }
        else
        {
            Utilities.printLine("*", 75);
            System.out.printf("%-20s%-15s%-10s\n", "Registration", "Image ID", "Date and TimSe");
            Utilities.printLine("*", 75);
            for (TollEvent tollEvent : tollEventSet)
            {
                System.out.printf("%-20s%-15s%-10s\n", tollEvent.getRegistration(), tollEvent.getImageID(), tollEvent.getTimestamp());
                count++;
            }
            System.out.println(count + MSG_RECORDS_FOUND);
            Utilities.printLine("*", 75);
        }
    }

    /**
     * Display all registration number in database
     */
    private void displayRegistrations(Set<String> regList)
    {
        int count = 0;
        if (regList.isEmpty())
        {
            System.out.println(MSG_NO_RECORD);
        }
        else
        {
            Utilities.printLine("*", 30);
            System.out.printf("|%-28s|\n", "Registration");
            Utilities.printLine("*", 30);
            for (String registrations : regList)
            {
                System.out.printf("|%-28s|\n", registrations);
                count++;
            }
            System.out.printf("|%-28s|\n", count + MSG_RECORDS_FOUND);
            Utilities.printLine("*", 30);
        }
    }

    /**
     *
     * @param inputReg registration to be searched
     * @return a set of toll event associated with the registration number
     */
    private Set<TollEvent> getTollEventWithReg(String inputReg)
    {
        Set<TollEvent> tollSet = new TreeSet<>();
        try
        {
            tollSet = ITollEventDao.getTollEventWithReg(inputReg);
        } catch (DaoException e)
        {
            System.out.println(e.getMessage());
        }
        return tollSet;
    }

    /**
     * Display all records associated with that registration number
     */
    private void TollEventWithReg()
    {
        boolean running = true;
        Set<TollEvent> tollSet = new TreeSet<>();
        while (running)
        {
            String inputReg = Utilities.getValidString("Search Registration Number, Enter Q to quit.");
            if (!inputReg.equals("Q"))
            {
                tollSet = getTollEventWithReg(inputReg);
                display(tollSet);
            }
            else
            {
                running = false;
            }
        }
    }

    /**
     * get toll events since a specific time
     *
     * @return a set of toll events
     */
    private Set<TollEvent> getTollEventSinceTimeOf()
    {
        Set<TollEvent> tollSet = new HashSet<>();
        try
        {
            Timestamp ts = Utilities.getValidSQLTimeStamp("Enter date and time(yyyy-mm-dd hour:m:s), Q to quit.");
            tollSet = ITollEventDao.getTollEventSinceTimeOf(ts);
        } catch (IllegalArgumentException e)
        {
            System.out.println(ERROR_FORMAT_INCORRECT);
        } catch (DaoException e)
        {
            System.out.println(e.getMessage());
        }
        return tollSet;
    }

    /**
     * get and display a set of toll events since a specific time
     */
    private void TollEventsSinceTimeOf()
    {
        Set<TollEvent> tollSet = getTollEventSinceTimeOf();
        display(tollSet);
    }

    /**
     * get toll events between a specific Date and time
     *
     * @return a set of toll events
     */
    private Set<TollEvent> getTollEventBetweenTimeOf()
    {
        Set<TollEvent> tollSet = new HashSet<>();
        try
        {
            Timestamp startDate = Utilities.getValidSQLTimeStamp("Enter starting date and time(yyyy-mm-dd hour:m:s), Q to quit.");
            Timestamp finishDate = Utilities.getValidSQLTimeStamp("Enter finishing date and time(yyyy-mm-dd hour:m:s), Q to quit.");
            if (startDate.before(finishDate))
            {
                tollSet = ITollEventDao.getTollEventBetweenTimeOf(startDate, finishDate);
            }
            else
            {
                System.out.println("Finish Date should be later than Start date.");
            }
        } catch (IllegalArgumentException e)
        {
            System.out.println(ERROR_FORMAT_INCORRECT);
        } catch (NullPointerException e)
        {
            System.out.println(ERROR);
        } catch (DaoException e)
        {
            System.out.println(e.getMessage());
        }
        return tollSet;
    }

    /**
     * get and display a set of toll events between a specific Date and time
     */
    private void TollEventsBetweenTimeOf()
    {
        Set<TollEvent> tollSet = getTollEventBetweenTimeOf();
        display(tollSet);
    }

    /**
     * get all registration number that has passed toll
     *
     * @return a set of registration number
     */
    private Set<String> getRegPassToll()
    {
        Set<String> regList = new TreeSet<>();
        try
        {
            regList = ITollEventDao.getRegPassToll();
        } catch (DaoException e)
        {
            System.out.println(e.getMessage());
        }
        return regList;
    }

    /**
     * Get and Display registration that has passed toll
     */
    private void regPassToll()
    {
        Set<String> regList = getRegPassToll();
        displayRegistrations(regList);
    }

    private void cleanTollEventsInDatabase()
    {
        try
        {
            ITollEventDao.cleanUpTollEvents(); // trauncate table 
        } catch (DaoException e)
        {
            System.out.println(e.getMessage());
        }

    }
}
