package java3.classFile;



public class Question {
	private String category = "not assigned";	  	//category of the question
	private int value = 0; 				//value of the correct answer
	private String question = "";		//the question sentence
	private String ans1 = ""; 			//answer choice 1
	private String ans2 = ""; 			//answer choice 2
	private String ans3 = ""; 			//answer choice 3
	private String correct = ""; 		//correct answer
	
	public Question(){} 				//Default constructor
	
	public Question(String category, int value, String question, String ans1, String ans2, String ans3,
			String correct) {
		this.category = category;
		this.value = value;
		this.question = question;
		this.ans1 = ans1;
		this.ans2 = ans2;
		this.ans3 = ans3;
		this.correct = correct;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category.toString();
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @return the ans1
	 */
	public String getAns1() {
		return ans1;
	}

	/**
	 * @return the ans2
	 */
	public String getAns2() {
		return ans2;
	}

	/**
	 * @return the ans3
	 */
	public String getAns3() {
		return ans3;
	}

	/**
	 * @return the correct
	 */
	public String getCorrect() {
		return correct;
	}

}
