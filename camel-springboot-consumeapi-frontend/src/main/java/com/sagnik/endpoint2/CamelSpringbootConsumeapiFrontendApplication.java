package com.sagnik.endpoint2;

import org.apache.camel.CamelContext;
import org.apache.camel.component.http.HttpComponent;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class CamelSpringbootConsumeapiFrontendApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CamelSpringbootConsumeapiFrontendApplication.class, args);
	}
	
	
	// Configuring Camel context
    @Bean
    public CamelContextConfiguration contextConfiguration() {
        return new CamelContextConfiguration() {
            @Override
            public void beforeApplicationStart(CamelContext context) {
                // Adding the HTTP component to the Camel context
                context.addComponent("http", new HttpComponent());
            }

            @Override
            public void afterApplicationStart(CamelContext camelContext) {
                // Performing additional configuration if needed after Camel context starts
            }
        };
    }


}
