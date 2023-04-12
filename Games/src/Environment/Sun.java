package Environment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sun extends JPanel implements ActionListener {

    int sunX=50;
    int sunY=50;
    int skyColorR = 135;
    int skyColorG = 206;
    int skyColorB = 235;
    Sun(){
        setBackground(new Color(skyColorR, skyColorG,skyColorB));
        JButton button = new JButton("Click to move");
        add(button);
        button.addActionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.orange);
        g.fillOval(sunX,sunY,50,50);

        g.setPaintMode();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        sunY+=10;

        sunX+= 10 ;
        repaint();
        System.out.println("sun moved");
    }
}
