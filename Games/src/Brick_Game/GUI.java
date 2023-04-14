package Brick_Game;

import javax.swing.*;

public class GUI {
    JFrame frame = new JFrame("Brick Game");
    GUI(){
        frame.setSize(500,500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }

}
