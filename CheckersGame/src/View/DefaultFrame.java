package View;
import Controller.Board;

import javax.swing.*;
import java.awt.*;

public class DefaultFrame extends JFrame {
    public DefaultFrame() {
        setTitle("Start");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc= new GridBagConstraints();
        JButton btnStartMatch=new JButton("NEW MATCH");



        btnStartMatch.addActionListener(e -> {

            new Board(this).setVisible(true);
            this.setVisible(false);
        });

        JLabel title = new JLabel("Checkers");
        title.setFont(new Font("Arial", Font.BOLD, 40));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0);
        add(title, gbc);



        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(btnStartMatch, gbc);


        JButton btn2 = new JButton("Btn2");
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(btn2, gbc);



    }

}
