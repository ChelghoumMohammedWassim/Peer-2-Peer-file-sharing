import DataBase.DbHelper;
import Frames.ClientChatTable;
import Frames.ClientFrame;
import Service.ServiceImp;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws RemoteException, SQLException, MalformedURLException, AlreadyBoundException, NotBoundException, InterruptedException, UnknownHostException {
        new ClientFrame();
    }
}