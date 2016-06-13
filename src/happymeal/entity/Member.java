package happymeal.entity;

import java.util.Date;

/**
 * created by Vittoria on 2016-06-13
 */

public class Member extends Customer {
    private String mname;
    private double points;
    private Date dateJoined;
    
    public String getMname(){
        return this.mname;
    }
    
    public double getPoints(){
        return this.points;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public Date getDateJoined(){
        return this.dateJoined;
    }
    
    public void setDateJoined (Date joinedDate){
        this.dateJoined = joinedDate;
    }
}
