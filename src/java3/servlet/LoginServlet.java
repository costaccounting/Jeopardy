package java3.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java3.classFile.Player;
import java3.classFile.QList;
import java3.classFile.Question;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	// doGet method is invoked to initiate the game when a person is starting the game without logging in
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String name = request.getParameter("name");
		HttpSession session = request.getSession();
		initiate(session, name);
		request.getRequestDispatcher("gameview.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * post method is used when user has entered a user name and password to log in to the game
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uid = request.getParameter("uid");
		String pass = request.getParameter("pass");
		Connection conn = null;
		//searches the password for given uid from member database and checks whether
		// the entered password value is equal to the the password in database
		// to validate login
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeopardy", "root", "celeste");
			try {
				String sql1 = "SELECT password FROM sheridan.users WHERE username=?";
				String sql2 = "select name from player where username=? ";
				PreparedStatement statement1 = conn.prepareStatement(sql1);
				// to prevent sql injection
				statement1.setString(1, uid);
				PreparedStatement statement2 = conn.prepareStatement(sql2);
				statement2.setString(1, uid);

				ResultSet result1 = statement1.executeQuery();

				RequestDispatcher dispatcher = request.getRequestDispatcher("invalid.html");

				while (result1.next()) {
					if (result1.getString(1).equals(pass)) {
						ResultSet result2 = statement2.executeQuery();

						HttpSession session = request.getSession();
						while (result2.next()) {
							String name = result2.getString(1);
							//initiating session variables
							initiate(session, name);
						}
						//dispatch to game
						dispatcher = request.getRequestDispatcher("gameview.jsp");
					}

				}
				conn.close();
				dispatcher.forward(request, response);

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	/**
	 * 
	 * @param session the Session variable
	 * @param name the name of the player
	 * 
	 * initiates the session variables.
	 */
	protected void initiate(HttpSession session, String name) {
		session.setAttribute("player", new Player(name));
		session.setAttribute("got_id", "");
		session.setAttribute("correct", "");
		//session.setAttribute("uid", uid);
		List<Integer> answered = new ArrayList<Integer>();
		session.setAttribute("answered", answered);
		List<Question> questions = new QList().getAllQuestions();
		session.setAttribute("questions", questions);
		Random randomGenerator = new Random();
		int random1 = randomGenerator.nextInt(25);
		int random2;
		do {
			random2 = randomGenerator.nextInt(25);
		} while (random1 == random2);
		session.setAttribute("random1", random1);
		session.setAttribute("random2", random2);
	

	}

}
