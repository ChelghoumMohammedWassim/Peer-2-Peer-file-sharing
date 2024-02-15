package Service;

import Resource.DataBase.DbHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class ResourceImpl extends UnicastRemoteObject implements Resource  {

    public ResourceImpl() throws RemoteException {
        super();
    }

    @Override
    public byte[] getFile(String file_name) throws IOException, SQLException {
        File file = new File(new DbHelper().getPath(file_name));
        byte[] fileData = new byte[(int) file.length()];
        FileInputStream fileInputStream = new FileInputStream(file);
        fileInputStream.read(fileData);
        fileInputStream.close();
        return fileData;
    }
}
