package happymeal.db;

import happymeal.connection.ConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class DishDB {

    private static Map<String, String> columnNameMap;

    static {
        columnNameMap = new HashMap<>();
        columnNameMap.put("name", "dname");
        columnNameMap.put("price", "price");
    }

    private PreparedStatement createPreparedStatement(Connection conn,
                                                      String columnName,
                                                      double priceThreshold)
            throws SQLException {
        String query = "SELECT * FROM DISH WHERE PRICE > ?";

        if (columnNameMap.containsKey(columnName)) {
            query = "SELECT " + columnNameMap.get(columnName) + " FROM DISH WHERE PRICE > ? ";
        }

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setDouble(1, priceThreshold);

        return preparedStatement;
    }

    public List<Object> getDishListByPriceThreshold(String columnName, double priceThreshold) {
        List<Object> resultList = new ArrayList<>();

        try (
                Connection conn = ConnectionUtility.getConnection();
                PreparedStatement preparedStatement = createPreparedStatement(conn,
                        columnName, priceThreshold);
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                Object o = resultSet.getString(columnNameMap.get(columnName));
                resultList.add(o);
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return resultList;
    }

}
