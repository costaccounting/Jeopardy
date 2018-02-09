<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java3.classFile.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Collections"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/game.css">
<title>Jeopardy</title>

</head>
<body>
	<%
		//loading session variables
		Player player = (Player) session.getAttribute("player");
		List<Question> questions = (ArrayList) session.getAttribute("questions");
		String got_id = session.getAttribute("got_id").toString();
		List<Integer> answered = (ArrayList) session.getAttribute("answered");
		String correct = session.getAttribute("correct").toString() == null ? ""
				: session.getAttribute("correct").toString();

		//resetting session value correct
		if (session.getAttribute("correct").toString().length() > 0) {
			session.setAttribute("correct", "");
			session.setAttribute("score", "");
			session.setAttribute("correct", "");
		}
		String qid = "";
	%>
	<div id="playerInformation">
		<h1>
			Welcome
			<%=player.getName().toUpperCase()%></h1>
		<%
			int score = player.getScore();
			//changing color of display of player score depending on positive or negative value
			if (score < 0) {
				out.println("<h2 style=\"color:red\">Your current score : " + score + "</h2>");
			} else {
				out.println("<h2>Your current score : " + score + "</h2>");
			}
		%>

	</div>
	<%
		//loading multiple answers of selected questions by session varible got_id
		if (got_id.length() > 0 && got_id != null) {
			Question q = questions.get(Integer.parseInt(got_id));

			out.println("<div class=\"modal\">");
			out.println("<div class=\"modal-content\">");
			out.println("<span class=\"close\">&times;</span>");
			out.println("<h2>" + q.getQuestion() + "</h2>");
			//form sends request to Process servler to calculate score based on answers 
			out.println("<form action=\"Process\" method=\"post\">");
			// sort variable holds the possible answers to a question
			List<Integer> sort = new ArrayList<>();
			for (int i = 0; i < 4; i++) {
				sort.add(i);
			}

			//suffling the order in which answers are presented in the radio
			Collections.shuffle(sort);
			//generating the radio for answers
			for (int i = 0; i < 4; i++) {
				if (sort.get(i) == 0)
					out.println("<input type=\"radio\" name=\"mcq\" value=\"" + q.getAns1() + "\">" + q.getAns1()
							+ "<br>");
				else if (sort.get(i) == 1)
					out.println("<input type=\"radio\" name=\"mcq\" value=\"" + q.getAns2() + "\">" + q.getAns2()
							+ "<br>");
				else if (sort.get(i) == 2)
					out.println("<input type=\"radio\" name=\"mcq\" value=\"" + q.getAns3() + "\">" + q.getAns3()
							+ "<br>");
				else if (sort.get(i) == 3)
					out.println("<input type=\"radio\" name=\"mcq\" value=\"" + q.getCorrect() + "\">"
							+ q.getCorrect() + "<br>");

			}
			// option to skip the question
			out.println("<input type=\"radio\" name=\"mcq\" value=\"skip\">Skip Question<br>");

			// presenting the option to chose a value for daily double questions 
			// if got_id is equal to random value then daily double is presented
			// the value entered in daily double is attached with the request parameter
			// of the question is not a daily double then the value of -1 is passed and
			// no daily double calculation is done
			if (got_id.equals(session.getAttribute("random1").toString())
					|| got_id.equals(session.getAttribute("random2").toString())) {
				int max = player.getScore() >= 1000 ? player.getScore() : 1000;
				out.println("<input type=\"number\" name=\"dailyDouble\" placeholder=\"Enter daily double value\""
						+ " min=\"0\" max=\"" + max + "\"><br>");

			} else {
				out.println("<input type=\"hidden\" name=\"dailyDouble\" value=\"-1\"><br>");
			}

			out.println("<input type=\"submit\" value=\"Answer\">");
			out.println("</form>");
			out.println("</div>");
			out.println("</div>");

		}
	%>

	<div id="gameBoard">
		<div name="game">
			<table id="gameTable">
				<tr>
					<th><h3>Literature</h3></th>
					<th><h3>Music</h3></th>
					<th><h3>Television</h3></th>
					<th><h3>Science</h3></th>
					<th><h3>Sheridan College</h3></th>
				</tr>

				<%
					for (int i = 0; i < 5; i++) {
				%>
				<tr>
					<%
						for (int j = 0; j < 5; j++) {
					%>
					<td>
						<%
							qid = i + j * 5 + "";
									// the form submits the value of qid ( the serial no. given to questions)
									// to a servlet called GetQ. Which returns the same value to gaveview.jsp
									// When a player clicks on a question, the clicking in done on the client side
									// this way the selected value of question is passed to server side
									out.print("<form action=\"GetQ\" method=\"post\">");
									//question is the name of request parameter being passed to GetQ
									out.print("<input type=\"hidden\" name=\"question\" value=\"" + qid + "\">");
									if (!answered.contains(i + j * 5)) {
										out.print("<input type=\"submit\" value=\"" + "$" + (i + 1) * 100 + "\">");
									} else {
										out.print("<input type=\"hidden\" value=\"" + "$" + (i + 1) * 100 + "\">");
									}
									out.print("</form>");
						%>
					</td>
					<%
						}
					%>
				</tr>
				<%
					}
				%>
			</table>

		</div>
	</div>
</body>
</html>