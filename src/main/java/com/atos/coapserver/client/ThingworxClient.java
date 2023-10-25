package com.atos.coapserver.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class ThingworxClient {
    private static final Logger logger = LoggerFactory.getLogger(ThingworxClient.class);
    private static final String BASE_URL = "<url_client>";
    private static final String API_KEY = "<api_key>";
    private static final HttpClient httpClient = HttpClient.newHttpClient();

    /**
     * Sends a POST request to Thingworx with the provided JSON object.
     *
     * @param jsonObject The JSON object to send.
     * @throws IOException          If an I/O error occurs.
     * @throws InterruptedException If the operation is interrupted.
     */
    public static void postToThingworx(String jsonObject) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .header("AppKey", API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(jsonObject, StandardCharsets.UTF_8))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            logger.info("POST Sent - Data: {} With response Code: {}", jsonObject, response.statusCode());
            System.out.println("POST Sent - Data: " + jsonObject + " With response Code: " + response.statusCode());
        } catch (IOException | InterruptedException e) {
            logger.error("An exception occurred during the request: {}", e.getMessage(), e);
            System.out.println("An exception occurred during the request: " + e.getMessage());
            throw e; // Rethrow the exception to propagate it
        }
    }
}
