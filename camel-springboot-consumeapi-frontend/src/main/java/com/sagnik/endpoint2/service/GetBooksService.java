package com.sagnik.endpoint2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sagnik.endpoint2.model.BookEnquiry;
import com.sagnik.endpoint2.model.Books;
import com.sagnik.endpoint2.model.BooksFrontend;

@Service("getBooksService")
public class GetBooksService {
	@Autowired
	private BookEnquiry bookEnquiry;
	
	@Autowired
	private BooksFrontend booksFrontend;

//	To Send POST request to Backend for response which comtains isbn number
	public BookEnquiry getBooksRequestToBackend() {
		bookEnquiry.setIsbn(2);
		return bookEnquiry;
	}
	
//	Mapping the values from backend object class to Frontend object class
	public String getBooksResponse(Books books){
		
		booksFrontend.setISBN(books.getIsbn());
		booksFrontend.setBOOKNAME(books.getBookName());
		booksFrontend.setGENRE(books.getGenre());
		booksFrontend.setAUTHOR(books.getAuthor());
		booksFrontend.setQTY(books.getQty());
		
		System.out.println(booksFrontend.toString());
		return booksFrontend.toString();
	}
}
