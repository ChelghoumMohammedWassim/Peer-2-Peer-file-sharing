package Client.Objects;

import Service.Server;

import javax.swing.table.AbstractTableModel;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchFileTable extends AbstractTableModel {
    Server server;
    List<Item> items;

    String[] header={"index","File name","Host name"};

    String ip;
    String host_name;

    public SearchFileTable(Server server,String host_name) {
        this.host_name=host_name;
        this.server=server;
        this.items =new ArrayList<Item>();
    }

    @Override
    public int getRowCount() {
        return this.items.size();
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
                return this.items.get(rowIndex).id;

            case 1:
                return this.items.get(rowIndex).getFile_name();

            case 2:
                return this.items.get(rowIndex).getHost_name();

            default: return null;
        }
    }

    public void search(String file_name) throws SQLException, RemoteException {
        this.items =new ArrayList<Item>();
        List<String> list= server.getResource(file_name,host_name);
        for (int i=0;i< list.size();i++){
            List<String> resource= List.of(list.get(i).split(";"));
            if (!resource.get(3).equals(this.host_name))
                this.items.add(new Item(Integer.parseInt(resource.get(0)),resource.get(1),resource.get(2),resource.get(3)));
        }
        fireTableDataChanged();
    }

    public Item getResource(int rowIndex){
        return this.items.get(rowIndex);
    }
}
