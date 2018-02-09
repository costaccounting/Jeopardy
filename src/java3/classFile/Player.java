package java3.classFile;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.List;

public class Player {
	String name = "";
	int score = 0;
	List questionsAnswered = new ArrayList<Question>();
	
	public Player() {}
	public Player(String name) {
		this.name = name;
	}
	
	public void answer(Question question, String answer) {
		questionsAnswered.add(question);
		if(question.getCorrect().equals(answer)) {
			score += question.getValue();
		}else {
			score -= question.getValue();
		}		
	}
	public void updateScore(int value) {
		score += value;
	}
	public void skip (Question question) {
		questionsAnswered.add(question);		
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
		/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	/**
	 * @return the questionsAnswered
	 */
	public List getQuestionsAnswered() {
		return questionsAnswered;
	}
	

}
