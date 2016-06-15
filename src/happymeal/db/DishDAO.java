package happymeal.db;

import happymeal.connection.ConnectionUtility;
import happymeal.entity.Dish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by anna on 2016-06-14.
 */
public class DishDAO {

    private static DishDAO instance = new DishDAO();
    public static DishDAO getInstance(){
        return instance;
    }

    private Dish read(ResultSet rs) throws SQLException
    {
        int rid = rs.getInt("restaurant_id");
        String name = rs.getString("dname");
        double price = rs.getDouble("price");
        Dish dish = new Dish(rid, name, price);
        return dish;
    }

    public List<Dish> findAllWithRID(int rid)
    {
        String sql = "select * from dish where restaurant_id="+rid;
        return doSelection(sql);
    }

    public List<Dish> findAllWithAdmin(String admin){

        String sql = "select * from dish where restaurant_id in " +
                "(select id from restaurant where admin_name='"+admin+"')";
        return doSelection(sql);

    }

    public List<Dish> doSelection(String sql){
        LinkedList<Dish> dishes = new LinkedList<Dish>();
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
                Dish dish = read(rs);
                dishes.add(dish);
            }
            return dishes;
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

    public void create(Dish dish)
    {
//
        PreparedStatement statement = null;
        Connection connection = null;
        try
        {
            connection = ConnectionUtility.getConnection();
            String sql = "insert into dish " +
                    "(restaurant_id, dname, price)values (?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, dish.getRestaurantID());
            statement.setString(2, dish.getName());
            statement.setDouble(3, dish.getPrice());
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

    public void delete(Dish dish)
    {
        PreparedStatement statement = null;
        Connection connection = null;
        try
        {
            connection =  ConnectionUtility.getConnection();
            String sql = "delete from dish where dname=? and restaurant_id=?";
            statement = connection.prepareStatement(sql);
            String dname = dish.getName();
            int rid = dish.getRestaurantID();
            statement.setString(1, dname);
            statement.setInt(2, rid);
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
