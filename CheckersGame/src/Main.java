import Controller.*;
import View.DefaultFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DefaultFrame().setVisible(true);
        });
    }
}