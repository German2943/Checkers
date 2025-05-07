package View;
import javax.swing.*;
import java.awt.*;

public class Table extends JFrame {
    public Table(){
        setTitle("Checkers Game");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel tablePanel= new JPanel();
        tablePanel.setLayout(new GridLayout(8,8));

        for (int row=0; row<8; row++){
            for(int col=0; col<8; col++){
                box singleBox=new box();
                singleBox.setCoordinateX(col);
                singleBox.setCoordinateY(row);


                if ((row + col)% 2 ==0){
                    singleBox.setBackground(Color.WHITE);
                }else {
                    singleBox.setBackground(Color.DARK_GRAY);
                }
                tablePanel.add(singleBox);
            }
        }
        add(tablePanel);
        setVisible(true);
    }
}
