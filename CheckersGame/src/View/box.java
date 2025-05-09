package View;
import javax.swing.JPanel;
import java.awt.*;

public class box extends JPanel{
    private int coordinateX;
    private int coordinateY;
    BorderLayout border=new BorderLayout();
    public box(){


    }

    public int getCoordinateX(){
        return coordinateX;
    }
    public void setCoordinateX(int coordinateX){
        this.coordinateX=coordinateX;
    }
    public int getCoordinateY(){
        return coordinateY;
    }
    public void setCoordinateY(int coordinateY){
        this.coordinateY=coordinateY;
    }
}
