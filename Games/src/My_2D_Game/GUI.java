package My_2D_Game;

import javax.swing.*;
import java.awt.*;

public class GUI {
    GUI(){
        frame.getContentPane().setBackground(Color.green);
    }
    JFrame frame = new JFrame("2D Adventure");
    GamePanel gp = new GamePanel();

    public void render() {
//        frame.setSize(400, 400);

        frame.add(gp);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }
}
