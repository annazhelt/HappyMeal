package happymeal.db;


import happymeal.connection.ConnectionUtility;
import happymeal.entity.Dish;
import happymeal.entity.NestedAggregationObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NestedAggregationDB {

    private static PreparedStatement createNestedAggregationPreparedStatement(Connection conn, String aggregation, String city)
            throws SQLException {

        String query = "select "+ aggregation+ "(price) as price, rname, dname " +
                "from dish d join restaurant r on restaurant_id = id " +
                "where restaurant_id in (select id from restaurant where address like ?) " +
                "group by restaurant_id having avg(price) < (select avg(price) from dish);";

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, "%" + city + "%");
        System.out.print(query);
        return preparedStatement;
    }

    public static List<NestedAggregationObject> getDishList(String aggregation, String city) {
        List<NestedAggregationObject> resultList = new ArrayList<>();
        try (
                Connection conn = ConnectionUtility.getConnection();
                PreparedStatement preparedStatement = createNestedAggregationPreparedStatement(conn, aggregation, city);
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                NestedAggregationObject object = new NestedAggregationObject();

                String dishName = resultSet.getString("dname");
                object.setDishName(dishName);

                double price = resultSet.getDouble("price");
                object.setPrice(price);

                String restaurantName = resultSet.getString("rname");
                object.setRestaurantName(restaurantName);

                resultList.add(object);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resultList;
    }
}
