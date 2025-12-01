package model;

public class MultipleChoiceQuestion extends Question{
	private List<String> options;
	private String correctAnswer;
	
	public MultipleChoiceQuestion(String text, int score, Difficulty difficulty,
								  List<String> options, String correctAnswer) {
		super(text, score, difficulty);
		this.correctAnswer = correctAnswer;
		this.options = options;
	}
	
	public List<String> getOptions() {
		return options;
	}
	
	@Override
	public boolean checkAnswer(String userAnswer) {
		return userAnswer.equalsIgnoreCase(correctAnswer);
	}
	
}
