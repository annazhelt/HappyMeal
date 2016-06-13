package happymeal.entity;

import java.util.Date;

/**
 * created by Vittoria on 2016-06-13
 */

public class Member extends Customers {
    private String mname;
    private double points;
    private Date dateJoined;
    
    public String getMname(){
        return this.mname;
    }
    
    public double getPoints(){
        return this.points;
    }
    
    // admin add points or reduce points??
    
    public Date getDateJoined(){
        return this.dateJoined;
    }
    
    public void setDateJoined (Date joinedDate){
        this.dateJoined = joinedDate;
    }
}
