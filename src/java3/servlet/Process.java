package java3.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import java3.classFile.Player;
import java3.classFile.QList;
import java3.classFile.Question;

/**
 * Servlet implementation class Process
 */
@WebServlet("/Process")
public class Process extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Process() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int index = Integer.parseInt(session.getAttribute("got_id").toString());
		int dailyDouble = -1;
		RequestDispatcher rd;
		try{
			dailyDouble = Integer.parseInt(request.getParameter("dailyDouble"));
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		//List<Question> questions = new QList().getAllQuestions();
		List<Question> questions = (ArrayList)session.getAttribute("questions");
		Player player = (Player) session.getAttribute("player");
		
		
		if(request.getParameter("mcq").length()>0) {
			String ans = request.getParameter("mcq");
			Question q = questions.get(index);
			if(ans.equals("skip"))
				session.setAttribute("score", "0");
			else {
				if(q.getCorrect().equals(ans)) {
					if(dailyDouble>=0)
						player.updateScore(dailyDouble);
					else
						player.updateScore(q.getValue());
					
				}else if (!q.getCorrect().equals(ans)) {
					if(dailyDouble>=0)
						player.updateScore(-1*dailyDouble);
					else
						player.updateScore(-1*q.getValue());
					
				}
			}
			
			List<Integer> answered = (ArrayList)session.getAttribute("answered");
			answered.add(Integer.parseInt(session.getAttribute("got_id").toString()));
			session.setAttribute("answered", answered);
			session.setAttribute("player", player);
			if(answered.size()<25)
				rd = request.getRequestDispatcher("gameview.jsp");
			else
				rd = request.getRequestDispatcher("result.jsp"); 
			
			session.setAttribute("got_id", "");
			session.setAttribute("correct", "answered");
			rd.forward(request, response);
				
		}
		
	
	}

}
