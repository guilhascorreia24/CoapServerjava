package com.atos.coapserver.resources;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.eclipse.californium.core.server.resources.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RootResource extends CoapResource {
    private static final Logger logger = LoggerFactory.getLogger(Device.class);
    private CoapResource wildcard = new Device( "*");
    public RootResource() {
        super("");
    }


    @Override
    public void handleGET(CoapExchange exchange) {
        logger.info("handleGET ROOT getRequest : " + exchange.advanced().getRequest());
        exchange.respond(CoAP.ResponseCode.CONTENT, "",60);
    }

    @Override
    public Resource getChild(String name){
        return wildcard;
    }

}
