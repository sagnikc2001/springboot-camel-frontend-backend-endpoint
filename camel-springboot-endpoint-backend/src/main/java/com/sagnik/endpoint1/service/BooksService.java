package com.sagnik.endpoint1.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sagnik.endpoint1.model.BookEnquiry;
import com.sagnik.endpoint1.model.Books;

import oracle.jdbc.OraclePreparedStatement;

@Component
public class BooksService {

	@Autowired
	private DataSource dataSource;

//	To fetch all books in the DB in url using GET method
	public List<Books> viewBooks() {

		Connection conn = null;
		OraclePreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String queryText = "select * from Books2023";
			conn = dataSource.getConnection();
			pstmt = (OraclePreparedStatement) conn.prepareStatement(queryText);
			rs = pstmt.executeQuery();
			
//			Defining the ArrayList of Books which we will be returning
			List<Books> listBooks = new ArrayList<>();
			
			while (rs.next()) {
//				Defining new object of each book data
				Books book = new Books();
//				Setting values in the object
	            book.setIsbn(rs.getInt(1));
	            book.setBookName(rs.getString(2));
	            book.setAuthor(rs.getString(3));
	            book.setGenre(rs.getString(4));
	            book.setQty(rs.getInt(5));
//	            Adding the object into the ArrayList of Books
	            listBooks.add(book);
			}
			return listBooks;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

//	To fetch book using isbn in url using GET method
	public Books viewBookByIsbn(int isbn) {
		Connection conn = null;
		OraclePreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String queryText = "select * from books2023 where isbn = ?";
			conn = dataSource.getConnection();
			pstmt = (OraclePreparedStatement) conn.prepareStatement(queryText);
			pstmt.setInt(1, isbn);

			rs = pstmt.executeQuery();

			if (rs.next()) {
//				Defining new object of book data
				Books book = new Books();
//				Setting values in the object
	            book.setIsbn(rs.getInt(1));
	            book.setBookName(rs.getString(2));
	            book.setAuthor(rs.getString(3));
	            book.setGenre(rs.getString(4));
	            book.setQty(rs.getInt(5));
//	            Returning the object which has book data
	            return book;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;

	}
	
//	For fetching book data of particular isbn using POST method containing isbn in body
	public Books seeBookByIsbn(BookEnquiry bookEnquiry) {
		
//		Getting isbn from bookEnquiry object
		int isbn = bookEnquiry.getIsbn();
		
		Connection conn = null;
		OraclePreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String queryText = "select * from books2023 where isbn = ?";
			conn = dataSource.getConnection();
			pstmt = (OraclePreparedStatement) conn.prepareStatement(queryText);
			pstmt.setInt(1, isbn);

			rs = pstmt.executeQuery();

			if (rs.next()) {
//				Defining new object of book data
				Books book = new Books();
//				Setting values in the object
	            book.setIsbn(rs.getInt(1));
	            book.setBookName(rs.getString(2));
	            book.setAuthor(rs.getString(3));
	            book.setGenre(rs.getString(4));
	            book.setQty(rs.getInt(5));
//	            Returning the object which has book data
	            return book;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

//	To add book data into DB using POST method containing Books json
	public void createBook(Books bookData) {

		Connection conn = null;
		OraclePreparedStatement pstmt = null;

		try {

			String queryText = "insert into books2023 (isbn, bookName, author, genre, qty) VALUES (?, ?, ?, ?, ?)";
			conn = dataSource.getConnection();
			pstmt = (OraclePreparedStatement) conn.prepareStatement(queryText);
			pstmt.setInt(1, bookData.getIsbn());
			pstmt.setString(2, bookData.getBookName());
			pstmt.setString(3, bookData.getAuthor());
			pstmt.setString(4, bookData.getGenre());
			pstmt.setInt(5, bookData.getQty());

//			Getting status of insert query
			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Succesfully created book");
			} else {
				System.out.println("Failed in creating book");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
