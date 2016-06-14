package happymeal.db;


import happymeal.connection.ConnectionUtility;
import happymeal.entity.OrderDetailsObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UpdateOrderDB {

    private static PreparedStatement createOrderDetailsPreparedStatement(Connection conn, int id)
            throws SQLException {

        String query = "select user_name, delivery_address, delivery_time, delivery_method, current_status, rname, dish_name " +
                "from placeorder p, orderdish o, restaurant r " +
                "where p.id = order_id and p.id = ? and o.restaurant_id=r.id;";

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    private static PreparedStatement createOrderUpdatePreparedStatement(Connection conn, int oid, String deliveryMethod,
                                                                        String deliveryTime, String deliveryAddress,
                                                                        String dname, int rid)
            throws SQLException {

        String query = "select * from placeorder, orderdish where id = order_id and id = ?";

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, oid);
        return preparedStatement;
    }

    public static List<OrderDetailsObject> getOrderDetails(int order_id) {
        List<OrderDetailsObject> objectList = new ArrayList<>();
        try (
                Connection conn = ConnectionUtility.getConnection();
                PreparedStatement preparedStatement = createOrderDetailsPreparedStatement(conn, order_id);
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                OrderDetailsObject object = new OrderDetailsObject();

                String user_name = resultSet.getString("user_name");
                object.setUser_name(user_name);

                String delivery_address = resultSet.getString("delivery_address");
                object.setDelivery_address(delivery_address);

                String delivery_method = resultSet.getString("delivery_method");
                object.setDelivery_method(delivery_method);

                String delivery_time  = resultSet.getString("delivery_time");
                object.setDelivery_time(delivery_time);

                String status = resultSet.getString("current_status");
                object.setStatus(status);

                String rname = resultSet.getString("rname");
                object.setRestaurantName(rname);

                String dname = resultSet.getString("dish_name");
                object.setDname(dname);

                objectList.add(object);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return objectList;
    }
}
