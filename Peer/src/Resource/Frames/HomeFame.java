package Resource.Frames;
import Client.Frames.DownloadFrame;
import Resource.objects.FileTable;
import Service.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class HomeFame extends JFrame implements ActionListener {
    Server server;
    JButton addButton;
    JButton removeButton;

    JButton openFileChooser;

    FileTable fileTable;
    JTable table;

    JTextField pathInput;

    String ip;
    String host_name;

    File selectedFile;
    JFileChooser fileChooser;

    JButton downloadFrame;

    HomeFame frame=this;

    public HomeFame(String ip,String host_name,String sIp) throws HeadlessException, SQLException, RemoteException, MalformedURLException, NotBoundException {
        this.server=(Server) Naming.lookup("rmi://"+sIp+":8087/Server");

        JFrame frame=this;

        this.ip=ip;
        this.host_name=host_name;
        server.updateUrl(host_name,"rmi://"+this.ip+":6060"+"/Resource");
        server.upadteConnection(host_name,"true");


        this.fileTable= new FileTable(this.server);

        table=new JTable(fileTable);


        this.setTitle("published file");
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(1000,500));

        this.getContentPane().add(new JScrollPane(table),BorderLayout.CENTER);



        addButton=new JButton();
        addButton.setText("Add New File");
        addButton.addActionListener(this);
        addButton.setPreferredSize(new Dimension(125, 30));

        removeButton=new JButton();
        removeButton.setText("Remove");
        removeButton.addActionListener(this);
        removeButton.setPreferredSize(new Dimension(125, 30));

        downloadFrame=new JButton();
        downloadFrame.setText("Search");
        downloadFrame.addActionListener(this);
        downloadFrame.setPreferredSize(new Dimension(125, 30));

        openFileChooser=new JButton();
        openFileChooser.setText("...");
        openFileChooser.addActionListener(this);
        openFileChooser.setPreferredSize(new Dimension(30, 30));

        pathInput=new JTextField();
        pathInput.setPreferredSize(new Dimension(250,30));
        pathInput.setMaximumSize(new Dimension(250,30));
        pathInput.setEnabled(false);

        JPanel buttomPanel=new JPanel();
        buttomPanel.setLayout(new FlowLayout(FlowLayout.CENTER,80,5));
        buttomPanel.add(removeButton);
        buttomPanel.add(this.downloadFrame);

        JPanel selectionPanel=new JPanel();
        selectionPanel.setLayout(new FlowLayout());
        selectionPanel.add(pathInput);
        selectionPanel.add(openFileChooser);

        JPanel toppanel=new JPanel();
         toppanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,5));
         toppanel.add(selectionPanel);
         toppanel.add(addButton);

        this.add(buttomPanel,BorderLayout.SOUTH);
        this.add(toppanel,BorderLayout.NORTH);
        this.setLocationRelativeTo(null);


        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    server.upadteConnection(host_name,"false");
                    System.exit(0);
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    server.upadteConnection(host_name,"false");
                    System.exit(0);
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
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

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==openFileChooser){
            this.fileChooser=new JFileChooser();
            int response= this.fileChooser.showOpenDialog(this);
            if (response==JFileChooser.APPROVE_OPTION){
                this.selectedFile=new File(this.fileChooser.getSelectedFile().getPath());
                this.pathInput.setText(this.fileChooser.getSelectedFile().getPath());
            }
        }

        if (e.getSource()==addButton){
            if (!(pathInput.getText().trim().isEmpty())){
                try {
                    fileTable.add(
                            "rmi://"+this.ip+":6060"+"/Resource",
                            this.selectedFile.getName(),
                            this.selectedFile.getAbsolutePath(),
                            host_name
                    );
                    pathInput.setText("");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                }
            }
        }

        if (e.getSource()==removeButton){
            int[] selection = table.getSelectedRows();
            for (int i = selection.length - 1; i >= 0; i--) {
                try {
                    fileTable.remove(selection[i],this.host_name);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        if (e.getSource()==downloadFrame){
            try {
                new DownloadFrame(this.frame,this.downloadFrame,this.host_name,this.server).setVisible(true);
                this.downloadFrame.setEnabled(false);
            } catch (MalformedURLException ex) {
                throw new RuntimeException(ex);
            } catch (NotBoundException ex) {
                throw new RuntimeException(ex);
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

    }


}
