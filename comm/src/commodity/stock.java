package commodity;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class vendor
 */

public class stock extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public stock() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post");
	    try
        {
		    String type = request.getParameter("type");
	        String item = request.getParameter("item");
	        int price = Integer.parseInt(request.getParameter("price"));
	        int stock = Integer.parseInt(request.getParameter("stock"));
	        String vendorId = request.getParameter("vendor");
	        String category = request.getParameter("category");
	        
	        ConnectMe conn = new ConnectMe();
	        if(conn.isConnected())
	        {
	        	System.out.print("connected");
	        }
	        else
	        {
	        	System.out.print("Not Connected");
	        }
	        System.out.print(type +","+ item +","+ price +","+ stock +","+ category +","+ vendorId);
	        //Connection connect = conn.connectDB;
	        //Statement stmt=connect.createStatement();
	        String sql="insert into stock (type,item,price,stock,category,vendor) values(?,?,?,?,?,?)";
	        try{
	            PreparedStatement ps=conn.connectDB.prepareStatement(sql);
	            ps.setString(1, type);
	            ps.setString(2, item);
	            ps.setInt(3, price);
	            ps.setInt(4, stock);
	            ps.setString(5, category);
	            ps.setString(6, vendorId);
	            int i = ps.executeUpdate();
	            System.out.println(i);
	            conn.close(ps);
	        }catch(Exception e){
	        System.out.println("Error in  " +e);   
	        }
	        response.sendRedirect("/comm/index.jsp");
        }
        catch (Exception e)
        {
        	System.out.print(e);
        }
	}
	

}
