package View;
import Controller.Board;

import javax.swing.*;
public class DefaultFrame extends JFrame {
    public DefaultFrame() {
        setTitle("Start");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel btnContaineer=new JPanel();
        btnContaineer.setLayout(null);
        setContentPane(btnContaineer);
        JButton btnStartMatch=new JButton("NEW MATCH");

        btnStartMatch.setBounds(100, 50, 150, 40);

        btnStartMatch.addActionListener(e -> {

            new Board(this).setVisible(true);
            this.setVisible(false);
        });
        btnContaineer.add(btnStartMatch);

    }

}
