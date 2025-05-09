package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Table extends JFrame {
    private int numRows=8;
    private int numCols=8;


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


                if ((row + col)% 2 ==0){
                    singleBox.setBackground(Color.WHITE);
                }else {
                    singleBox.setBackground(Color.DARK_GRAY);
                }
                if ((row<=2) || (row>=(numRows-3))){
                    String color=null;
                    if(row<=2){

                        color="black";

                        if (((row==0)||(row==2)) && (col%2==0)){
                            chip singleChip=new chip(color);
                            singleBox.add(singleChip);
                            enableDrag(singleChip);
                        }else if((row==1) && (col%2!=0)){
                            chip singleChip=new chip(color);
                            singleBox.add(singleChip);
                            enableDrag(singleChip);
                        }
                    }
                    if((row>=(numRows-3)) ){
                        color="white";

                        if (((row==numRows-1)||(row==numRows-3)) && (col%2==0)){
                            chip singleChip=new chip(color);
                            singleBox.add(singleChip);
                            enableDrag(singleChip);
                        }else if((row==numRows-2) && (col%2!=0)){
                            chip singleChip=new chip(color);
                            singleBox.add(singleChip);
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

    private void enableDrag(chip chip){
        final Point[] offset={new Point()};
        final JLayeredPane layeredPane=getLayeredPane();
        final Container[] parentBox={null};
        chip.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mousePressed(java.awt.event.MouseEvent e){
                parentBox[0]=chip.getParent();
                offset[0]=e.getPoint();
                layeredPane.add(chip, JLayeredPane.DRAG_LAYER);
                layeredPane.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));

            }
            @Override
            public void mouseReleased(java.awt.event.MouseEvent e){
                Component comp=SwingUtilities.getDeepestComponentAt(getContentPane(), e.getXOnScreen()-getLocationOnScreen().x, e.getYOnScreen()-getLocationOnScreen().y);

                if (comp instanceof box){
                    box destiny=(box) comp;
                    chip.setLocation(0,0);
                    destiny.add(chip);
                }else if(comp !=null && comp.getParent() instanceof box){
                    box destiny=(box) comp.getParent();
                    chip.setLocation(0,0);
                    destiny.add(chip);
                }else {
                    if (parentBox[0]!=null){
                        chip.setLocation(0,0);
                        parentBox[0].add(chip);
                    }
                }
                layeredPane.remove(chip);
                layeredPane.setCursor(Cursor.getDefaultCursor());
                parentBox[0].revalidate();
                parentBox[0].repaint();
                getContentPane().revalidate();
                getContentPane().repaint();
            }
        });
        chip.addMouseMotionListener(new java.awt.event.MouseMotionAdapter(){
            @Override
            public void mouseDragged(java.awt.event.MouseEvent e){
                Point mousePoint=SwingUtilities.convertPoint(chip,e.getPoint(), layeredPane);
                chip.setLocation(mousePoint.x - offset[0].x, mousePoint.y - offset[0].y);
                chip.repaint();
            }
        });
    }
}
