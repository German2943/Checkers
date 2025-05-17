package Controller;
import View.DefaultFrame;

import javax.swing.*;

public class MainController {


    public void Initializer(){
        SwingUtilities.invokeLater(() -> {
            new DefaultFrame().setVisible(true);
        });
    }
}
