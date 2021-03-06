package accounts;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DbConn;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int accountId = (Integer) session.getAttribute("accountId");
		if (accountId == 0){
			response.sendRedirect("/index.jsp");
		}
		else {
		 response.sendRedirect("HomePosts");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String email = (String) request.getParameter("email");
		String password = (String) request.getParameter("password");

		HttpSession session = request.getSession(true);
		boolean loginStatus = checkLogin(email, password);
		if (loginStatus) {
			
			ArrayList<Post> homePostList = HomePosts.getHomePosts();
			UserAccount userAccount = UserAccount.getUserAccountByEmail(email);
			// set session variable
			session.setAttribute("accountId", userAccount.getAccountId());
			ArrayList<BookClub> userClubList = BookClub.getBookClubsByAcct(userAccount.getAccountId());
			ArrayList<BookClub> bookClubList = BookClub.getBookClubs();
			int clubId = HomePosts.getHomeClubId();
			
			request.setAttribute("userClubList", userClubList);
			request.setAttribute("clubList", bookClubList);
			request.setAttribute("clubId", clubId);
			request.setAttribute("user", userAccount);
			request.setAttribute("homePostList", homePostList);
			
			request.getRequestDispatcher("/home.jsp").forward(request, response);
			
//			response.sendRedirect("home.jsp");
		} else {
			String error = "Sorry, could not login.";
			request.setAttribute("error", error);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}

	private boolean checkLogin(String email, String password) {
		UserAccount userAccount = UserAccount.getUserAccountByEmail(email);
		if (password.equals(userAccount.getPassword())) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean loginWithFacebook(String fbId){
		UserAccount fbUser = UserAccount.getUserAccountByFbId(fbId);
		String id = fbUser.getFbId();
		if (fbId.equals(id)){
			return true;
		}
		else {
			return false;
		}
	}

}
