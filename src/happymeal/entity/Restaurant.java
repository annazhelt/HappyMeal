package src.happymeal.entity;

/**
 * Created by anna on 2016-06-10.
 */
public class Restaurant {

    private int id;
    private String rname;
    private String address;
    private String admin_name;
    private int phone;

    public int getId() {
        return id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    String getRestaurant(){
        return rname + " " + phone + " " + address;
    }
}
