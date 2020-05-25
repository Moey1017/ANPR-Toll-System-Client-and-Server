/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server_Client;

import java.util.Set;
import java.util.TreeSet;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;

/**
 *
 * @author HP
 */
public class Testing
{
    public static void main(String[] args)
    {
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
                        JsonArrayBuilder arrayBuilder = factory.createArrayBuilder();
                        Set<String> carRegs = new TreeSet<>();
                        carRegs.add("s");
                        carRegs.add("1");
                        carRegs.add("2");
                        carRegs.add("3");
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
                        System.out.println(response);
    }
}
