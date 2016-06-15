package happymeal.db;

import happymeal.connection.ConnectionUtility;
import happymeal.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by anna on 2016-06-13.
 */
public class UserDAO {
    private static UserDAO instance = new UserDAO();

    public static UserDAO getInstance() {
        return instance;
    }

    private User read(ResultSet rs) throws SQLException
    {
        String username = rs.getString("uname");
        String password = rs.getString("password");
        boolean isAdminFlag = rs.getString("isAdminFlag").equals("Y");
        User user = new User();
        user.setUser_name(username);
        user.setPassword(password);
        user.setAdminFlag(isAdminFlag);
        return user;
    }


    public User findByUsername(String username)
    {
        ResultSet rs = null;
        PreparedStatement statement = null;
        Connection connection = null;
        try
        {
            connection = ConnectionUtility.getConnection();
            String sql = "select * from users where uname=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            rs = statement.executeQuery();
            if (!rs.next())
            {
                return null;
            }
            return read(rs);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {  try{
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

    public List<User> findAll()
    {
        LinkedList<User> users = new LinkedList<User>();
        ResultSet rs = null;
        PreparedStatement statement = null;
        Connection connection = null;
        try
        {
            connection = ConnectionUtility.getConnection();
            String sql = "select * from user order by uname";
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next())
            {
                User user = read(rs);
                users.add(user);
            }
            return users;
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

    public void update(User user)
    {
        PreparedStatement statement = null;
        Connection connection = null;
        try
        {
            connection = ConnectionUtility.getConnection();
            String sql = "update user set " + "password=? where id=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getPassword());
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

    public void create(User user)
    {
//        Long id = getUniqueId();
//        user.setId(id);
        PreparedStatement statement = null;
        Connection connection = null;
        try
        {
            connection = ConnectionUtility.getConnection();
            String sql = "insert into user " + "(uname, password, isAdmin) "

                    + "values (?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUser_name());
            statement.setString(2, user.getPassword());
            statement.setLong(3, 0);
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

    public void delete(User user)
    {
        PreparedStatement statement = null;
        Connection connection = null;
        try
        {
            connection =  ConnectionUtility.getConnection();
            String sql = "delete from user where uname=?";
            statement = connection.prepareStatement(sql);
            String name = user.getUser_name();
            statement.setString(1, name);
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

