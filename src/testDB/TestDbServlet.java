package testDB;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
/**
 * Class used for testing purposes of database connection
 * Servlet implementation class TestDbServlet
 */
@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// setup connection variables
		String user = "hbstudent";
		String pass = "hbstudent";
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/sports_team_statistics?useSSL=false";
		String driver = "com.mysql.jdbc.Driver"; //name of jdbc driver class for MySQL
		
		// get connection to database
		try {
			PrintWriter out = response.getWriter();
			
			out.println("Connecting to database: " + jdbcUrl);
			
			Class.forName(driver);
			
			Connection myConn =DriverManager.getConnection(jdbcUrl, user, pass);
		
			out.println(" Success!");
			
			myConn.close();
		}
		
		catch (Exception exc) {
			exc.printStackTrace();
			throw new ServletException(exc);
		}
	}
}
