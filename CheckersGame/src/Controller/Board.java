package Controller;
import View.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;

public class Board extends Table{

    private String singleMoveB="NoMove";
    private String singleMoveW="NoMove";
    private HashMap<Integer,String> columns=new HashMap<>();
    private JFrame startFrame;
    private ArrayList<String> movements=new ArrayList<>();
    private int numberMove=1;
    private int mainNumberMove=1;


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
                boolean pieceRemoved=false;
                if (comp instanceof box){
                    destiny=(box) comp;


                }else if(comp !=null && comp.getParent() instanceof box){
                    destiny=(box) comp.getParent();
                }


                box eliminated=null;
                String colorOpposite=null;
                int gapX=0;
                int gapY=0;

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
                                gapX=+1;
                                gapY=-1;
                                eliminated=origen;
                                colorOpposite="black";

                                pieceRemoved=true;
                                validMove=true;


                            }else if (deltaX==-2){
                                gapX=-1;
                                gapY=-1;
                                eliminated=origen;
                                colorOpposite="black";
                                pieceRemoved=true;
                                validMove=true;
                            }




                        }


                        if (color.equals("black") && deltaY==2 ){

                            if (deltaX==2){
                                gapX=+1;
                                gapY=+1;
                                eliminated=origen;
                                colorOpposite="white";
                                pieceRemoved=true;
                                validMove=true;


                            }else if (deltaX==-2){
                                gapX=-1;
                                gapY=+1;
                                eliminated=origen;
                                colorOpposite="white";
                                pieceRemoved=true;
                                validMove=true;
                            }




                        }



                    }
                    if (chip.getIsQueen()){


                        int row=origen.getCoordinateY();
                        int col=origen.getCoordinateX();
                        while (Math.abs(deltaX)==Math.abs(deltaY) && row >=0 && row<getNumRows() && col>=0 && col<getNumCols()){

                            box diagonal=getBoxes()[row][col];


                            int orientation=0;
                            if ((deltaX>0)&&(deltaY>0)){
                                orientation=1;
                            } else if ((deltaX>0)&&(deltaY<0)) {
                                orientation=2;
                            }else if ((deltaX<0)&&(deltaY<0)) {
                                orientation=3;
                            }else if ((deltaX<0)&&(deltaY>0)) {
                                orientation=4;
                            }
                            if( ((diagonal.getCoordinateX()!=origen.getCoordinateX())&&(diagonal.getCoordinateY()!=origen.getCoordinateY()))&&(diagonal.getHasAChip() )&& (((diagonal.getCoordinateX()==destiny.getCoordinateX()-1)||(diagonal.getCoordinateX()==destiny.getCoordinateX()+1))&&((diagonal.getCoordinateY()==destiny.getCoordinateY()-1)||(diagonal.getCoordinateY()==destiny.getCoordinateY()+1)))){



                                if((orientation==1)&&(diagonal.getCoordinateX()==destiny.getCoordinateX()-1)&&(diagonal.getCoordinateY()==destiny.getCoordinateY()-1)){


                                    if (!getChip(diagonal).getColor().equalsIgnoreCase(color)){
                                        validMove=true;
                                        gapX=0;
                                        gapY=0;
                                        eliminated=diagonal;
                                        pieceRemoved=true;

                                    }



                                } else if ((orientation==2)&&(diagonal.getCoordinateX()==destiny.getCoordinateX()-1)&&(diagonal.getCoordinateY()==destiny.getCoordinateY()+1)) {
                                    if (!getChip(diagonal).getColor().equalsIgnoreCase(color)){
                                        validMove=true;
                                        gapX=0;
                                        gapY=0;
                                        eliminated=diagonal;

                                        pieceRemoved=true;
                                    }
                                } else if ((orientation==3)&&(diagonal.getCoordinateX()==destiny.getCoordinateX()+1)&&(diagonal.getCoordinateY()==destiny.getCoordinateY()+1)) {
                                    if (!getChip(diagonal).getColor().equalsIgnoreCase(color)){
                                        validMove=true;
                                        gapX=0;
                                        gapY=0;
                                        eliminated=diagonal;
                                        pieceRemoved=true;
                                    }
                                }else if ((orientation==4)&&(diagonal.getCoordinateX()==destiny.getCoordinateX()+1)&&(diagonal.getCoordinateY()==destiny.getCoordinateY()-1)) {
                                    if (!getChip(diagonal).getColor().equalsIgnoreCase(color)){
                                        validMove=true;
                                        gapX=0;
                                        gapY=0;
                                        eliminated=diagonal;
                                        pieceRemoved=true;
                                    }
                                }
                            } else if ((Math.abs(deltaX)==Math.abs(deltaY))) {

                                validMove=true;


                            }
                            col += (deltaX>0) ? 1:-1;
                            row += (deltaY>0) ? 1:-1;


                        }



                    }
                }


                if (validMove){
                    if(!(((getMainNumberMove()%2 !=0)&&(chip.getColor().equalsIgnoreCase("black")))||((getMainNumberMove()%2 ==0)&&(chip.getColor().equalsIgnoreCase("white"))))){
                        validMove=false;
                        chip.setLocation(0,0);
                        origen.add(chip);
                        Object[] options = { "Understood" };
                        JOptionPane.showOptionDialog(
                                null,
                                "It's not this color's turn yet",
                                "WARNING",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                options,
                                options[0]
                        );

                    }else {
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
                            setSingleMoveB(origen, destiny, pieceRemoved);
                        }else {
                            setSingleMoveW(origen, destiny, pieceRemoved);
                            registMove();
                            System.out.println(getMovements().getLast());
                            setNumberMove();
                            resetSingleMoveB();
                            resetSingleMoveW();
                        }
                        if(pieceRemoved){
                            if((chip.getIsQueen())){
                                removeChip(gapX,gapY,eliminated);
                            }else {
                                validateRegularElimination(gapX,gapY,eliminated,colorOpposite);
                            }

                        }
                        setMainNumberMove();

                        endMessage(chip, chip.getColor());
                    }


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
    public void endMessage(chip Chip, String winner){
        if (matchEnded()){
            registMove();
            System.out.println(getMovements().getLast());
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
            setWinner(winner);


        }
    }
    public ArrayList<String> getMovements(){
        return this.movements;
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
    public String singleMove(box origin, box destiny, boolean pieceRemoved){
        String separator=" ";
        if(pieceRemoved){
            separator="x";
        }else {
            separator="-";
        }
        return getColumns().get(origin.getCoordinateX())+String.valueOf(origin.getCoordinateY()+1)+separator+getColumns().get(destiny.getCoordinateX())+String.valueOf(destiny.getCoordinateY()+1);
    }
    public void setSingleMoveB(box origin, box destiny, boolean pieceRemoved){
        this.singleMoveB=singleMove(origin,destiny, pieceRemoved);
    }
    public void setSingleMoveW(box origin, box destiny, boolean pieceRemoved){
        this.singleMoveW=singleMove(origin,destiny, pieceRemoved);
    }
    public String getSingleMoveB(){
        return this.singleMoveB;
    }
    public String getSingleMoveW(){
        return this.singleMoveW;
    }
    public void resetSingleMoveB(){
        this.singleMoveB="NoMove";
    }
    public void resetSingleMoveW(){
        this.singleMoveW="NoMove";
    }
    public void registMove(){
        getMovements().add(String.valueOf(getNumberMove())+". "+getSingleMoveB()+" "+getSingleMoveW());
        getMovementsList().add(String.valueOf(getNumberMove())+". "+getSingleMoveB()+" "+getSingleMoveW());
        this.saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File file = new File("matches/" + getMatchTitle() + ".txt");
                    file.getParentFile().mkdirs();
                    FileWriter writer=new FileWriter(file);
                    writer.write("[white "+getPlayer1()+"]"+System.lineSeparator());
                    writer.write("[black "+getPlayer2()+"]"+System.lineSeparator());

                    int scoreW=0;
                    int scoreB=0;
                    if (getWinner()!=null){
                        if (getWinner().equalsIgnoreCase("black")){
                            scoreB=1;
                        } else if (getWinner().equalsIgnoreCase("white")) {
                            scoreW=1;
                        }
                    }
                    writer.write("[Result "+scoreB+"-"+scoreW+"]"+System.lineSeparator());
                    for (String line : getMovementsList()){
                        writer.write(line+System.lineSeparator());
                    }


                    writer.close();
                } catch (IOException ex) {
                    JOptionPane.showConfirmDialog(null,"ERROR");
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al guardar el archivo: " + ex.getMessage());

                }
            }
        });



    }


    public int getMainNumberMove(){
        return this.mainNumberMove;

    }
    public void setMainNumberMove(){
        this.mainNumberMove=this.mainNumberMove+1;
    }



}
