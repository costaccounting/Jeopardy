package java3.classFile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QList {
	private Connection conn = null;
	private List list = new ArrayList<Question>();
	public QList(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jeopardy", "root", "celeste");
			try {
				String sql1 = "select name, value, description, ans1, ans2, ans3, ans4 from questions q inner join category c on \r\n" + 
						"(q.category = c.id)";
				Statement statement1 = conn.createStatement();
				ResultSet result1 = statement1.executeQuery(sql1);
				while (result1.next()) {
					String name = result1.getString(1); 
					int value = Integer.parseInt(result1.getString(2));
					String description = result1.getString(3);
					String ans1 = result1.getString(4);
					String ans2 = result1.getString(5);
					String ans3 = result1.getString(6);
					String ans4 = result1.getString(7);
					Question q = new Question ( name, value, description, ans1, ans2, ans3, ans4); 
					list.add(q);
					
				}
			}catch (SQLException ex1) {
					ex1.printStackTrace();
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public Question getQuestion(int id) {
		return (Question) list.get(id);
	}
	public List getAllQuestions() {
		return list;
	}
	

}
