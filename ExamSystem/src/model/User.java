package model;

public class User {
    private String id;
    private String f_name;
    private String l_name;
    private String password;
    
    public User(){
    }
    
    public User(String id, String f_name, String l_name, String password){
        this.id = id;
        this.f_name = f_name;
        this.l_name = l_name;
        this.password = password;
    }
    
    public String getId(){
        return id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getFname(){
        return f_name;
    }
    
    public void setFname(String f_name){
        this.f_name = f_name;
    }
    
    public String getLname(){
        return l_name;
    } 
    
    public void setLname(String l_name){
        this.l_name = l_name;
    }
    
    @Override
    public String toString() {
        return "User{id='" + id + "', First name='" + f_name + ", Last name = " + l_name +"'}";
    }
    
}
