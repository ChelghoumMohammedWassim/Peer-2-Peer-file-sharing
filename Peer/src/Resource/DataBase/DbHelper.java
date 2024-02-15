package Resource.DataBase;


import Resource.objects.SubItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbHelper {
    Connection connection;
    Statement statement;
    public DbHelper() throws SQLException {
        this.connection= DriverManager.getConnection("jdbc:sqlite:Files.db");
        this.statement=connection.createStatement();
    }

    public void add(String file_name, String file_path) throws SQLException {
        this.statement.execute("INSERT INTO Files VALUES(NULL,'"+file_name+"','"+file_path+"')");
    }

    public void remove(int id) throws SQLException {
        this.statement.executeUpdate("DELETE FROM Files WHERE id="+id);
    }

    public List<SubItem> getResource() throws SQLException {
        List<SubItem> list=new ArrayList<SubItem>();
        ResultSet resultSet=this.statement.executeQuery("SELECT * FROM Files");
        while (resultSet.next()){
            list.add(new SubItem(
                    resultSet.getInt("id"),
                    resultSet.getString("file_name"),
                    resultSet.getString("file_path")
            ));
    }
        return list;
    }

    public String getPath(String file_name) throws SQLException {
        String path="";
        ResultSet resultSet=this.statement.executeQuery("SELECT * FROM Files WHERE file_name = '"+file_name+"'");
        while (resultSet.next()){
            path=resultSet.getString("file_path");
        }
        System.out.println(path);
        return path;
    }

}
