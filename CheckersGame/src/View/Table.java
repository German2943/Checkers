package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class Table extends JFrame {
    private int numRows=8;
    private int numCols=8;
    private box[][] boxes=new box[numRows][numCols];

    public box[][] getBoxes(){
        return this.boxes;
    }
    public Table(){
        setTitle("Checkers Game");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel tablePanel= new JPanel();
        tablePanel.setLayout(new GridLayout(numRows,numCols));



        for (int row=0; row<numRows; row++){
            for(int col=0; col<numCols; col++){
                box singleBox=new box();
                singleBox.setCoordinateX(col);
                singleBox.setCoordinateY(row);
                boxes[row][col]=singleBox;


                if ((row + col)% 2 ==0){
                    singleBox.setBackground(Color.WHITE);
                }else {
                    singleBox.setBackground(Color.DARK_GRAY);
                }
                if ((row<=2) || (row>=(numRows-3))){
                    String color=null;
                    if(row<=2){

                        color="black";

                        if (((row==0)||(row==2)) && (col%2!=0)){
                            chip singleChip=new chip(color);
                            singleBox.add(singleChip);
                            singleBox.setHasAChip(true);
                            enableDrag(singleChip);
                        }else if((row==1) && (col%2==0)){
                            chip singleChip=new chip(color);
                            singleBox.add(singleChip);
                            singleBox.setHasAChip(true);
                            enableDrag(singleChip);
                        }
                    }
                    if((row>=(numRows-3)) ){
                        color="white";

                        if (((row==numRows-1)||(row==numRows-3)) && (col%2==0)){
                            chip singleChip=new chip(color);
                            singleBox.add(singleChip);
                            singleBox.setHasAChip(true);
                            enableDrag(singleChip);
                        }else if((row==numRows-2) && (col%2!=0)){
                            chip singleChip=new chip(color);
                            singleBox.add(singleChip);
                            singleBox.setHasAChip(true);
                            enableDrag(singleChip);
                        }
                    }






                }
                tablePanel.add(singleBox);

            }
        }
        add(tablePanel);
        setVisible(true);
    }

    public abstract void enableDrag(chip chip);
}
