package com.atos.coapserver.server;

import com.atos.coapserver.resources.*;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.eclipse.californium.core.server.resources.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up a CoAP server.
 *
 * This class creates and configures a CoAP server instance, registering various CoAP resources,
 * and defining the server's endpoint with the specified port. The CoAP server is then started
 * and returned as a configured instance.
 *
 * @return The fully configured and initialized CoAP server instance.
 */
@Configuration
public class CoAPServerSetup {
    private final int coapServerPort;
    private static final Logger logger = LoggerFactory.getLogger(CoAPServerSetup.class);
    public CoAPServerSetup(@Value("${coap.server.port}") int coapServerPort) {
        this.coapServerPort = coapServerPort;
    }
    @Bean
    public CoapServer createServer() {
        CoapServer coapServer = new CoapServer(){
            @Override
            protected Resource createRoot() {
                return new RootResource();
            }
        };
        registerResources(coapServer);
        configureEndpoint(coapServer);
        coapServer.start();
        return coapServer;
    }
    /**
     * Helper method to register CoAP resources with the server.
     *
     * @param coapServer The CoAP server to which resources will be registered.
     */
    private void registerResources(CoapServer coapServer) {
    }
    /**
     * Helper method to configure the CoAP server's endpoint with the specified port.
     *
     * @param coapServer The CoAP server for which the endpoint will be configured.
     */
    private void configureEndpoint(CoapServer coapServer) {
        CoapEndpoint.Builder builder = new CoapEndpoint.Builder();
        builder.setPort(coapServerPort);
        coapServer.addEndpoint(builder.build());
    }
}

