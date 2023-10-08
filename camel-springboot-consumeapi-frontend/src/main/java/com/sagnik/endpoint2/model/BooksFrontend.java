package com.sagnik.endpoint2.model;

import org.springframework.stereotype.Component;

@Component
public class BooksFrontend {
	
	private int ISBN;

	private String BOOKNAME;

	private String AUTHOR;

	private String GENRE;
	
	private int QTY;
	
//	Parameterised Constructor
	public BooksFrontend(int iSBN, String bOOKNAME, String aUTHOR, String gENRE, int qTY) {
		super();
		ISBN = iSBN;
		BOOKNAME = bOOKNAME;
		AUTHOR = aUTHOR;
		GENRE = gENRE;
		QTY = qTY;
	}

//	Default Constructor
	public BooksFrontend() {
		super();
	}
	
//	Getters and Setters

	public int getISBN() {
		return ISBN;
	}

	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}

	public String getBOOKNAME() {
		return BOOKNAME;
	}

	public void setBOOKNAME(String bOOKNAME) {
		BOOKNAME = bOOKNAME;
	}

	public String getAUTHOR() {
		return AUTHOR;
	}

	public void setAUTHOR(String aUTHOR) {
		AUTHOR = aUTHOR;
	}

	public String getGENRE() {
		return GENRE;
	}

	public void setGENRE(String gENRE) {
		GENRE = gENRE;
	}

	public int getQTY() {
		return QTY;
	}

	public void setQTY(int qTY) {
		QTY = qTY;
	}


//	toString
	@Override
	public String toString() {
		return "BooksFrontend [ISBN=" + ISBN + ", BOOKNAME=" + BOOKNAME + ", AUTHOR=" + AUTHOR + ", GENRE=" + GENRE
				+ ", QTY=" + QTY + "]";
	}
	
	

}
