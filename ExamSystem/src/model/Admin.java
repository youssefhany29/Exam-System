package model;

public class Admin extends User {
    public Admin(){
        super();
    }
    
    public Admin(String id, String f_name, String l_name ,String password) {
        super(id, f_name, l_name, password);
    }
    
    @Override
    public String toString() {
        return "Admin{id='" + getId() + "', First name='" + getFname() + ", Last name = " + getLname() +"'}";
    }
}
