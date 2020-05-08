/**
 *
 * D00217017 Jing Sheng Moey 
 * SD2A
 */
package CA6;

import Exceptions.DaoException;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import javax.json.JsonReader;

public class Server
{

    private final VehicleDAOInterface IVehicleDao = new MySqlVehicleDAO();
    private final TollEventDAOInterface ITollEventDao = new MySqlTollEventDAO();
    private ArrayList<TollEvent> tollEvents = new ArrayList<>();
    private ArrayList<TollEvent> invalidList = new ArrayList<>();
    private Set<Vehicle> vehiclesList = scanVehicleFile("Vehicles.csv");

    public static void main(String[] args)
    {
        try
        {
            Server server = new Server();
            server.start();
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    public void start()
    {
        try
        {
            ServerSocket ss = new ServerSocket(8080);  // set up ServerSocket to listen for connections on port 8080

            System.out.println("Server: Server started. Listening for connections on port 8080...");

            int clientNumber = 0;  // a number for clients that the server allocates as clients connect

            while (true)    // loop continuously to accept new client connections
            {
                Socket socket = ss.accept();    // listen (and wait) for a connection, accept the connection, 
                // and open a new socket to communicate with the client
                clientNumber++;

                System.out.println("Server: Client " + clientNumber + " has connected.");

                System.out.println("Server: Port# of remote client: " + socket.getPort());
                System.out.println("Server: Port# of this server: " + socket.getLocalPort());

                Thread t = new Thread(new ClientHandler(socket, clientNumber)); // create a new ClientHandler for the client,
                t.start();                                                  // and run it in its own thread

                System.out.println("Server: ClientHandler started in thread for client " + clientNumber + ". ");
                System.out.println("Server: Listening for further connections...");
            }
        } catch (IOException e)
        {
            System.out.println("Server: IOException: " + e);
        }
        System.out.println("Server: Server exiting, Goodbye!");
    }

    public class ClientHandler implements Runnable   // each ClientHandler communicates with one Client
    {

        BufferedReader socketReader;
        PrintWriter socketWriter;
        Socket socket;
        int clientNumber;

        public ClientHandler(Socket clientSocket, int clientNumber)
        {
            try
            {
                InputStreamReader isReader = new InputStreamReader(clientSocket.getInputStream());
                this.socketReader = new BufferedReader(isReader);

                OutputStream os = clientSocket.getOutputStream();
                this.socketWriter = new PrintWriter(os, true); // true => auto flush socket buffer

                this.clientNumber = clientNumber;  // ID number that we are assigning to this client

                this.socket = clientSocket;  // store socket ref for closing 

            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }

        @Override
        public void run()
        {
            String message;
            try
            {
                while ((message = socketReader.readLine()) != null)
                {
                    System.out.println("Server: (ClientHandler): Read command from client " + clientNumber + ": " + message);
                    StringReader sr = new StringReader(message);
                    JsonObject jsonMessage = Json.createReader(sr).readObject();
                    String value = jsonMessage.getString("PacketType");
                    // parse Json 
                    if (value.equals("HeartBeat"))
                    {
                        JsonObject jsonRootObject
                                = Json.createObjectBuilder()
                                        .add("PacketType", "Heartbeat response")
                                        .build();

                        String response = jsonRootObject.toString();
                        socketWriter.println(response);
                        System.out.println("Heartbeat has been sent");
                    }
                    else if (value.equals("GetRegisteredVehicles"))
                    {
                        JsonBuilderFactory factory = Json.createBuilderFactory(null);
                        JsonArrayBuilder arrayBuilder = factory.createArrayBuilder();
                        Set<String> carRegs = getAllVehicleRegInString();
                        for (String u : carRegs)
                        {
                            arrayBuilder.add(u);
                        }
                        //build json Array
                        JsonArray jsonArray = arrayBuilder.build();

                        // wrap the JsonArray in a JsonObject and give the JsonArray a key name
                        JsonObject jsonRootObject
                                = Json.createObjectBuilder()
                                        .add("PacketType", "ReturnRegisteredVehicles")
                                        .add("Vehicles", jsonArray)
                                        .build();

                        String response = jsonRootObject.toString();
                        socketWriter.println(response);
                        System.out.println("Vehicle Registraions have been sent.");
                    }
                    else if (value.equals("RegisterValidTollEvent"))
                    {
                        JsonArray arr = jsonMessage.getJsonArray("TollEvent");
                        if (!arr.isEmpty())
                        {
                            JsonObject obj = arr.getJsonObject(0);
                            String reg = obj.getString("Reg");
                            long imageId = obj.getInt("ImageId");
                            Instant timeStamp = Instant.parse(obj.getString("TimeStamp"));
                            tollEvents.add(new TollEvent(reg, imageId, timeStamp));
                            boolean inserted = false;
                            try
                            {
                                inserted = ITollEventDao.insertTollEvent(reg, imageId, timeStamp);
                            } catch (DaoException e)
                            {
                                System.out.println(e.getMessage());
                            }
                            if (inserted)
                            {
                                JsonObject jsonRootObject
                                        = Json.createObjectBuilder()
                                                .add("PacketType", "RegisteredValidTollEvent")
                                                .build();
                                String response = jsonRootObject.toString();
                                socketWriter.println(response);
                            }
                            else
                            {
                                JsonObject jsonRootObject
                                        = Json.createObjectBuilder()
                                                .add("PacketType", "FailedToInsert")
                                                .build();
                                String response = jsonRootObject.toString();
                                socketWriter.println(response);
                            }
                        }
                    }
                    else if (value.equals("RegisterInvalidTollEvent"))
                    {
                        JsonArray arr = jsonMessage.getJsonArray("TollEvent");
                        if (!arr.isEmpty())
                        {
                            JsonObject obj = arr.getJsonObject(0);
                            String reg = obj.getString("Reg");
                            long imageId = obj.getInt("ImageId");
                            Instant timeStamp = Instant.parse(obj.getString("TimeStamp"));

                            TollEvent t = new TollEvent(reg, imageId, timeStamp);
                            invalidList.add(t);
                            System.out.println(t + " has been stored to Memory.");
                            JsonObject jsonRootObject
                                    = Json.createObjectBuilder()
                                            .add("PacketType", "RegisteredInvalidTollEvent")
                                            .build();
                            String response = jsonRootObject.toString();
                            socketWriter.println(response);
                        }
                        else
                        {
                            System.out.println("No Toll Event has been received.");
                        }
                    }
                    else if (value.equals("Close"))
                    {

                    }
                    else
                    {
                        JsonObject jsonRootObject
                                = Json.createObjectBuilder()
                                        .add("PacketType", "Unknown Command")
                                        .build();

                        String response = jsonRootObject.toString();
                        socketWriter.println(response);
                    }
                }
                socket.close();
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
            System.out.println("Server: (ClientHandler): Handler for Client " + clientNumber + " is terminating .....");
        }
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
     * getAllVehicleRegInString
     *
     * @return a set vehicle registration numbers
     */
    private Set<String> getAllVehicleRegInString()
    {
        Set<Vehicle> vehicles = vehiclesList;
        Set<String> vReg = new TreeSet<>();
        for (Vehicle v : vehicles)
        {
            vReg.add(v.getRegistrationNumber());
        }
        return vReg;
    }

//    /**
//     *
//     * @return a set of vehicle object from database
//     */
//    private Set<Vehicle> readVehicleRegFromDatabase()
//    {
//        Set<Vehicle> vehicles = new HashSet<>();
//        try
//        {
//            vehicles = IVehicleDao.getAllVehicle();
//        } catch (DaoException e)
//        {
//            System.out.println(e.getMessage());
//        }
//        return vehicles;
//    }
}
