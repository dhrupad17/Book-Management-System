package com.factories;

import com.service.BookService;
import com.service.BookServiceImpl;

public class BookServiceFactory {
	private static BookService bookService;
	static {
		bookService=new BookServiceImpl();
	}
	public static BookService getBookService() {
		return bookService;
		
	}
}
