package com.dao;

import com.beans.Book;

public interface BookDao {
	public String add(Book book);
	public Book search(String bid);
	public String update(Book book);
	public String delete(String bid);
	
}
