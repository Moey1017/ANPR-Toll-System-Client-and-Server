/**
 *
 * D00217017 Jing Sheng Moey
 * SD2A
 */
package Server_Client;

import java.io.File;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.Socket;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Client
{

    private Map<String, ArrayList<TollEvent>> tollEventsInMemory = new TreeMap<>();
    private ArrayList<TollEvent> invalidList = new ArrayList<>();
    private Set<String> vehicleRegSet = new TreeSet<>();
    private int countTollEvent = 0;

    public static void main(String[] args)
    {
        Client client = new Client();
        client.start();
    }

    public void start()
    {
        try
        {
            boolean running = true;
            Scanner in = new Scanner(System.in);
            Socket socket = new Socket("localhost", 8080); // connect to server socket, and open new socket
            OutputStream os = socket.getOutputStream();
            PrintWriter socketWriter = new PrintWriter(os, true);// true=> auto flush buffers
            Scanner socketReader = new Scanner(socket.getInputStream()); // get input stream

            System.out.println("Client: Port# of this client : " + socket.getLocalPort());
            System.out.println("Client: Port# of Server :" + socket.getPort());
            System.out.println("Client: This Client is running and has connected to the server\n");

            //get heartBeat
            String packetToBeSent = getPacket("HeartBeat"); // get heartbeat response when client run
            socketWriter.println(packetToBeSent); // write command to socket
            String getHeartBeatResponse = socketReader.nextLine();  // getting heart beat respsonse here 
            StringReader sr = new StringReader(getHeartBeatResponse);
            JsonObject jsonMessage = Json.createReader(sr).readObject();
            String value = jsonMessage.getString("PacketType");
            if (value.equals("Heartbeat response"))
            {
                System.out.println("Respsone From server:" + getHeartBeatResponse);
                System.out.println("The server is listening.\n");
            }
            else
            {
                System.out.println(value);
            }

            //get registered Vehicle
            String getRegisteredPacket = getPacket("GetRegisteredVehicles");
            socketWriter.println(getRegisteredPacket); // write command to socket
            String getRegisteredVehicles = socketReader.nextLine();
            sr = new StringReader(getRegisteredVehicles);
            jsonMessage = Json.createReader(sr).readObject();
            value = jsonMessage.getString("PacketType");
            if (value.equals("ReturnRegisteredVehicles"))
            {
                System.out.println("Receiving Vehicle Registration from server.");
                JsonArray vehicleRegArray = jsonMessage.getJsonArray("Vehicles");
                for (int i = 0; i < vehicleRegArray.size(); i++)
                {
                    vehicleRegSet.add(vehicleRegArray.getString(i));
                    System.out.println(vehicleRegArray.getString(i));
                }
                System.out.println((vehicleRegArray.size() + 1) + " Vehicle registration number has been sent from server and stored locally in client.\n");
            }
            else
            {
                System.out.println("Something went wrong.");
            }
            scanTollEventFileToMemory();
            while (running)
            {
                try
                {
                    displayMenuList();
                    int command = Utilities.getValidInt(0, 3, "Please enter a command ");
                    if (command == 1)
                    {
                        int totalValidRecordSent = 0;
                        int totalValidRecordInserted = 0;
                        Iterator<Map.Entry<String, ArrayList<TollEvent>>> itr = tollEventsInMemory.entrySet().iterator();
                        while (itr.hasNext())
                        {
                            Map.Entry<String, ArrayList<TollEvent>> entry = itr.next();
                            List<TollEvent> list = entry.getValue();
                            Iterator<TollEvent> t = list.iterator();
                            while (t.hasNext())
                            {
                                TollEvent temp = t.next(); // get this Toll Event 
                                String jsonRootObject = getStringJsonRootObject(temp);
                                socketWriter.println(jsonRootObject);
                                System.out.println("Toll Event has been sent.");
                                totalValidRecordSent++;
                                String getPacket = socketReader.nextLine();
                                sr = new StringReader(getPacket);
                                jsonMessage = Json.createReader(sr).readObject();
                                value = jsonMessage.getString("PacketType");
                                System.out.println(value);
                                if (value.equals("RegisteredValidTollEvent"))
                                {
                                    totalValidRecordInserted++;
                                    t.remove();
                                    countTollEvent--;
                                }
                            }
                            if (!itr.hasNext())
                            {
                                itr.remove();
                            }
                        }
                        Utilities.getStringLine("*", 50);
                        System.out.println("Total Valid Records Sent:" + totalValidRecordSent);
                        System.out.println("Total Valid Records Inserted:" + totalValidRecordInserted);
                        System.out.println("Total valid Toll Event in Memory:" + countTollEvent);
                        Utilities.getStringLine("*", 50);
                        Utilities.awaitForEnter();
                    }
                    else if (command == 2)
                    {
                        int totalInvalidRecordSent = 0;
                        Iterator<TollEvent> t = invalidList.iterator();
                        while (t.hasNext())
                        {
                            TollEvent temp = t.next();
                            String jsonRootObject = getStringJsonRootObject(temp);
                            socketWriter.println(jsonRootObject);
                            System.out.println("Toll Event has been sent.");
                            String getPacket = socketReader.nextLine();
                            sr = new StringReader(getPacket);
                            jsonMessage = Json.createReader(sr).readObject();
                            value = jsonMessage.getString("PacketType");
                            System.out.println(value);
                            if (value.equals("RegisteredInvalidTollEvent"))
                            {
                                totalInvalidRecordSent++;
                                t.remove();
                            }
                        }
                        Utilities.getStringLine("*", 50);
                        System.out.println("Total Invalid Records Sent:" + totalInvalidRecordSent);
                        System.out.println("Total Invalid Toll Event in Memory:" + invalidList.size());
                        Utilities.getStringLine("*", 50);
                        Utilities.awaitForEnter();
                    }
                    else if (command == 3)
                    {
                        //get heartBeat
                        packetToBeSent = getPacket("HeartBeat"); // get heartbeat response when client run
                        socketWriter.println(packetToBeSent); // write command to socket
                        getHeartBeatResponse = socketReader.nextLine();  // getting heart beat respsonse here 
                        sr = new StringReader(getHeartBeatResponse);
                        jsonMessage = Json.createReader(sr).readObject();
                        value = jsonMessage.getString("PacketType");
                        if (value.equals("Heartbeat response"))
                        {
                            System.out.println("Respsone From server:" + getHeartBeatResponse);
                            System.out.println("The server is listening.");
                        }
                        else
                        {
                            System.out.println(value);
                        }
                        Utilities.awaitForEnter();
                    }
                    else if (command == 0)
                    {
                        boolean quit = Utilities.getValidBoolean("Do you sure you want to quit?(Y/N)");
                        if (quit)
                        {
                            packetToBeSent = getPacket("Close"); // get heartbeat response when client run
                            socketWriter.println(packetToBeSent); // write command to socket
                            running = false;
                            socketWriter.close();
                            socketReader.close();
                            socket.close();
                        }
                    }
                    else
                    {
                        System.out.println();
                        Utilities.awaitForEnter();
                    }
                } catch (IOException e)
                {
                    System.out.println("Client message: IOException: " + e);
                }
            }
        } catch (IOException e)
        {
            System.out.println("Client message: IOException: " + e);
        }
        System.out.println("System Close Goodbye.");
    }

    public static String getPacket(String cmd)
    {
        JsonObject jsonRootObject
                = Json.createObjectBuilder()
                        .add("PacketType", cmd)
                        .build();
        return jsonRootObject.toString();
    }

    /**
     * Store all tollEvents into Memory
     */
    private void scanTollEventFileToMemory()
    {
        tollEventsInMemory = scanTollEventsFile("Toll-Events.csv"); // read toll system into memory
        if (!tollEventsInMemory.isEmpty())
        {
            countTollEvent = 0;
            Set<String> keys = tollEventsInMemory.keySet();
            for (String k : keys)
            {
                countTollEvent += tollEventsInMemory.get(k).size();
            }
            System.out.println("Toll Events have been read and store in Client Memory");
            System.out.println("Total Record(s)         : " + countTollEvent);
            System.out.println("Total Invalid Records(s): " + invalidList.size());
        }
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
     * Check if this registration is existed
     *
     * @param regNum vehicle registration number
     * @return true of false
     */
    private boolean ifRegExist(String regNum)
    {
        return vehicleRegSet.contains(regNum);
    }

    private void displayMenuList()
    {
        ArrayList<String> menu = new ArrayList<>();
        menu.add("*******************************************************************************");
        menu.add("Please Select an option.");
        menu.add("0. Quit the system");
        menu.add("1. Send Toll Event to server");
        menu.add("2. Send Invalid Toll Event to server");
        menu.add("3. Check heartbeat");
        menu.add("*******************************************************************************");
        for (String m : menu)
        {
            System.out.println(m);
        }
    }

    /**
     * Build JsonRootObject and turns into String
     *
     * @param t this toll Event
     * @return a String of Json Root Object
     */
    String getStringJsonRootObject(TollEvent t)
    {
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonArrayBuilder arrayBuilder = factory.createArrayBuilder();

        arrayBuilder.add(Json.createObjectBuilder()
                .add("Reg", t.getRegistration())
                .add("ImageId", t.getImageID())
                .add("TimeStamp", t.getTimestamp().toString())
                .build()
        );
        // build the JsonArray
        JsonArray jsonArray = arrayBuilder.build();

        // wrap the JsonArray in a JsonObject and give the JsonArray a key name
        JsonObject jsonRootObject
                = Json.createObjectBuilder()
                        .add("PacketType", "RegisterValidTollEvent")
                        .add("TollEvent", jsonArray)
                        .build();
        return "";
    }
}
