package com.mapfreusa.devops;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.util.SocketUtils;
import org.springframework.util.StringUtils;

@SpringBootApplication
@EnableConfigurationProperties
public class DevOpsApplication {

	private static int minPort = 87000;
	
	private static int maxPort = 88000;
	
	public static void main(String[] args) {
		setRandomPort();
		SpringApplication.run(DevOpsApplication.class);
	}
	
	 private static Logger logger = LoggerFactory.getLogger(DevOpsApplication.class);
	    
	    public static void setRandomPort() {
			   try {
			        String userDefinedPort = System.getProperty("server.port", System.getenv("SERVER_PORT"));
			        if (StringUtils.isEmpty(userDefinedPort)) {
			           int port = SocketUtils.findAvailableTcpPort(minPort, maxPort);
			           System.setProperty("server.port", String.valueOf(port));
			           logger.info("Server port set to {}.", port);
			         }
			    } catch (IllegalStateException var4) {
			           logger.warn("No port available in range 87000-88000. Default embedded server configuration will be used.");
			    }
		}
}
