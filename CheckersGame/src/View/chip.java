package View;
import javax.swing.*;
import java.awt.*;
public class chip extends JPanel{
    private String color;
    private JLabel imageLabel;
    private boolean isQueen=false;
    public chip(String color){
        this.color=color.toLowerCase();
        setLayout(new BorderLayout());

        ImageIcon icon= new ImageIcon(getClass().getResource("img/"+this.color+".png"));
        ImageIcon scaledImage =new ImageIcon(icon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH))  ;
        imageLabel=new JLabel(scaledImage);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);

        add(imageLabel, BorderLayout.CENTER);
        setOpaque(false);
        setPreferredSize(new Dimension(35,35));




    }
    public void changeToQueen(){
        removeAll();
        this.color=color.toLowerCase();
        setLayout(new BorderLayout());

        ImageIcon icon= new ImageIcon(getClass().getResource("img/"+this.color+"Q.png"));
        imageLabel=new JLabel(icon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);

        add(imageLabel, BorderLayout.CENTER);
        setOpaque(false);
        setIsQueen(true);
    }
    public boolean getIsQueen(){
        return this.isQueen;
    }
    public void setIsQueen(boolean isQueen){
        this.isQueen=isQueen;
    }
    public String getColor(){
        return this.color;
    }

}
