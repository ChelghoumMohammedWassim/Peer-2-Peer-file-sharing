package DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbHelper {
    Connection connection;
    Statement statement;
    public DbHelper() throws SQLException {
        this.connection= DriverManager.getConnection("jdbc:sqlite:Peer2Peer.db");
        this.statement=connection.createStatement();
    }

    public void add(String url, String file_name, String isConnected ,String host_name) throws SQLException {
        this.statement.execute("INSERT INTO Peer VALUES(NULL,'"+ url+ "','"+file_name+"','"+host_name+"','"+isConnected+"')");
    }

    public void remove(String file_name,String host_name) throws SQLException {
        this.statement.executeUpdate("DELETE FROM Peer WHERE file_name = '"+file_name+"' AND host_name = '"+host_name+"'");
    }

    public List<String> getResource(String file_name ,String peer) throws SQLException {
        List<String> list=new ArrayList<String>();
        String host_name="true";
            ResultSet resultSet=this.statement.executeQuery("SELECT * FROM Peer WHERE file_name LIKE '%"+file_name+"%' AND host_name <>'"+host_name+"' AND is_connceted = 'true'");
        while (resultSet.next()){
            list.add((resultSet.getInt("id")+";"+resultSet.getString("url")+";"+resultSet.getString("file_name")
                +";"+resultSet.getString("host_name")).toString());
    }
        return list;
    }

    public void updateUrl(String host_name,String url) throws SQLException {
        this.statement.executeUpdate("UPDATE Peer SET url='"+url+"'"+"WHERE host_name='"+host_name+"'");
    }

    public void  upadteConnection(String host_name,String cnx) throws SQLException {
        this.statement.executeUpdate("UPDATE Peer SET is_connceted='"+cnx+"'"+"WHERE host_name='"+host_name+"'");
    }

}
