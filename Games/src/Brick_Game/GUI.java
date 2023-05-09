package Brick_Game;

import javax.swing.*;

public class GUI {
    JFrame frame = new JFrame("GRAPHIC");
    GamePanel gp = new GamePanel();   // box lai extend gareko j panela bana ra


    public void render(){
        frame.setSize(400,400);
        frame.add(gp);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
