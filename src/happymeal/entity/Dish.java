package happymeal.entity;

public class Dish {
    private int restaurantID;
    private String name;
    private double price;


    private Restaurant restaurant;

    public Dish(){}

    public Dish(int restaurantID, String name, double price) {
        this.restaurantID = restaurantID;
        this.name = name;
        this.price = price;
    }

    public Dish(int restaurantID, String name){
        this.restaurantID = restaurantID;
        this.name = name;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
