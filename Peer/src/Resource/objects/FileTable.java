package Resource.objects;

import Resource.DataBase.DbHelper;
import Service.Server;

import javax.swing.table.AbstractTableModel;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public class FileTable extends AbstractTableModel {
    Server server;
    List<SubItem> subItems;

    String[] header={"index","name","path"};

    String ip;

    DbHelper dbHelper;
    public FileTable(Server server) throws SQLException, RemoteException {
        super();
        this.server = server;
        this.ip=ip;
        this.dbHelper=new DbHelper();
        this.subItems =this.dbHelper.getResource();
    }

    @Override
    public int getRowCount() {
        return this.subItems.size();
    }

    @Override
    public int getColumnCount() {
        return this.header.length;
    }

    @Override
    public String getColumnName(int column) {
        return this.header[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0:
                return this.subItems.get(rowIndex).id;

            case 1:
                return this.subItems.get(rowIndex).getFile_name();

            case 2:
                return this.subItems.get(rowIndex).getFile_path();


            default: return null;
        }
    }

    public void remove(int row,String host_name) throws SQLException, RemoteException {
        server.remove(host_name,subItems.get(row).getFile_name());
        dbHelper.remove(subItems.get(row).id);
        this.subItems.remove(row);
        fireTableRowsDeleted(row,row);
    }

    public void add(String url, String file_name,String file_path,String host_name) throws SQLException, RemoteException {
        server.add(url, file_name, "true", host_name);
        dbHelper.add(file_name,file_path);
        this.subItems=dbHelper.getResource();
        fireTableRowsInserted(subItems.size() -1, subItems.size() -1);
    }

}
