package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.beans.Book;
import com.factories.ConnectionFactory;

public class BookDaoImpl implements BookDao{

	@Override
	public String add(Book book) {
		// TODO Auto-generated method stub
		String status="";
		try {
			Connection con=ConnectionFactory.getConnection();
			Statement st=con.createStatement();
			Book b=search(book.getBid());
			if(b==null) {
				String query="insert into book values('"+book.getBid()+"','"+book.getBno()+"','"+book.getBname()+"','"+book.getBauthor()+"')";
				int rowCount=st.executeUpdate(query);
//				System.out.println(query);
				if(rowCount==1)
					status="success";
				else
					status="failure";
			}else {
				status="exsisted";
			}
		}catch(Exception e) {
			status="failure";
			e.printStackTrace();
		}
		
		return status;
	}

	@Override
	public Book search(String bid) {
		// TODO Auto-generated method stub
		Book book=null;
		try {
			Connection con=ConnectionFactory.getConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from book where bid='"+bid+"'");
			boolean b=rs.next();
			if(b==true) {
				book=new Book();
				book.setBid(rs.getString("bid"));
				book.setBno(rs.getString("bno"));
				book.setBname(rs.getString("bname"));
				book.setBauthor(rs.getString("bauthor"));
			}else {
				book=null;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	@Override
	public String update(Book book) {
		// TODO Auto-generated method stub
		String status="";
		try {
			Connection con=ConnectionFactory.getConnection();
			Statement st=con.createStatement();
			int rowCount=st.executeUpdate("update book set bno='"+book.getBno()+"', bname='"+book.getBname()+"', bauthor='"+book.getBauthor()+"' where bid='"+book.getBid()+"'");
			if(rowCount==1) {
				status="success";
			}else
			{
				status="failure";
			}
		}catch(Exception e) {
			status="failure";
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public String delete(String bid) {
		// TODO Auto-generated method stub
		String status="";
		try {
			Book b=search(bid);
			if(b==null)
				status="notexsisted";
			else
			{
				Connection con=ConnectionFactory.getConnection();
				Statement st=con.createStatement();
				int rowCount=st.executeUpdate("delete from book where bid='"+bid+"'");
				if(rowCount==1)
					status="success";
				else
					status="failure";
			}
		}catch(Exception e) {
			status="failure";
			e.printStackTrace();
		}
		return status;
	}

}
