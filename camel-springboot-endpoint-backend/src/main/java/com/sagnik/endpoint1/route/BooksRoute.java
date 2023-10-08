package com.sagnik.endpoint1.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sagnik.endpoint1.model.BookEnquiry;
import com.sagnik.endpoint1.model.Books;
import com.sagnik.endpoint1.service.BooksService;

@Component
public class BooksRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {

		restConfiguration().bindingMode(RestBindingMode.auto);

		rest("/books")
		
		
//		Get All Books using GET method at http://localhost:8080/camel/books/view/all
		 .get("/view/all")
		  .to("direct:view-all-books")
		  
		  
//		 Get Books by isbn using GET method at http://localhost:8080/camel/books/view/{isbn}
		 .get("/view/{isbn}")
		  .to("direct:view-book-by-isbn")
		  
		  
//		 To add books in db using POST method at http://localhost:8080/camel/books/add
		 .post("/add")
		  .type(Books.class)
		  .consumes("application/json")
		  .to("direct:add-book")
		  
		  
//		 To get books by isbn using POST method at http://localhost:8080/camel/books/see
		 .post("/see")
		  .type(BookEnquiry.class)
		  .consumes("application/json")
		  .to("direct:see-book");

		
		
		
		from("direct:view-all-books")
		 .log("something-${body}")
		 .to("bean:booksService?method=viewBooks");

		from("direct:view-book-by-isbn")
		 .to("bean:booksService?method=viewBookByIsbn(${header.isbn})");

		from("direct:add-book")
		 .to("bean:booksService?method=createBook");
		
//		For fetching book by isbn using POST method
		from("direct:see-book")
		 .log("Entry: ${body}")
		 .log("Entry 2: ${body}")
		 .to("bean:booksService?method=seeBookByIsbn")
		 .log("Response : ${body}")
		 .choice()
		   .when(body().isNull())
		     .setBody(constant("Book Not found"))	
		   .otherwise()
	            .setHeader("Content-Type", constant("application/json"));  
	}

}
