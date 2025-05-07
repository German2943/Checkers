package View;
import javax.swing.*;
import java.awt.*;

public class Table extends JFrame {
    int numRows=9;
    int numCols=7;
    public Table(){
        setTitle("Checkers Game");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel tablePanel= new JPanel();
        tablePanel.setLayout(new GridLayout(numRows,numCols));

        for (int row=0; row<numRows; row++){
            for(int col=0; col<numCols; col++){
                box singleBox=new box();
                singleBox.setCoordinateX(col);
                singleBox.setCoordinateY(row);


                if ((row + col)% 2 ==0){
                    singleBox.setBackground(Color.WHITE);
                }else {
                    singleBox.setBackground(Color.DARK_GRAY);
                }
                tablePanel.add(singleBox);
                ImageIcon img=new ImageIcon("img/black.png");

                JLabel LabelImg=new JLabel(img);
                LabelImg.setHorizontalAlignment(SwingConstants.CENTER);
                LabelImg.setVerticalAlignment(SwingConstants.CENTER);
                singleBox.add(LabelImg, BorderLayout.CENTER);

            }
        }
        add(tablePanel);
        setVisible(true);
    }
}
