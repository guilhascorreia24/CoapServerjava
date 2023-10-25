package com.atos.coapserver.resources;

import com.atos.coapserver.client.ThingworxClient;
import com.atos.coapserver.utils.AllClassUtils;
import org.apache.commons.codec.binary.Hex;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.OptionSet;
import org.eclipse.californium.core.coap.Response;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a CoAP resource for handling device-related requests.
 */
public class Device extends CoapResource {
    private static final Logger logger = LoggerFactory.getLogger(Device.class);
    public Device(String name) {
        super(name);
        //add(new Conf("conf"));
    }

    @Override
    public void handlePOST(CoapExchange exchange) {
        logger.info("handlePost wildcard getRequest: " + exchange.advanced().getRequest());
        logger.info(exchange.getRequestOptions().getUriPathString());
        try {
            System.out.println("wildcard post RequestOptions:"+exchange.getRequestOptions());
            String postType = exchange.advanced().getRequest().getOptions().getUriPath().get(0);
            if(Objects.equals(postType, "data")){
                byte[] payload = exchange.getRequestPayload();
                String cborEncodedPayload = Hex.encodeHexString(payload);
                Map<String, Object> decodedMap = AllClassUtils.deserializeCborPayload(payload);
                logger.info("Data POST Received - Encoded CBOR payload: {}, Decoded CBOR payload: {}", cborEncodedPayload, decodedMap);

                System.out.println("Data POST Received - Encoded CBOR payload: " + cborEncodedPayload + ", Decoded CBOR payload: " + decodedMap);

                JSONObject wrappedJsonObject = AllClassUtils.createWrappedJsonObjectFromCBORMap(decodedMap);
                ThingworxClient.postToThingworx(wrappedJsonObject.toString());
            }
            OptionSet requestOptions = new OptionSet();
            Response response = Response.createResponse(exchange.advanced().getRequest(), CoAP.ResponseCode.valueOfText("2.00"));
            exchange.respond(response);
        } catch (IOException | InterruptedException e) {
            logger.error("An exception occurred during Data POST handling: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
    @Override
    public void handleGET(CoapExchange exchange) {
        logger.info("handleGET wildcard getRequest: " + exchange.advanced().getRequest());
        logger.info(exchange.getRequestOptions().getUriPathString());
        System.out.println("wildcard get getRequestOptions:"+exchange.getRequestOptions().toString());
        exchange.respond(CoAP.ResponseCode.CONTENT, "",60);
    }

}
