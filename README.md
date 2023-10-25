# CoAP Server for Thingworx Integration

This Java project is a CoAP server that facilitates the integration of CoAP-based devices with Thingworx, a popular IoT platform.

## Table of Contents

- [Introduction](#introduction)
- [Authors](#authors)
- [Features](#features)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [Usage](#usage)
- [Configuration](#configuration)
- [Contributing](#contributing)

## Authors

- Denis Botnaru - Application Developer
- Guilherme Correia - Application Developer
- Pedro Ferreira - Application Developer

## Introduction

The CoAP Server for Thingworx Integration allows you to receive CoAP messages from devices, convert them to the Thingworx format, and send them to the Thingworx platform for further processing and visualization.

## Features

- Receive CoAP messages and convert them to Thingworx-compatible format.
- Send converted messages to the Thingworx platform.
- Customizable CoAP endpoints for different device JSON types.

## Getting Started

### Prerequisites

Before you begin, ensure you have the following prerequisites installed:

- Java Development Kit 17 (JDK)
- Maven
- Spring Boot

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.gsissc.myatos.net/ES-MAD-VODAFONE-IDL/AKOCoapServer.git
   cd AKOCoapServer

2. Build the project using Maven:

    ```bash
    mvn clean install

3. Run the CoAP server:

    - Prod mode:
   ```bash
    java -jar target/demo-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
    ```
    - Dev mode:
   ```bash
    java -jar target/demo-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev

## Usage
To use the CoAP server, configure your CoAP-enabled devices to send messages to the server's endpoint, e.g., coap://yourserver:5683/sample. The server will convert and forward these messages to Thingworx for further processing.

## Configuration
Configuration for this project can be done through the application.properties file:
1. spring.profiles.active: Set the active Spring profile, which can be either prod (production) or dev (development).
2. coap.server.port: Specify the CoAP server's listening port.

Additionally, log configuration can be customized in the logback-spring.xml file.

## Contributing
Contributions to this project are welcome! If you want to contribute, please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Commit your changes and push them to your fork.
4. Create a pull request with a clear description of your changes.