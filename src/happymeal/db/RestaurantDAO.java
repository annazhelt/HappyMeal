package happymeal.db;

import happymeal.connection.ConnectionUtility;
import happymeal.entity.Restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by anna on 2016-06-14.
 */
public class RestaurantDAO {

    private static RestaurantDAO instance = new RestaurantDAO();
    public static RestaurantDAO getInstance(){
        return instance;
    }

//    id INT PRIMARY KEY AUTO_INCREMENT,
//    rname VARCHAR(20) NOT NULL,
//    admin_name VARCHAR(30) NOT NULL,
//    phone DECIMAL(10) NULL,
//    address VARCHAR(200) NOT NULL,
    private Restaurant read(ResultSet rs) throws SQLException
    {
        int id = rs.getInt("id");
        String name = rs.getString("rname");
        String admin = rs.getString("admin_name");
        //String phone = Integer.toString(rs.getInt("phone"));
        String address = rs.getString("address");

        Restaurant restaurant = new Restaurant();
        restaurant.setRname(name);
        restaurant.setId(id);
        restaurant.setAdmin_name(admin);
        return restaurant;
    }


    public List<Restaurant> doSelelctionQuery(String sql){

        LinkedList<Restaurant> restaurants = new LinkedList<Restaurant>();
        ResultSet rs = null;
        PreparedStatement statement = null;
        Connection connection = null;
        try
        {
            connection = ConnectionUtility.getConnection();
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next())
            {
                Restaurant restaurant = read(rs);
                restaurants.add(restaurant);
            }
            return restaurants;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            try{
                if(statement!=null)
                    statement.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(connection!=null)
                    connection.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }

    public List<Restaurant> findAllWithAdmin(String admin)
    {
        String sql = "select * from restaurant where admin_name='"+admin+"'";
        return doSelelctionQuery(sql);
    }

    public Restaurant findById(int id){
        String sql = "select * from restaurant where id="+id;
        List<Restaurant> restaurants = doSelelctionQuery(sql);
        Restaurant r = null;
        for(Restaurant res: restaurants){
            r = res;
        }
        return r;
    }

    public void create(Restaurant restaurant)
    {
//
        PreparedStatement statement = null;
        Connection connection = null;
        try
        {
            connection = ConnectionUtility.getConnection();
            String sql = "insert into Restaurant (rname, admin_name, phone, address) values (?,?,?,?)";
            statement = connection.prepareStatement(sql);
            System.out.println(restaurant.getRname());
            statement.setString(1, restaurant.getRname());
            statement.setString(2, restaurant.getAdmin_name());
            statement.setString(3, restaurant.getPhone());
            statement.setString(4,restaurant.getAddress());
            statement.executeUpdate();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        } finally
        {
            try{
                if(statement!=null)
                    statement.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(connection!=null)
                    connection.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }

    public void delete(Restaurant restaurant)
    {
        PreparedStatement statement = null;
        Connection connection = null;
        try
        {
            connection =  ConnectionUtility.getConnection();
            String sql = "delete from restaurant where id=?";
            statement = connection.prepareStatement(sql);
            //String rname = restaurant.getRname();
            int id = restaurant.getId();
            //statement.setString(1, rname);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        } finally
        {
            try{
                if(statement!=null)
                    statement.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(connection!=null)
                    connection.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
}
