package com.sagnik.endpoint2.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import com.sagnik.endpoint2.model.*;

@Component
public class MyRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		
		restConfiguration().bindingMode(RestBindingMode.json);
//		from("timer:test-rest-api?period=10000")
//        .routeId("rest-api-route")  // Set a route ID for better logging
//        .log("Rest API Calling")
//        .setHeader(Exchange.HTTP_METHOD, constant("GET")) // Use 'constant' instead of 'simple'
//        .to("http://localhost:8080/camel/books/view/all")
//        .unmarshal(new JacksonDataFormat(Books.class))
//        .log("HTTP Response: ${body}");
		
//		from("timer:test-rest-api?period=10000")
//		 .setHeader("Content-Type", constant("application/json"))
//		 .setBody(constant("{\"isbn\": 2}"))
//		 .to("http://localhost:8080/camel/books/see")
////		 .unmarshal(new JacksonDataFormat(Books.class))
//		 .log("Response from API : ${body}");
		
		from("timer:test-rest-api?period=10000") //Setting Timer to call the route
		 .setHeader("Content-Type", constant("application/json"))
		 .to("direct:get-book-by-isbn");
		
		
		
		from("direct:get-book-by-isbn")
		.log("Entered here")
		.to("bean:getBooksService?method=getBooksRequestToBackend")  //Getting isbn for POST request to get Books data
		.marshal().json(JsonLibrary.Jackson) // Convert the BookEnquiry object to JSON
		.log("Request Body : ${body}")
		.to("http://localhost:8080/camel/books/see")  //Sending the body with isbn for POST request
		.unmarshal(new JacksonDataFormat(Books.class))  //Unmarshalling the JSON response from Backend into Books
		.to("bean:getBooksService?method=getBooksResponse") //Sending response of Books.class and mapping to Frontend's BooksFrontend.class
		.log("Response from API : ${body}");





		
	}

}
