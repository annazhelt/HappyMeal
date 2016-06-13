package happymeal.entity;

import java.util.List;

/**
* created by Vittoria on 2016-06-13
*/

publich class OrderDish {
   private int order_id;
    private String restaurant_id;
    // not sure whether it is correct
   //private List<Dish> dishes;
   private int  restaurant_id;

   public int getOrderId(){
         return this.order_id;
   }
    public String getDish_name(){
        return this.dish_name;
    }
   
    public int getRestaurant_id(){
        return this.restaurant_id;
    }
    
    public void setOrder_id( int id){
        this.restaurant_id = id;
    }

    public void setDish_name(String newName){
        this.dish_name = newName;
    }

    public void setRestaurant_id(int restID){
        this.restaurant_id = restID;
    }
    
//    public void addDish (int restaurant_id, String dname){
//        
//    }
    
    
    
//    public void cancel selectedDish (int order_id, int restaurant_id, String dname){
//        
//    }
    
//    public double checkDish_Price(int restaurant_id, String dname) {
//        
//    }
}