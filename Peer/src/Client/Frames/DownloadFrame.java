package Client.Frames;


import Client.Objects.Item;
import Client.Objects.SearchFileTable;
import Resource.Frames.HomeFame;
import Service.Resource;
import Service.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class DownloadFrame extends JDialog implements ActionListener {

    JTextField searchInput;

    JButton connectButton;

    SearchFileTable fileTable;


    Server server;

    JTable table;

    JLabel message;
    JDialog frame;

    public DownloadFrame(HomeFame homeFame,JButton button,String host_name,Server serv) throws HeadlessException, MalformedURLException, NotBoundException, RemoteException, SQLException {
        super(homeFame);

        this.server=serv;

        this.setTitle("Search file");
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(600,500));

        Action enterButton = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                message.setText("");
                if(!(searchInput.getText().trim().isEmpty())){
                    try {
                        fileTable.search(searchInput.getText().trim());
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        };

        searchInput=new JTextField();
        searchInput.setPreferredSize(new Dimension(250,30));
        searchInput.setMaximumSize(new Dimension(250,30));
        searchInput.addActionListener(enterButton);

        connectButton=new JButton();
        connectButton.setText("Download");
        connectButton.addActionListener(this);
        connectButton.setPreferredSize(new Dimension(125, 30));

        JPanel toppanel=new JPanel();
        toppanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,5));
        toppanel.add(searchInput);
        toppanel.add(connectButton);

        fileTable=new SearchFileTable(this.server,host_name);
        table=new JTable(fileTable);

        message=new JLabel();
        message.setText("");
        message.setAlignmentX(JLabel.CENTER);



        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.add(toppanel,BorderLayout.NORTH);
        this.add(new JScrollPane(table),BorderLayout.CENTER);
        this.add(message,BorderLayout.SOUTH);

        frame=this;

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                button.setEnabled(true);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                frame.dispose();
                button.setEnabled(true);
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        this.frame=this;


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==connectButton){
            int[] selection = this.table.getSelectedRows();
            if (selection.length>0){
                message.setText("");
                try {
                    Item item=fileTable.getResource(selection[0]);
                    Resource resource =(Resource) Naming.lookup(item.getUrl());
                    message.setText("Downloading file ....");
                    message.setForeground(Color.black);
                    byte[] fileData = resource.getFile(item.getFile_name());
                    if (fileData.length>0){
                        JFileChooser fileChooser=new JFileChooser();
                        fileChooser.setSelectedFile(new File("Copy_"+item.getFile_name()));
                        int response=fileChooser.showSaveDialog(frame);
                        if (response==JFileChooser.APPROVE_OPTION){
                            FileOutputStream fos = new FileOutputStream(fileChooser.getSelectedFile().getPath());
                            fos.write(fileData);
                            fos.close();
                            message.setText("File downloaded");
                            message.setForeground(Color.green);
                        }else {
                            message.setText("");
                        }
                    }
                } catch (Exception ex) {
                    message.setText("Server not connected");
                    message.setForeground(Color.red);
                }
            }else {
                message.setText("Select server");
                message.setForeground(Color.red);
            }
        }
    }
}
