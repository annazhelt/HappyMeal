package happymeal.entity;

/**
 * Created by valeria on 12.06.16.
 */


public class Order {

    private String user_name;
    private String delivery_address;
    private String delivery_time;
    private String delivery_method;
    private int id;
    private String status;

    public void setUser_name(String user_name){
        this.user_name = user_name;
    }

    public void setDelivery_address(String address){
        this.delivery_address = address;
    }

    public void setDelivery_time(String time){
        this.delivery_time = time;
    }

    public void setDelivery_method(String method){
        this.delivery_method = method;
    }

    public void setSatus(String status){
        this.status = status;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getUser_name(){
        return user_name;
    }

    public String getDelivery_address(){
        return delivery_address;
    }

    public String getDelivery_time(){
        return delivery_time;
    }

    public String getDelivery_method(){
        return  delivery_method;
    }

    public String getSatus(){
        return  status;
    }

    public int getId(){
        return id;
    }

}
