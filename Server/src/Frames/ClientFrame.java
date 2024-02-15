package Frames;


import Service.ServiceImp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

public class ClientFrame extends JFrame implements ActionListener {
    ClientChatTable chatTable = new ClientChatTable();
    JTable table = new JTable(chatTable);


    public ClientFrame() throws MalformedURLException, NotBoundException, RemoteException, InterruptedException, AlreadyBoundException, SQLException, UnknownHostException {


        InetAddress inetAddress=InetAddress.getLocalHost();
        String myIp= inetAddress.getHostAddress();
        this.chatTable.addCommand("Server online : "+myIp);
        JPanel frame = new JPanel();
        frame.setSize(new Dimension(600, 500));
        setSize(new Dimension(600, 500));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(new Color(61, 61, 61));
        setTitle("Server");

        JPanel buttomPanel = new JPanel();
        buttomPanel.setSize(new Dimension(600, 60));
        buttomPanel.setLayout(new BorderLayout());

        setLayout(new BorderLayout());
        frame.setLayout(new BorderLayout());
        frame.add(buttomPanel, BorderLayout.SOUTH);
        frame.setBackground(new Color(73, 73, 74));
        table.setRowHeight(table.getRowHeight() + 10);
        table.setShowGrid(false);
        table.setFont(new Font("Monospace", 0, 14));
        table.setSelectionBackground(new Color(0f, 0f, 0f, 0f));
        table.setTableHeader(null);
        table.setBackground(new Color(238, 238, 238, 255));
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        setLocationRelativeTo(null);
        add(frame);
        pack();
        Registry registry= LocateRegistry.createRegistry(8087);
        System.out.println(registry);
        registry.rebind("Server",new ServiceImp(chatTable));
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
