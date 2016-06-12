package happymeal.db;

import happymeal.connection.ConnectionUtility;
import happymeal.entity.Dish;
import happymeal.entity.Restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class DishDB {

    private static Map<String, String> columnNameMap;

    static {
        columnNameMap = new HashMap<>();
        columnNameMap.put("restaurant", "rname");
        columnNameMap.put("price", "price");
    }

    private PreparedStatement createPreparedStatement(Connection conn,
                                                      String columnName,
                                                      double priceThreshold)
            throws SQLException {
        String query = "SELECT dname FROM DISH WHERE PRICE > ?";

        if (columnNameMap.containsKey(columnName)) {
            query = "SELECT dname," + columnNameMap.get(columnName) + " FROM dish join restaurant on restaurant_id = id WHERE PRICE > ? ";
        }

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setDouble(1, priceThreshold);

        return preparedStatement;
    }

    public List<Dish> getDishListByPriceThreshold(String columnName, double priceThreshold) {
        List<Dish> resultList = new ArrayList<>();

        try (
                Connection conn = ConnectionUtility.getConnection();
                PreparedStatement preparedStatement = createPreparedStatement(conn,
                        columnName, priceThreshold);
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                Dish dish = new Dish();
                String dbColumnName = columnNameMap.get(columnName);
                if (dbColumnName.equals("rname")) {
                    Restaurant restaurant = new Restaurant();
                    String rname = resultSet.getString("rname");
                    restaurant.setRname(rname);
                    dish.setRestaurant(restaurant);
                } else if (dbColumnName.equals("price")){
                    double price = resultSet.getDouble("price");
                    dish.setPrice(price);
                }
                String dishName = resultSet.getString("dname");
                dish.setName(dishName);
                resultList.add(dish);
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return resultList;
    }

}
