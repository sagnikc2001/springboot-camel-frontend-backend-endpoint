package com.sagnik.endpoint2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Books {

	@JsonProperty("isbn")
	private int isbn;

	@JsonProperty("bookName")
	private String bookName;

	@JsonProperty("author")
	private String author;

	@JsonProperty("genre")
	private String genre;
	
	@JsonProperty("qty")
	private int qty;

//	Default Constructor
	public Books() {
//		Default Constructor
	}

//	Parameterized Constructor
	public Books(int isbn, String bookName, String author, String genre, int qty) {
		super();
		this.isbn = isbn;
		this.bookName = bookName;
		this.author = author;
		this.genre = genre;
		this.qty = qty;
	}

//	Getters and Setters

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

//	toString:

	@Override
	public String toString() {
		return "Books [isbn=" + isbn + ", bookName=" + bookName + ", author=" + author + ", genre=" + genre + ", qty="
				+ qty + "]";
	}

}
