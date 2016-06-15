package happymeal.entity;
import java.util.List;

/**
 * Created by anna on 2016-06-10.
 */
public class Restaurant {

    private int id;
    private String rname;
    private String address;
    private String admin_name;
    private String phone;
    private List<Dish> dishes;

    public int getId() {
        return id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
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

    public String getRestaurant(){
        return rname + " " + phone + " " + address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
