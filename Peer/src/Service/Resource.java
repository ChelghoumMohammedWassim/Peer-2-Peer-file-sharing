package Service;

import java.io.IOException;
import java.rmi.Remote;
import java.sql.SQLException;

public interface Resource extends Remote {

    public byte[] getFile(String file_name) throws IOException, SQLException;

}
