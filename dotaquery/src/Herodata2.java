import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Herodata2")
public class Herodata2 extends HttpServlet{
    public Herodata2() {
    	super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("hname");
		String user = request.getParameter("user");
		System.out.println(user);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Connection con = null;
		Statement stmt;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@oracle.cise.ufl.edu:1521:orcl", "shwang", "B7hhp342e");
			
			ArrayList al=null;
			ArrayList userList =new ArrayList();
			String query = "select * from HERO natural join HERODATA where HERO.NAME = '" + name + "'";
			stmt=con.createStatement(); 
			ResultSet  rs = stmt.executeQuery(query);

			while(rs.next())
			{
				al  = new ArrayList();
				al.add(rs.getInt(1));
				al.add(rs.getString(2));
				al.add(rs.getString(3));
				al.add(rs.getInt(4));
				al.add(rs.getInt(5));
				al.add(rs.getInt(6));
				al.add(rs.getInt(7));
				al.add(rs.getInt(8));
				al.add(rs.getInt(9));
				al.add(rs.getInt(10));
			
				userList.add(al);
			}

		request.setAttribute("heroList",userList);
			System.out.println(user +"user is");
			request.setAttribute("name", user);
			RequestDispatcher dispatcher1 = getServletContext().getRequestDispatcher("/herodata2.jsp");
			dispatcher1.forward(request,response);
			
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
}
