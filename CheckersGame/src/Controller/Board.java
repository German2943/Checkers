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
