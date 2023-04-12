package My_2D_Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler extends JFrame implements KeyListener {
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 1;
    public boolean upPress, downPress, rightPress, leftPress;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {


    }

    @Override
    public void keyReleased(KeyEvent e) {
        int k = e.getKeyCode();
        System.out.println(k);
        if (k == 87) {
            playerY = playerY - 30;

//            repaint();
        } else if (k == 68) {
            playerX = playerX + 30;
//            repaint();
        } else if (k == 65) {
            playerX -= 30;
//          repaint();

        } else if (k == 83) {
            playerY += 30;
        } else if (k == 69) {
            playerX+=10;
        }


    }
}
