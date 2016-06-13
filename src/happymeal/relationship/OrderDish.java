package happymeal.relationship;

/**
 * Created by valeria on 12.06.16.
 */
public class OrderDish {

    //ConsistsOf relationship between Order and Dish
    private int order_id;
    private String dish_name;
    private int restaurant_id;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }
}
