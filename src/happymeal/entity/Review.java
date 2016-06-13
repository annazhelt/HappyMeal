package happymeal.entity;

import java.util.List;

public class Review {
    private double rating;
    private int num_rating;
    private List<String> comment;
    private int id;
    private String user_name;

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNum_rating() {
        return num_rating;
    }

    public void setNum_rating(int num_rating) {
        this.num_rating = num_rating;
    }

    public List<String> getComment() {
        return comment;
    }

    public void setComment(List<String> comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    private int restaurant_id;
}
