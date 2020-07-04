package commodity;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class vendor
 */

public class vendor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public vendor() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Get");
		if(request.getParameter("vValue")!=null)
		{
			int vValue = Integer.parseInt(request.getParameter("vValue"));
			try {
				getData(vValue, request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int vid = Integer.parseInt(request.getParameter("vId"));
		String name = request.getParameter("name");
		String category = request.getParameter("category");
		String address = request.getParameter("address");
		String contact = request.getParameter("contact");
		storedet(name, address, category, contact, vid);
		response.sendRedirect("/comm/index.jsp");
	}

	public String storedet(String one, String two, String three, String four, int five) {
		ConnectMe con = new ConnectMe();
		System.out.println(con.isConnected());
		String sql = "insert into vendor (name,address,category,contact,id) values(?,?,?,?,?)";
		try {
			PreparedStatement ps = con.connectDB.prepareStatement(sql);
			ps.setString(1, one);
			ps.setString(2, two);
			ps.setString(3, three);
			ps.setString(4, four);
			ps.setInt(5, five);
			int i = ps.executeUpdate();
			System.out.println(i);
			con.close(ps);
		} catch (Exception e) {
			System.out.println("Error in  " + e);
		}
		return "Success";
	}
	
	public String getData(int value, HttpServletRequest request, HttpServletResponse response) throws SQLException {
		ConnectMe con = new ConnectMe();
		vendorBean bean = new vendorBean();
		ArrayList<vendorBean> arrayBean = new ArrayList();
		Statement st = con.connectDB.createStatement();
		String sql = "select customer_name, oId, slot, total_price from orders";
		System.out.println(sql);
		ResultSet rst1 = st.executeQuery(sql);
		try {
			while (rst1.next()) 
			{
				bean.setCustomer_name(rst1.getString(1));
				bean.setoId(rst1.getInt(2));
				bean.setSlot(rst1.getInt(3));
				bean.setTotal_price(rst1.getInt(4));
				arrayBean.add(bean);
			}
			request.getSession().setAttribute("arrayList", arrayBean);
			 RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			 rd.forward(request, response);
			
		} catch (Exception e) {
			System.out.println("Error in  " + e);
		}
		return "Success";
	}

}
