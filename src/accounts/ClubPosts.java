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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class groupPosts
 */
@WebServlet("/groupPosts")
public class ClubPosts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClubPosts() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int clubId = Integer.parseInt(request.getParameter("clubId"));
		ArrayList<Post> clubPosts = getClubPosts(clubId);
		request.setAttribute("clubPostList", clubPosts);
		 request.getRequestDispatcher("/home.jsp").forward(request, response);
	}

	private final String DB_CONNECTION = "jdbc:mysql://localhost/book_club";
	
	private ArrayList<Post> getClubPosts(int clubId) {
ArrayList<Post> clubPosts = new ArrayList<Post>();
try {
	Class.forName("com.mysql.jdbc.Driver");

	// Properties prop = new Properties();
	// prop.load(getClass().getResourceAsStream("/DbAccess.properties"));
	//
	// String user = prop.getProperty("dbUser");
	// String pass = prop.getProperty("dbPassword");

	// Database credentials
	final String USER = "leesaruz_iClient";
	final String PASS = "Jd59wMUo";

	Connection conn = DriverManager.getConnection(DB_CONNECTION, USER,
			PASS);
	String sql = "SELECT * FROM post p INNER JOIN club c ON p.clubId = c.clubId WHERE c.clubId=?";
	PreparedStatement stmt = conn.prepareStatement(sql);
	stmt.setInt(1, clubId);
	ResultSet resultSet = stmt.executeQuery();

	while (resultSet.next()) {
		int postId = resultSet.getInt("postId");
		String title = resultSet.getString("title");
		String content = resultSet.getString("content");
		Date date = resultSet.getDate("date");
		int accountId = resultSet.getInt("accountId");
		int cId = resultSet.getInt("clubId");

		Post newPost = new Post();
		newPost.setPostId(postId);
		newPost.setTitle(title);
		newPost.setContent(content);
		newPost.setDate(date);
		newPost.setAccountId(accountId);
		newPost.setClubId(cId);

		clubPosts.add(newPost);
	}

} catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return clubPosts;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}