package Controller;
import View.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.ArrayList;

public class Board extends Table{
    private String singleMoveB=null;
    private String singleMoveW=null;
    private HashMap<Integer,String> columns=new HashMap<>();
    private JFrame startFrame;
    private ArrayList<String> movements=new ArrayList<>();
    private int numberMove=1;
    public Board(JFrame startFrame){
        setColumns();
        this.startFrame=startFrame;

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e){
                startFrame.setVisible(true);
            }
        });
    }

    @Override
    public   void enableDrag(chip chip){


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
                box destiny=null;
                if (comp instanceof box){
                    destiny=(box) comp;


                }else if(comp !=null && comp.getParent() instanceof box){
                    destiny=(box) comp.getParent();
                }


                box origen=(box) parentBox[0];
                boolean validMove=false;
                if (destiny!=null && origen!=null){
                    int x0=origen.getCoordinateX();
                    int y0=origen.getCoordinateY();
                    int y1=destiny.getCoordinateY();
                    int x1=destiny.getCoordinateX();


                    int deltaX=x1-x0;
                    int deltaY=y1-y0;

                    String color=chip.getColor();

                    if (Math.abs(deltaX)==1){


                        if (color.equals("white") && deltaY==-1){
                            validMove=true;
                        }
                        if (color.equals("black") && deltaY==1){
                            validMove=true;
                        }
                    }else if(Math.abs(deltaX)==2){







                        if (color.equals("white") && deltaY==-2 ){

                            if (deltaX==2){
                                validateRegularElimination(+1,-1,origen,"black");
                                validMove=true;


                            }else if (deltaX==-2){
                                validateRegularElimination(-1,-1,origen,"black");
                                validMove=true;
                            }




                        }


                        if (color.equals("black") && deltaY==2 ){

                            if (deltaX==2){
                                validateRegularElimination(+1,+1,origen,"white");
                                validMove=true;


                            }else if (deltaX==-2){
                                validateRegularElimination(-1,+1,origen,"white");
                                validMove=true;
                            }




                        }



                    }
                    if (chip.getIsQueen()){


                        int row=origen.getCoordinateY();
                        int col=origen.getCoordinateX();
                        while (Math.abs(deltaX)==Math.abs(deltaY) && row >=0 && row<getNumRows() && col>=0 && col<getNumCols()){

                            box diagonal=getBoxes()[row][col];
                            box last=null;

                            int orientation=0;
                            if ((deltaX>0)&&(deltaY>0)){
                                orientation=1;
                                System.out.println("orientación 1");
                            } else if ((deltaX>0)&&(deltaY<0)) {
                                orientation=2;
                                System.out.println("orientación 2");
                            }else if ((deltaX<0)&&(deltaY<0)) {
                                orientation=3;
                                System.out.println("orientación 3");
                            }else if ((deltaX<0)&&(deltaY>0)) {
                                System.out.println("orientación 4");
                                orientation=4;
                            }
                            if( ((diagonal.getCoordinateX()!=origen.getCoordinateX())&&(diagonal.getCoordinateY()!=origen.getCoordinateY()))&&(diagonal.getHasAChip() )&& (((diagonal.getCoordinateX()==destiny.getCoordinateX()-1)||(diagonal.getCoordinateX()==destiny.getCoordinateX()+1))&&((diagonal.getCoordinateY()==destiny.getCoordinateY()-1)||(diagonal.getCoordinateY()==destiny.getCoordinateY()+1)))){



                                if((orientation==1)&&(diagonal.getCoordinateX()==destiny.getCoordinateX()-1)&&(diagonal.getCoordinateY()==destiny.getCoordinateY()-1)){


                                    if (!getChip(diagonal).getColor().equalsIgnoreCase(color)){
                                        validMove=true;
                                        removeChip(0,0, diagonal);

                                    }



                                } else if ((orientation==2)&&(diagonal.getCoordinateX()==destiny.getCoordinateX()-1)&&(diagonal.getCoordinateY()==destiny.getCoordinateY()+1)) {
                                    if (!getChip(diagonal).getColor().equalsIgnoreCase(color)){
                                        validMove=true;
                                        removeChip(0,0, diagonal);
                                    }
                                } else if ((orientation==3)&&(diagonal.getCoordinateX()==destiny.getCoordinateX()+1)&&(diagonal.getCoordinateY()==destiny.getCoordinateY()+1)) {
                                    if (!getChip(diagonal).getColor().equalsIgnoreCase(color)){
                                        validMove=true;
                                        removeChip(0,0, diagonal);
                                    }
                                }else if ((orientation==4)&&(diagonal.getCoordinateX()==destiny.getCoordinateX()+1)&&(diagonal.getCoordinateY()==destiny.getCoordinateY()-1)) {
                                    if (!getChip(diagonal).getColor().equalsIgnoreCase(color)){
                                        validMove=true;
                                        removeChip(0,0, diagonal);
                                    }
                                }
                            } else if ((Math.abs(deltaX)==Math.abs(deltaY))) {

                                validMove=true;


                            }
                            col += (deltaX>0) ? 1:-1;
                            row += (deltaY>0) ? 1:-1;
                            last=diagonal;

                        }



                    }
                }
                if (validMove){
                    chip.setLocation(0,0);
                    destiny.add(chip);
                    destiny.setHasAChip(true);
                    origen.setHasAChip(false);
                    if (chip.getColor().equalsIgnoreCase("black") && (destiny.getCoordinateY()==getNumRows()-1)){
                        chip.changeToQueen();

                    }
                    if (chip.getColor().equalsIgnoreCase("white") && (destiny.getCoordinateY()==0)){
                        chip.changeToQueen();

                    }
                    if(chip.getColor().equalsIgnoreCase("black")){
                        setSingleMoveB(origen, destiny);
                    }else {
                        setSingleMoveW(origen, destiny);
                        registMove();
                        System.out.println(getMovements().getLast());
                    }

                    endMessage(chip);

                }else {
                    chip.setLocation(0,0);
                    origen.add(chip);
                    Object[] options = { "Understood" };
                    JOptionPane.showOptionDialog(
                            null,
                            "INVALID MOVE",
                            "WARNING",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            options,
                            options[0]
                    );
                }

                layeredPane.remove(chip);
                layeredPane.setCursor(Cursor.getDefaultCursor());

                getContentPane().revalidate();
                getContentPane().repaint();
                origen.revalidate();
                origen.repaint();
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

    public chip getChip(JPanel panel){
        for (Component comp : panel.getComponents()) {
            if (comp instanceof chip) {
                chip innerPanel = (chip) comp;
                return innerPanel;

            }
        }
        return null;

    }

    public void removeChip(int gapX, int gapY, box Box){
        getBoxes()[Box.getCoordinateY()+gapY][Box.getCoordinateX()+gapX].removeAll();
        getBoxes()[Box.getCoordinateY()+gapY][Box.getCoordinateX()+gapX].revalidate();
        getBoxes()[Box.getCoordinateY()+gapY][Box.getCoordinateX()+gapX].repaint();
        getBoxes()[Box.getCoordinateY()+gapY][Box.getCoordinateX()+gapX].setHasAChip(false);
    }


    public void validateRegularElimination(int gapX, int gapY, box Box, String color){
        boolean chipExists=(getChip(getBoxes()[Box.getCoordinateY()+gapY][Box.getCoordinateX()+gapX])!=null);
        if (chipExists){
            boolean isNotColor=((getChip(getBoxes()[Box.getCoordinateY()+gapY][Box.getCoordinateX()+gapX]).getColor().equalsIgnoreCase(color)));
            if ( (isNotColor) && ((getBoxes()[Box.getCoordinateY()+gapY][Box.getCoordinateX()+gapX].getHasAChip()))){

                removeChip(gapX,gapY, Box);
            }
        }
    }

    public boolean matchEnded(){
        int Whitechips=0;
        int BlackChips=0;
        for(int i=0; i<getNumRows();i++){
            for (int j=0; j<getNumCols();j++){
                if (getBoxes()[i][j].getHasAChip()){
                    if (getChip(getBoxes()[i][j]).getColor().equalsIgnoreCase("white")){
                        Whitechips=Whitechips+1;
                    }else {
                        BlackChips=BlackChips+1;
                    }
                }
            }
        }
        if (Whitechips==0 || BlackChips==0){
            return true;
        }else {
            return  false;
        }
    }
    public void endMessage(chip Chip){
        if (matchEnded()){
            Object[] options = { "Menu" };
            int Joption=JOptionPane.showOptionDialog(
                    null,
                    "WINNER: "+Chip.getColor(),
                    "END OF MATCH",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );
            if (Joption==0 || Joption==-1){
                setVisible(false);
                new DefaultFrame().setVisible(true);
            }


        }
    }
    public ArrayList<String> getMovements(){
        return this.movements;
    }
    public void registMove(box origin, box destiny){

    }
    public int getNumberMove(){
        return this.numberMove;
    }
    public void setNumberMove(){
        this.numberMove=this.numberMove+1;
    }
    public void setColumns(){
        this.columns.put(0,"a");
        this.columns.put(1,"b");
        this.columns.put(2,"c");
        this.columns.put(3,"d");
        this.columns.put(4,"e");
        this.columns.put(5,"f");
        this.columns.put(6,"g");
        this.columns.put(7,"h");
    }

    public HashMap<Integer, String> getColumns() {
        return this.columns;
    }
    public String singleMove(box origin, box destiny){
        return getColumns().get(origin.getCoordinateX())+String.valueOf(origin.getCoordinateY()+1)+"-"+getColumns().get(destiny.getCoordinateX())+String.valueOf(destiny.getCoordinateY()+1);
    }
    public void setSingleMoveB(box origin, box destiny){
        this.singleMoveB=singleMove(origin,destiny);
    }
    public void setSingleMoveW(box origin, box destiny){
        this.singleMoveW=singleMove(origin,destiny);
    }
    public String getSingleMoveB(){
        return this.singleMoveB;
    }
    public String getSingleMoveW(){
        return this.singleMoveW;
    }
    public void registMove(){
        getMovements().add(String.valueOf(getNumberMove())+". "+getSingleMoveB()+" "+getSingleMoveW());
    }
}
