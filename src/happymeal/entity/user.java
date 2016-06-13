package happymeal.entity;

import java.util.List;
import static java.lang.System.out;

/**
 * created by Vittoria on 2016-06-13
 */
int MAX_CAPACITY_NAME = 30;
int MAX_CAPACITY_PASSWORD = 15;
public class User{
    private String username;
    private int password;
    private isAdminFlag;
    
    public String getUserName (){
        return this.username;
    }
    
    public void setUserName(String newName){
        if length(newName) > MAX_CAPACITY_NAME
            System.out.printf("UserName is too long!");
        else this.username = newName;
    }
    
    public int getPassword(){
        return this.password;
    }
    
    public void setPassword(String password){
        if length(password)) > MAX_CAPACITY_PASSWORD
            System.out.printf("password is too long!");
        else this.password = password;
    }
    
    public int isAdmin (String username){
        return (username.isAdminFlag == 'Y');
    }
}
