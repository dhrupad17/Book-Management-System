package com.service;

import com.beans.Book;
import com.dao.BookDao;
import com.factories.BookDaoFactory;

public class BookServiceImpl implements BookService{

	@Override
	public String addBook(Book book) {
		BookDao bookDao=BookDaoFactory.getBookDao();
		String status=bookDao.add(book);
		return status;
	}

	@Override
	public Book searchBook(String bid) {
		// TODO Auto-generated method stub
		BookDao bookDao=BookDaoFactory.getBookDao();
		Book book=bookDao.search(bid);
		return book;
	}

	@Override
	public String updateBook(Book book) {
		// TODO Auto-generated method stub
		BookDao bookDao=BookDaoFactory.getBookDao();
		String status=bookDao.update(book);
		return status;
	}

	@Override
	public String deleteBook(String bid) {
		// TODO Auto-generated method stub
		BookDao bookDao=BookDaoFactory.getBookDao();
		String status=bookDao.delete(bid);
		return status;
	}

}
