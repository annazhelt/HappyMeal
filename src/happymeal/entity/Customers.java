package happymeal.entity;

public class Customers {
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