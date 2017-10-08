package user.session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Tmp {
	

	HttpSession session;
	HttpServletRequest request;
	
	String user_domain_path;
	
	public String getUserDomainPath() {
		try {
			session = request.getSession();
			int session_id = 0;
			
			String driver = "com.mysql.jdbc.Driver";
	        Class.forName(driver);
	        
	        // 관리자 Login
	        String url = "jdbc:mysql://localhost:3306/moaa";
	        String id = "root";
	        String pw = "andrew12345";
	        
	        // 연결
	        Connection conn = DriverManager.getConnection(url, id, pw);
	        
			session_id = (int) session.getAttribute("key_id");
			System.out.println(session_id);
			
			Statement st = conn.createStatement();
	        // 내가 입려한 id와 pw 값이 DB안에 있는지 확인한다
	        String sql = "SELECT domain_path FROM users_domain WHERE users_id='" + session_id + "'";

	        ResultSet rs = st.executeQuery(sql);
//	        String user_domain_path="";
	        while(rs.next()) {

	        	this.user_domain_path = rs.getString("domain_path");
	        }
	        
	        System.out.println("user's domain path: " + user_domain_path);

//	        request.setAttribute("user_domain", user_domain_path);
//	        ServletContext context = getServletContext();
//	        RequestDispatcher dispatcher = context.getRequestDispatcher("/dashboard.jsp");
//	        dispatcher.forward(request, response);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return user_domain_path;
	}
	



}
