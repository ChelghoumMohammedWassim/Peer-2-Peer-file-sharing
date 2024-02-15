
import Resource.Frames.HomeFame;
import Service.ResourceImpl;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException, SQLException, UnknownHostException {

        Registry registry= LocateRegistry.createRegistry(6060);
        System.out.println(registry);
        registry.rebind("Resource",new ResourceImpl());

        InetAddress inetAddress=InetAddress.getLocalHost();
        String myIp= inetAddress.getHostAddress();
        //new HomeFame(myIp,inetAddress.getHostName()).setVisible(true);
        new ConnectionFrame();
    }
}