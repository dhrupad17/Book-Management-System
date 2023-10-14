package com.factories;

import com.dao.BookDao;
import com.dao.BookDaoImpl;

public class BookDaoFactory {
	private static BookDao bookDao;
	static {
		bookDao=new BookDaoImpl();
	}
	public static BookDao getBookDao() {
		return bookDao;
		
	}
}
