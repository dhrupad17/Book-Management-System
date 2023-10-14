package com.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.beans.Book;
import com.factories.BookServiceFactory;
import com.service.BookService;

/**
 * Servlet implementation class ControllerServlet
 */
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		String requestURI=request.getRequestURI();
		
		pw.println("<link rel='stylesheet' href='css/bootstrap.css'> </link>");
		
		Book book=null;
		BookService bookService=BookServiceFactory.getBookService();
		RequestDispatcher requestDispatcher=null;
		String bid="";
		String bno="";
		String bname="";
		String bauthor="";
		String status="";
		if(requestURI.endsWith("add.do")) {
			bid=request.getParameter("bid");
			bno=request.getParameter("bno");
			bname=request.getParameter("bname");
			bauthor=request.getParameter("bauthor");
			book=new Book();
			
			
			book.setBid(bid);
			book.setBno(bno);
			book.setBname(bname);
			book.setBauthor(bauthor);
			
			status=bookService.addBook(book);
			
			if(status.equals("exsisted")) {
				requestDispatcher=request.getRequestDispatcher("exsisted.html");
				requestDispatcher.forward(request, response);
			}
			if(status.equals("success")) {
				requestDispatcher=request.getRequestDispatcher("success.html");
				requestDispatcher.forward(request, response);
			}
			if(status.equals("failure")) {
				requestDispatcher=request.getRequestDispatcher("failure.html");
				requestDispatcher.forward(request, response);
			}
		}
		if(requestURI.endsWith("search.do")) {
			bid=request.getParameter("bid");
			book=bookService.searchBook(bid);
			if(book==null) {
				requestDispatcher=request.getRequestDispatcher("notexsisted.html");
				requestDispatcher.forward(request, response);
			}else {
				pw.println("<html>");
				pw.println(" <body style='background-color: rgb(255, 232, 162);'>");
				pw.println("<br><br><br>");
				pw.println("<h3 align='center'>Book Details</h3>");
				pw.println("<table class='table table-hover table-striped' style=' background-color:#c3a178;'>");

				pw.println("<tr><td>Book ID</td><td>"+book.getBid()+"</td></tr>");
				pw.println("<tr><td>Book ISBN NO</td><td>"+book.getBno()+"</td></tr>");
				pw.println("<tr><td>Book Name</td><td>"+book.getBname()+"</td></tr>");
				pw.println("<tr><td>Book Author</td><td>"+book.getBauthor()+"</td></tr>");
				pw.println("</table></body></html>");
			}
		}
		if(requestURI.endsWith("edit.do")) {
			bid=request.getParameter("bid");
			book=bookService.searchBook(bid);
			
			if(book==null) {
				requestDispatcher=request.getRequestDispatcher("notexsisted.html");
				requestDispatcher.forward(request, response);
			}else {
				pw.println("<html>");
				pw.println(" <body style='background-color: rgb(255, 232, 162);'>");
				pw.println("<br><br><br>");
				pw.println("<h3 align='center'>Book Edit form</h3>");
				pw.println("<form method='POST' action='./update.do'>");
				pw.println("<table class='table table-hover table-striped' style=' background-color:#c3a178;'>");

				pw.println("<tr><td>Book ID</td><td>"+book.getBid()+"</td></tr>");
				pw.println("<input type='hidden' name='bid' value='"+book.getBid()+"'/>");
				pw.println("&nbsp;&nbsp;<tr><td>Book ISBN NO :</td><td><input type='text' name='bno' value='"+book.getBno()+"'></td></tr>");
				pw.println("&nbsp;&nbsp;<tr><td>Book NAME :</td><td><input type='text' name='bname' value='"+book.getBname()+"'></td></tr>");
				pw.println("&nbsp;&nbsp;<tr><td>Book Author :</td><td><input type='text' name='bauthor' value='"+book.getBauthor()+"'></td></tr>");
				pw.println("</table>");
				pw.println("&nbsp;&nbsp;<tr><td><button type='submit' class='btn btn-primary' value='UPDATE'>Update</button></td></tr>");
				pw.println("</form></body></html>");
			}
		}
		if(requestURI.endsWith("update.do")) {
			bid=request.getParameter("bid");
			bno=request.getParameter("bno");
			bname=request.getParameter("bname");
			bauthor=request.getParameter("bauthor");
			
			book=new Book();
			book.setBid(bid);
			book.setBno(bno);
			book.setBname(bname);
			book.setBauthor(bauthor);
			
			status=bookService.updateBook(book);
			
			if(status.equals("success")) {
				requestDispatcher=request.getRequestDispatcher("success.html");
				requestDispatcher.forward(request,response);
			}
			if(status.equals("failure")) {
				requestDispatcher=request.getRequestDispatcher("failure.html");
				requestDispatcher.forward(request,response);
			}
		}
		
		if(requestURI.endsWith("delete.do")) {
			bid=request.getParameter("bid");
			
			status=bookService.deleteBook(bid);
			
			if(status.equals("notexsisted")) {
				requestDispatcher=request.getRequestDispatcher("notexsisted.html");
				requestDispatcher.forward(request, response);
			}
			if(status.equals("success")) {
				requestDispatcher=request.getRequestDispatcher("successDelete.html");
				requestDispatcher.forward(request, response);
			}
			if(status.equals("failure")) {
				requestDispatcher=request.getRequestDispatcher("failureDelete.html");
				requestDispatcher.forward(request, response);
			}
		}
	}
}
