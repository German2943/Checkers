package Controller;
import View.*;

import javax.swing.*;
import java.awt.*;

public class Board extends Table{
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
                            if ((getBoxes()[origen.getCoordinateY()-1][origen.getCoordinateX()+1].getHasAChip())||(getBoxes()[origen.getCoordinateY()-1][origen.getCoordinateX()-1].getHasAChip())){
                                validMove=true;
                                if (deltaX==2){
                                    getBoxes()[origen.getCoordinateY()-1][origen.getCoordinateX()+1].removeAll();
                                    getBoxes()[origen.getCoordinateY()-1][origen.getCoordinateX()+1].revalidate();
                                    getBoxes()[origen.getCoordinateY()-1][origen.getCoordinateX()+1].repaint();

                                }else if (deltaX==-2){
                                    getBoxes()[origen.getCoordinateY()-1][origen.getCoordinateX()-1].removeAll();
                                    getBoxes()[origen.getCoordinateY()-1][origen.getCoordinateX()-1].revalidate();
                                    getBoxes()[origen.getCoordinateY()-1][origen.getCoordinateX()-1].repaint();
                                }
                            }

                        }
                        if (color.equals("black") && deltaY==2){
                            if ((getBoxes()[origen.getCoordinateY()+1][origen.getCoordinateX()+1].getHasAChip())||(getBoxes()[origen.getCoordinateY()+1][origen.getCoordinateX()-1].getHasAChip())){
                                validMove=true;
                                if (deltaX==2){
                                    getBoxes()[origen.getCoordinateY()+1][origen.getCoordinateX()+1].removeAll();
                                    getBoxes()[origen.getCoordinateY()+1][origen.getCoordinateX()+1].revalidate();
                                    getBoxes()[origen.getCoordinateY()+1][origen.getCoordinateX()+1].repaint();

                                }else if (deltaX==-2){
                                    getBoxes()[origen.getCoordinateY()+1][origen.getCoordinateX()-1].removeAll();
                                    getBoxes()[origen.getCoordinateY()+1][origen.getCoordinateX()-1].revalidate();
                                    getBoxes()[origen.getCoordinateY()+1][origen.getCoordinateX()-1].repaint();
                                }
                            }
                        }
                    }
                }
                if (validMove){
                    chip.setLocation(0,0);
                    destiny.add(chip);
                    destiny.setHasAChip(true);
                    origen.setHasAChip(false);

                }else {
                    chip.setLocation(0,0);
                    origen.add(chip);
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
}
