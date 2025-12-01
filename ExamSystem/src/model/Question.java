package model;

public abstract class Question{
	private String text;
	private int score;
	private Difficulty difficulty;
	
	public Question(String text, int score, Difficulty difficulty) {
		this.text = text;
		this.score = score;
		this.difficulty = difficulty;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public Difficulty getDifficulty() {
		return difficulty
	} 
	
	public void setDifficulty() {
		this.difficulty = difficulty;
	}
	
	public abstract boolean checkAnswer(String userAnswer);
	
}
