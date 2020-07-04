package commodity;

import java.io.IOException;
import java.util.*;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import virus.calculateSlots;


/**
 * Servlet implementation class vendor
 */

public class placeOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public placeOrder() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String customer = request.getParameter("customer");
		String item = request.getParameter("item");
		String quantity = request.getParameter("quantity");
        storedet(customer, item, quantity);
        response.sendRedirect("/comm/index.jsp");
    }

 

    public String storedet(String customer, String item, String quantity){
        ConnectMe con=new ConnectMe();
        Random rand = new Random();
        String sql="insert into orders (customer_name, item, quantity, oId, id, slot, total_price) values(?,?,?,?,?,?,?)";
        try{
            PreparedStatement ps=con.connectDB.prepareStatement(sql);
            ps.setString(1, customer);
            ps.setString(2, item);
            ps.setInt(3, Integer.parseInt(quantity));
            ps.setInt(4, rand.nextInt(99));
            ps.setInt(5, rand.nextInt(10));
            ps.setInt(6, rand.nextInt(10));
            ps.setInt(7, rand.nextInt(1000));
            int i = ps.executeUpdate();
            System.out.println(i);
            con.close(ps);
        }catch(Exception e){
        System.out.println("Error in  " +e);    
        }
        return "Success";
    }

}
