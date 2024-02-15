import Resource.Frames.HomeFame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;


public class ConnectionFrame implements ActionListener {

    JFrame frame;
    JButton ipButton = new JButton();
    JTextField input = new JTextField();

    JLabel er = new JLabel();

    public ConnectionFrame() {
        frame = new JFrame();
        frame.setSize(new Dimension(350, 300));
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 40));
        frame.setTitle("Connection");
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 0, 10, 10));
        JLabel titre = new JLabel();
        titre.setText("IP :");
        titre.setSize(new Dimension(20, 20));
        titre.setFont(new Font("", 0, 16));

        input.setPreferredSize(new Dimension(250, 30));


        panel.add(titre);
        panel.add(input);

        ipButton.setText("Connect");
        ipButton.addActionListener(this);

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1, 1, 40, 0));
        buttons.setSize(new Dimension(500, 70));
        buttons.add(ipButton);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.add(er);
        frame.add(buttons);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==ipButton){
            if (!input.getText().trim().isEmpty()){
                try {
                    InetAddress inetAddress=InetAddress.getLocalHost();
                    String myIp= inetAddress.getHostAddress();
                    new HomeFame(myIp,inetAddress.getHostName(),input.getText().trim()).setVisible(true);
                    frame.setVisible(false);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        }

    }
}
