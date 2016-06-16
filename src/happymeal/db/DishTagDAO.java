package happymeal.db;

import happymeal.connection.ConnectionUtility;
import happymeal.entity.DishTag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by anna on 2016-06-15.
 */
public class DishTagDAO {
    private static DishTagDAO instance = new DishTagDAO();
    public static DishTagDAO getInstance(){
        return instance;
    }

    private DishTag read(ResultSet rs) throws SQLException
    {
        int rid = rs.getInt("restaurant_id");
        String dname = rs.getString("dish_name");
        String tname = rs.getString("tag_name");
        DishTag dt = new DishTag(rid, dname, tname);
        return dt;
    }

    public List<DishTag> findAllWithRIDandDish(int rid, String dname)
    {
        String sql = "select * from dishtag where restaurant_id="+rid+ " and dish_name = '" + dname +"'";
        return doSelection(sql);
    }
    

    public List<DishTag> doSelection(String sql){
        LinkedList<DishTag> dts = new LinkedList<DishTag>();
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
                DishTag dt = read(rs);
                dts.add(dt);
            }
            return dts;
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

    public void create(DishTag dt)
    {
//
        PreparedStatement statement = null;
        Connection connection = null;
        try
        {
            connection = ConnectionUtility.getConnection();
            String sql = "insert into dishtag " +
                    "(restaurant_id, dish_name, tag_name)values (?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, dt.getRid());
            statement.setString(2, dt.getDname());
            statement.setString(3, dt.getTname());
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

    public void delete(DishTag dt)
    {
        PreparedStatement statement = null;
        Connection connection = null;
        try
        {
            connection =  ConnectionUtility.getConnection();
            String sql = "delete from dishtag where restaurant_id=? and dish_name=? and tag_name=?";
            statement = connection.prepareStatement(sql);
            String dish_name = dt.getDname();
            int rid = dt.getRid();
            String tag_name = dt.getTname();
            statement.setInt(1, rid);
            statement.setString(2, dish_name);
            statement.setString(3, tag_name);
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
