package com.atos.coapserver.client;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.elements.exception.ConnectorException;

import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;

import java.util.Random;

public class CoAPClientSimulator {
    public static void main(String[] args) throws ConnectorException, IOException {
        // Create a CoAP client instance targeting your server's resource
        CoapClient client = new CoapClient("coap://localhost:5683");
        CoapResponse postResponse = client.get();
        if (postResponse != null) {
            byte[] token = postResponse.advanced().getToken().getBytes();
            int tokenLength = token.length;
            System.out.println("POST Response: " + postResponse.getCode());
            System.out.println("POST Response: " + postResponse.getOptions());
            System.out.println("POST Response: " + postResponse.advanced().getToken());
            System.out.println("POST Response: " + postResponse.advanced().getType());
            System.out.println("POST Response: " + postResponse.advanced().getMID());
            System.out.println("POST Response: " + tokenLength);
            System.out.println("Post payload response "+ postResponse.advanced().getPayloadString());
        }


        ///pOST TEST

        client = new CoapClient("coap://localhost:5683/data");

        CBORFactory cborFactory = new CBORFactory();
        ObjectMapper cborMapper = new ObjectMapper(cborFactory);
        Map<String, Object> json = createHashMap();

        //String type = json.get("ty").toString();
        // Serialize object to CBOR

        byte[] cborBytes = cborMapper.writeValueAsBytes(json);
        postResponse = client.post(cborBytes,60);
        if (postResponse != null) {
            byte[] token = postResponse.advanced().getToken().getBytes();
            int tokenLength = token.length;
            System.out.println("POST Response: " + postResponse.getCode());
            System.out.println("POST Response: " + postResponse.getOptions());
            System.out.println("POST Response: " + postResponse.advanced().getToken());
            System.out.println("POST Response: " + postResponse.advanced().getType());
            System.out.println("POST Response: " + postResponse.advanced().getMID());
            System.out.println("POST Response: " + tokenLength);
            System.out.println("Post payload response "+ postResponse.advanced().getPayloadString());
        }

        // Release resources
        client.shutdown();
    }

    // Generate a random placeholder value
    private static String generateRandomValue() {
        Random random = new Random();
        return String.valueOf(random.nextInt(100)); // You can adjust the range as needed
    }

    private static Map<String, Object> createHashMap() {
        List<String> idList = new ArrayList<>();
        idList.add(generateRandomValue());
        idList.add(generateRandomValue());
        idList.add(generateRandomValue());

        List<List<String>> dnList = new ArrayList<>();

        // First inner list
        List<String> innerList1 = new ArrayList<>();
        innerList1.add(generateRandomValue());
        innerList1.add(generateRandomValue());
        innerList1.add(generateRandomValue());
        innerList1.add(generateRandomValue());
        innerList1.add(generateRandomValue());
        dnList.add(innerList1);

        // Second inner list
        List<String> innerList2 = new ArrayList<>();
        innerList2.add(generateRandomValue());
        innerList2.add(generateRandomValue());
        innerList2.add(generateRandomValue());
        dnList.add(innerList2);

        // Third inner list
        List<String> innerList3 = new ArrayList<>();
        innerList3.add(generateRandomValue());
        innerList3.add(generateRandomValue());
        dnList.add(innerList3);

        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", idList);
        hashMap.put("ty", "sample");
        hashMap.put("ts", generateRandomValue());
        hashMap.put("dn", dnList);
        return hashMap;
    }
}

