package Service;

import DataBase.DbHelper;
import Frames.ClientChatTable;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

public class ServiceImp extends UnicastRemoteObject implements Server {

    DbHelper dbHelper;
    ClientChatTable table;
    public ServiceImp(ClientChatTable table) throws SQLException, RemoteException {
        this.table=table;
        this.dbHelper=new DbHelper();
    }

    @Override
    public void add(String url, String file_name,String file_path,String host_name) throws RemoteException, SQLException {
        this.dbHelper.add(url,file_name,file_path,host_name);
        this.table.addCommand(host_name+" publish a new file");
        System.out.println(host_name+" publish a new file");
    }

    @Override
    public void remove(String host_name, String file_name) throws RemoteException, SQLException {
        this.dbHelper.remove(file_name,host_name);
        this.table.addCommand(host_name+" remove "+file_name);
        System.out.println(host_name+" remove "+file_name);
    }


    @Override
    public List<String> getResource(String file_name,String peer) throws RemoteException, SQLException {
        System.out.println(peer+" search for "+file_name);
        this.table.addCommand(peer+" search for "+file_name);
        return this.dbHelper.getResource(file_name,peer);
    }

    @Override
    public void updateUrl(String host_name, String url) throws RemoteException, SQLException {
        this.dbHelper.updateUrl(host_name,url);
        this.table.addCommand(host_name+" connected");
        System.out.println(host_name+" connected");
    }

    @Override
    public void upadteConnection(String host_name, String cnx) throws RemoteException, SQLException {
        this.dbHelper.upadteConnection(host_name,cnx);
    }

}
