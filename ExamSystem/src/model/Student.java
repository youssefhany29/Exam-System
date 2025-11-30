package model;

public class Student extends User {
    private int attemptCount = 0;
    
    public Student() {
        super();
    }
    
    public Student(String id, String f_name, String l_name ,String password){
        super(id, f_name, l_name, password);
    }
    
    public void increseAttempts(){
        attemptCount++;
    }
    
    public int getAttemptCount(){
        return attemptCount;
    }
}
