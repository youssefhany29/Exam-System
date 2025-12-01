package model;


public class TrueFalseQuestion extends Question{
    private boolean answer;
    
    public TrueFalseQuestion(String text, int score, Difficulty difficulty, boolean answer) {
    	super(text, score, difficulty);
    	this.answer = answer;
    }
    
    public boolean getAnswer() {
    	return answer;
    }
    
    @Override
    public boolean checkAnswer(String userAnswer) {
    	boolean userValue = Boolean.parseBoolean(userAnswer);
    	return userValue == answer;
    }
}
