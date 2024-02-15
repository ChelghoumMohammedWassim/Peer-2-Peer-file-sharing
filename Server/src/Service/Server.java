package Service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface Server extends Remote {

    public void add(String url, String file_name,String file_path,String host_name) throws RemoteException, SQLException;

    public void remove(String host_name ,String file_name) throws RemoteException, SQLException;

    public List<String> getResource(String file_name,String peer) throws RemoteException, SQLException;

    public void updateUrl(String host_name,String url) throws RemoteException, SQLException;

    public void  upadteConnection(String host_name,String cnx) throws  RemoteException,SQLException;

}
