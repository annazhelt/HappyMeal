package happymeal.db;

import happymeal.connection.ConnectionUtility;
import happymeal.entity.Tag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by anna on 2016-06-15.
 */
public class TagDAO {

    private static TagDAO instance = new TagDAO();
    public static TagDAO getInstance(){
        return instance;
    }

    private Tag read(ResultSet rs) throws SQLException
    {
        String tname = rs.getString("tname");
        Tag t = new Tag(tname);
        return t;
    }

    public List<Tag> findAll()
    {
        String sql = "select * from tag";
        return doSelection(sql);
    }


    public List<Tag> doSelection(String sql){
        LinkedList<Tag> tags = new LinkedList<Tag>();
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
                Tag t = read(rs);
                tags.add(t);
            }
            return tags;
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

    public void create(Tag tag)
    {
//
        PreparedStatement statement = null;
        Connection connection = null;
        try
        {
            connection = ConnectionUtility.getConnection();
            String sql = "insert into tag " +
                    "(tname)values (?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, tag.getTname());
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

    public void delete(Tag tag)
    {
        PreparedStatement statement = null;
        Connection connection = null;
        try
        {
            connection =  ConnectionUtility.getConnection();
            String sql = "delete from tag where tname=?";
            statement = connection.prepareStatement(sql);
            String tag_name = tag.getTname();
            statement.setString(1, tag_name);
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
