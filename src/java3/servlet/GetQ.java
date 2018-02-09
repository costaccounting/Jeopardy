package java3.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java3.classFile.QList;
import java3.classFile.Question;

/**
 * Servlet implementation class GetQ
 */
@WebServlet("/GetQ")
public class GetQ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetQ() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String value = request.getParameter("question");
		HttpSession session = request.getSession();
		List<Question> questions = (ArrayList)session.getAttribute("questions");
		
		if(value.length()>0) {
			//value of request parameter question is passed to a session attribute get_id
			session.setAttribute("got_id", value);
		}
		request.getRequestDispatcher("gameview.jsp").forward(request, response);
	}

}
