package com.sagnik.endpoint1.model;

public class BookEnquiry {

	private int isbn;

//	Parameterized Constructor

	public BookEnquiry(int isbn) {
		super();
		this.isbn = isbn;
	}

//	Default Constructor

	public BookEnquiry() {
	}

//	Getters and Setters

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

//	toString:

	@Override
	public String toString() {
		return "BookEnquiry [isbn=" + isbn + "]";
	}

}
