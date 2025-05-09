package View;
import javax.swing.*;
import java.awt.*;
public class chip extends JPanel{
    private String color;
    private JLabel imageLabel;
    public chip(String color){
        this.color=color.toLowerCase();
        setLayout(new BorderLayout());

        ImageIcon icon= new ImageIcon(getClass().getResource("img/"+this.color+".png"));
        imageLabel=new JLabel(icon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);

        add(imageLabel, BorderLayout.CENTER);
        setOpaque(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            }
        });



    }
    public String getColor(){
        return this.color;
    }
}
