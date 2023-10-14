package com.service;

import com.beans.Book;

public interface BookService {
	public String addBook(Book book);
	public Book searchBook(String bid);
	public String updateBook(Book book);
	public String deleteBook(String bid);
}
