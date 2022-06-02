package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Gestor
 */
@WebServlet("/Gestor")
public class Gestor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// private int pepe;

	public Gestor() {
		super();
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Cargamos las variables necesarias.
		String login = request.getParameter("login");
		String password = request.getParameter("password");

		String type = request.getParameter("type");
		String title = request.getParameter("title");
		
		// Comprobamos que la petición sea de tipo login
		if (type.equals("login")) {
			if (login.equals("pepe") && password.equals("pepe")) {
				////////////////////////////////////////////
				Class.forName("com.mysql.cj.jdbc.Driver");
				String myUrl = "jdbc:mysql://localhost:3306/biblioteca";
				Connection conn = DriverManager.getConnection(myUrl, "root", "");
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM LIBROS");
				ArrayList<Libro> libros = new ArrayList<Libro>();
				while (rs.next())
					libros.add(new Libro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6), rs.getString(7)));
				////////////////////////////////////////////
				request.setAttribute("lista", libros);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("bienvenida.jsp");
				requestDispatcher.forward(request, response);
			} else
				response.sendRedirect("index.html");
		} else if(type.equals("search")) { // Comprobamos que la petición sea de tipo search
			Class.forName("com.mysql.cj.jdbc.Driver");
			String myUrl = "jdbc:mysql://localhost:3306/biblioteca";
			Connection conn = DriverManager.getConnection(myUrl, "root", "");
			Statement st = conn.createStatement();
			System.out.print("Titulo: "+ title);
			ResultSet rs = st.executeQuery("SELECT * FROM libros WHERE titulo LIKE '%"+title+"%'");
			ArrayList<Libro> libros = new ArrayList<Libro>();
			while (rs.next())
				libros.add(new Libro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7)));
			////////////////////////////////////////////
			request.setAttribute("lista", libros);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("bienvenida.jsp");
			requestDispatcher.forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * protected void doPut(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException {
	 * response.getWriter().append("<h1>Se ha recibido un PUT</h1>"); } protected
	 * void doDelete(HttpServletRequest request, HttpServletResponse response)
	 * throws ServletException, IOException {
	 * response.getWriter().append("<h1>Se ha recibido un DELETE</h1>"); }
	 */

}
