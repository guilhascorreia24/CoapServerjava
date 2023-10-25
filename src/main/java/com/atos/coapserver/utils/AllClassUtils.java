package com.atos.coapserver.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.CoAP.Code;
import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.core.coap.Response;
import org.eclipse.californium.core.coap.Token;
import org.json.JSONObject;
import org.springframework.boot.autoconfigure.jms.JmsProperties;

import java.io.IOException;
import java.util.Map;

public class AllClassUtils {
    private static final ObjectMapper cborMapper = new ObjectMapper(new CBORFactory());

    /**
     * Deserialize a CBOR payload into a map of key-value pairs.
     *
     * @param payload The CBOR payload as a byte array.
     * @return A map containing the deserialized data.
     * @throws IOException If an I/O error occurs during deserialization.
     */
    public static Map<String, Object> deserializeCborPayload(byte[] payload) throws IOException {
        return cborMapper.readValue(payload, new TypeReference<>() {
        });
    }
    /**
     * Create a wrapped JSON object from a CBOR map.
     *
     * @param decodedMap The CBOR map to wrap in a JSON object.
     * @return A JSON object containing the wrapped data.
     */
    public static JSONObject createWrappedJsonObjectFromCBORMap(Map<String, Object> decodedMap) {
        return new JSONObject(Map.of("json", decodedMap));
    }

    public static String CoapHeaderResponse(Request request){
        return null;
    }
}
