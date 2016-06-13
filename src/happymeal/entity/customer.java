package happymeal.entity;

import java.util.List;
import static java.lang.System.out;

/**
 * created by Vittoria on 2016-06-13
 */

public class Customer {
    private String cname;
    private int phone;
    
    public int getPhone() {
        return this.phone;
    }
    
    public String getCname(){
        return this.cname;
    }
    
    public void setPhone(int phoneNum){
        this.phone = phoneNum;
    }
    
    public void setCname(String name) {
        this.cname = name;
    }
}