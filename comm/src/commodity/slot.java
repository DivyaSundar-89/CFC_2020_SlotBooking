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

public class slot extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public slot() {
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
		String vId = request.getParameter("vId");
        int iSlotPeriod = Integer.parseInt(request.getParameter("iSlotPeriod"));
        System.out.println("Vender Id: " + vId);
        System.out.println("Slot Period: " + iSlotPeriod);
        
        String workingDay[] = request.getParameterValues("workingDay");
        String workingDays = "";
        if (workingDay != null) {
            System.out.println("Working Days are: ");
            for (String lang : workingDay) {
            	if(workingDays.length() > 0)
            	{
            		workingDays = workingDays + "," + lang;
            	}
            	else
            	{
            		workingDays = lang;
            	}
            }
            System.out.println("\t" + workingDays);
        }
        
        String openingSlot = request.getParameter("oSlot") + request.getParameter("oTime");
        System.out.println("Opening Slot: " + openingSlot);
        
        String closingSlot = request.getParameter("cSlot") + request.getParameter("cTime");
        System.out.println("Closing Slot: " + closingSlot);
        
        String exclusion = request.getParameter("sExcFrom");
        exclusion += request.getParameter("exFrTime");
        exclusion += "to";
        exclusion += request.getParameter("sExcTo");
        exclusion += request.getParameter("exToTime");
        storedet(iSlotPeriod, workingDays, openingSlot, closingSlot, exclusion, vId);
        response.sendRedirect("/comm/index.jsp");
    }

 

    public String storedet(int slotPeriod, String wdays, String openSlot, String closeSlot, String exclu, String vendor){
        ConnectMe con=new ConnectMe();
        System.out.println(con.isConnected());
        String sql="insert into slot (slot_time_period, workingdays, opening_time, closing_time, exclusion, vendor) values(?,?,?,?,?,?)";
        try{
            PreparedStatement ps=con.connectDB.prepareStatement(sql);
            ps.setInt(1, slotPeriod);
            ps.setString(2, wdays);
            ps.setString(3, openSlot);
            ps.setString(4, closeSlot);
            ps.setString(5, exclu);
            ps.setString(6, vendor);
            int i = ps.executeUpdate();
            System.out.println(i);
            con.close(ps);
        }catch(Exception e){
        System.out.println("Error in  " +e);    
        }
        return "Success";
    }

}
