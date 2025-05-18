package View;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public abstract class Table extends JFrame {

    private int numRows=8;
    private int numCols=8;
    private box[][] boxes=new box[numRows][numCols];




    public box[][] getBoxes(){
        return this.boxes;
    }
    public Table(){




        setTitle("Checkers Game");
        setSize(620, 620);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);



        JPanel tablePanel= new JPanel();
        tablePanel.setPreferredSize(new Dimension(496,496));
        tablePanel.setLayout(new GridLayout(numRows,numCols));


        JPanel control=new JPanel();
        control.setBackground(Color.ORANGE);
        control.setLayout(new BorderLayout());
        control.setPreferredSize(new Dimension(620,620));



        JPanel topPanel=new JPanel();
        topPanel.setLayout(new GridLayout(2, 10));
        topPanel.setBackground(Color.LIGHT_GRAY);
        topPanel.setPreferredSize(new Dimension(620,124));
        char c='A';
        for (int i=0; i<2; i++){
            for (int j=0;j<10;j++){
                if (i==1){
                    if(0<j && j<9){
                        JPanel columChar=new JPanel(new BorderLayout());
                        columChar.setBorder(new LineBorder(Color.BLACK, 1));
                        JLabel colum=new JLabel(String.valueOf(c),SwingConstants.CENTER);
                        colum.setFont(new Font("Arial",Font.BOLD,15));
                        columChar.add(colum);
                        topPanel.add(columChar);
                        c++;
                    }else {
                        JPanel filler=new JPanel();
                        filler.setVisible(false);
                        topPanel.add(filler);
                    }
                }else {
                    JPanel filler=new JPanel();
                    filler.setVisible(false);
                    topPanel.add(filler);
                }
            }
        }




        JPanel bottomPanel=new JPanel();
        bottomPanel.setBackground(Color.LIGHT_GRAY);
        bottomPanel.setPreferredSize(new Dimension(620,124));

        JPanel rightPanel=new JPanel();
        rightPanel.setBackground(Color.LIGHT_GRAY);
        rightPanel.setPreferredSize(new Dimension(62,372));

        JPanel leftPanel=new JPanel();
        leftPanel.setBackground(Color.LIGHT_GRAY);
        leftPanel.setLayout(new GridLayout(8,1));
        leftPanel.setPreferredSize(new Dimension(62,372));

        for (int i=1;i<=8;i++){

            JPanel rowNumber =new JPanel(new BorderLayout());
            rowNumber.setBorder(new LineBorder(Color.BLACK, 1));
            JLabel number =new JLabel(String.valueOf(i),SwingConstants.CENTER);
            number.setFont(new Font("Arial",Font.BOLD,15));
            rowNumber.add(number);
            leftPanel.add(rowNumber);
        }


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
        control.add(topPanel, BorderLayout.PAGE_START);
        control.add(leftPanel, BorderLayout.LINE_START);
        control.add(tablePanel, BorderLayout.CENTER);
        control.add(rightPanel, BorderLayout.LINE_END);
        control.add(bottomPanel,BorderLayout.PAGE_END);


        add(control);
        setVisible(true);
    }

    public abstract void enableDrag(chip chip);
    public int getNumRows(){
        return this.numRows;
    }
    public int getNumCols(){
        return  this.numCols;
    }
}
