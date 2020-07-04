package commodity;

import java.io.IOException;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class vendor
 */

public class customer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ")
				.append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post");
		int vid = Integer.parseInt(request.getParameter("cId"));
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String contact = request.getParameter("contact");
		storedet(name, address, contact, vid);
		response.sendRedirect("/comm/index.jsp");
	}

	public String storedet(String one, String two, String three, int four) {
		ConnectMe con = new ConnectMe();
		System.out.println(con.isConnected());
		String sql = "insert into vendor (name,address,contact,id) values(?,?,?,?)";
		try {
			PreparedStatement ps = con.connectDB.prepareStatement(sql);
			ps.setString(1, one);
			ps.setString(2, two);
			ps.setString(3, three);
			ps.setInt(4, four);
			int i = ps.executeUpdate();
			System.out.println(i);
			con.close(ps);
		} catch (Exception e) {
			System.out.println("Error in  " + e);
		}
		return "Success";
	}

}
