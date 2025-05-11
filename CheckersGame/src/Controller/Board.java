package Controller;
import View.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Board extends Table{
    private JFrame startFrame;
    public Board(JFrame startFrame){
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
                        System.out.println("verificación de cambio en Y");





                        System.out.println("verificación de límites de coordenadas");


                        if (color.equals("white") && deltaY==-2 ){
                            System.out.println("confirmación de cambio en Y");
                            System.out.println("delta Y: "+deltaY);
                            System.out.println("delta X: "+deltaX);
                            if (deltaX==2){
                                System.out.println("confirmación de cambio positivo en x");
                                boolean chipExists3=(getChip(getBoxes()[origen.getCoordinateY()-1][origen.getCoordinateX()+1])!=null);
                                if (chipExists3){
                                    System.out.println("confirmación de existencia de chip");
                                    boolean isBlack=((getChip(getBoxes()[origen.getCoordinateY()-1][origen.getCoordinateX()+1]).getColor().equalsIgnoreCase("black")));
                                    if ( (isBlack) && ((getBoxes()[origen.getCoordinateY()-1][origen.getCoordinateX()+1].getHasAChip()))){
                                        System.out.println("confirmación de color y prescencia en columna y fila");
                                        validMove=true;
                                        getBoxes()[origen.getCoordinateY()-1][origen.getCoordinateX()+1].removeAll();
                                        getBoxes()[origen.getCoordinateY()-1][origen.getCoordinateX()+1].revalidate();
                                        getBoxes()[origen.getCoordinateY()-1][origen.getCoordinateX()+1].repaint();
                                        getBoxes()[origen.getCoordinateY()-1][origen.getCoordinateX()+1].setHasAChip(false);
                                    }
                                }


                            }else if (deltaX==-2){
                                boolean chipExists4=(getChip(getBoxes()[origen.getCoordinateY()-1][origen.getCoordinateX()-1])!=null);
                                if (chipExists4){
                                    boolean isBlack=((getChip(getBoxes()[origen.getCoordinateY()-1][origen.getCoordinateX()-1]).getColor().equalsIgnoreCase("black")));
                                    if ( (isBlack) && ((getBoxes()[origen.getCoordinateY()-1][origen.getCoordinateX()-1].getHasAChip()))){
                                        validMove=true;
                                        getBoxes()[origen.getCoordinateY()-1][origen.getCoordinateX()-1].removeAll();
                                        getBoxes()[origen.getCoordinateY()-1][origen.getCoordinateX()-1].revalidate();
                                        getBoxes()[origen.getCoordinateY()-1][origen.getCoordinateX()-1].repaint();
                                        getBoxes()[origen.getCoordinateY()-1][origen.getCoordinateX()-1].setHasAChip(false);
                                    }
                                }
                            }




                        }


                        if (color.equals("black") && deltaY==2 ){
                            System.out.println("confirmación de cambio en Y");
                            System.out.println("delta Y: "+deltaY);
                            System.out.println("delta X: "+deltaX);
                            if (deltaX==2){
                                System.out.println("confirmación de cambio positivo en x");
                                boolean chipExists1=(getChip(getBoxes()[origen.getCoordinateY()+1][origen.getCoordinateX()+1])!=null);
                                if (chipExists1){
                                    System.out.println("confirmación de existencia de chip");
                                    boolean isWhite=((getChip(getBoxes()[origen.getCoordinateY()+1][origen.getCoordinateX()+1]).getColor().equalsIgnoreCase("white")));
                                    if ( (isWhite) && ((getBoxes()[origen.getCoordinateY()-1][origen.getCoordinateX()+1].getHasAChip()))){
                                        System.out.println("confirmación de color y prescencia en columna y fila");
                                        validMove=true;
                                        getBoxes()[origen.getCoordinateY()+1][origen.getCoordinateX()+1].removeAll();
                                        getBoxes()[origen.getCoordinateY()+1][origen.getCoordinateX()+1].revalidate();
                                        getBoxes()[origen.getCoordinateY()+1][origen.getCoordinateX()+1].repaint();
                                        getBoxes()[origen.getCoordinateY()+1][origen.getCoordinateX()+1].setHasAChip(false);
                                    }
                                }


                            }else if (deltaX==-2){
                                boolean chipExists2=(getChip(getBoxes()[origen.getCoordinateY()+1][origen.getCoordinateX()-1])!=null);
                                if (chipExists2){
                                    boolean isWhite=((getChip(getBoxes()[origen.getCoordinateY()+1][origen.getCoordinateX()-1]).getColor().equalsIgnoreCase("white")));
                                    if ( (isWhite) && ((getBoxes()[origen.getCoordinateY()+1][origen.getCoordinateX()-1].getHasAChip()))){
                                        validMove=true;
                                        getBoxes()[origen.getCoordinateY()+1][origen.getCoordinateX()-1].removeAll();
                                        getBoxes()[origen.getCoordinateY()+1][origen.getCoordinateX()-1].revalidate();
                                        getBoxes()[origen.getCoordinateY()+1][origen.getCoordinateX()-1].repaint();
                                        getBoxes()[origen.getCoordinateY()+1][origen.getCoordinateX()-1].setHasAChip(false);
                                    }
                                }
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
                                    System.out.println("O1");


                                    if (!getChip(diagonal).getColor().equalsIgnoreCase(color)){
                                        validMove=true;
                                        diagonal.removeAll();
                                        diagonal.revalidate();
                                        diagonal.repaint();
                                        diagonal.setHasAChip(false);

                                    }



                                } else if ((orientation==2)&&(diagonal.getCoordinateX()==destiny.getCoordinateX()-1)&&(diagonal.getCoordinateY()==destiny.getCoordinateY()+1)) {
                                    System.out.println("O2 ");
                                    if (!getChip(diagonal).getColor().equalsIgnoreCase(color)){
                                        validMove=true;
                                        diagonal.removeAll();
                                        diagonal.revalidate();
                                        diagonal.repaint();
                                        diagonal.setHasAChip(false);
                                    }
                                } else if ((orientation==3)&&(diagonal.getCoordinateX()==destiny.getCoordinateX()+1)&&(diagonal.getCoordinateY()==destiny.getCoordinateY()+1)) {
                                    System.out.println("O3 ");
                                    if (!getChip(diagonal).getColor().equalsIgnoreCase(color)){
                                        validMove=true;
                                        diagonal.removeAll();
                                        diagonal.revalidate();
                                        diagonal.repaint();
                                        diagonal.setHasAChip(false);
                                    }
                                }else if ((orientation==4)&&(diagonal.getCoordinateX()==destiny.getCoordinateX()+1)&&(diagonal.getCoordinateY()==destiny.getCoordinateY()-1)) {
                                    System.out.println("O4 ");
                                    if (!getChip(diagonal).getColor().equalsIgnoreCase(color)){
                                        validMove=true;
                                        diagonal.removeAll();
                                        diagonal.revalidate();
                                        diagonal.repaint();
                                        diagonal.setHasAChip(false);
                                    }
                                }
                            } else if ((Math.abs(deltaX)==Math.abs(deltaY))) {
                                System.out.println("O5");

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
    public boolean verifyOutBounds(int row, int col){
        if (row+1<getNumRows() && row-1>=0 && col+1<getNumRows() && col-1>=0){
            return true;
        }else {
            return false;
        }

    }
}
