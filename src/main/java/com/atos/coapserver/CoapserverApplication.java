package com.atos.coapserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * The main application class for the CoAP Server.
 *
 * This class initializes and configures the Spring Boot application, disabling the web server
 * to run the CoAP server as a standalone application. It serves as the entry point for the CoAP
 * server application.
 */
@SpringBootApplication
public class CoapserverApplication {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(CoapserverApplication.class);
		app.setWebApplicationType(WebApplicationType.NONE); // Disable the web server
		app.run(args);
	}
}