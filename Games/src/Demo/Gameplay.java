package Demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gameplay extends JFrame implements KeyListener, ActionListener {
    boolean play =false;
    int score =0;
    int totalBricks = 21;
    Timer time ;
    int delay =8;
    int playerX =310,playerY=450,y=100,z=8;
    int ballposX = 120;
    int ballposY = 350;
    int ballXdir = -1;
    int ballYdir = -2;

    Gameplay(){
        this.setSize(500,500);
        //this.add(gp);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.getContentPane().setBackground(Color.BLACK);
        this.setFocusTraversalKeysEnabled(false);
        time = new Timer(delay,this);
        time.start();
        

    }

    @Override
    public void paint(Graphics g) {
        // background
        g.setColor(Color.black);
        g.fillRect(1,1,30,30);

        // border
        g.setColor(Color.yellow);
        g.fillRect(0,0,3,500);
        g.fillRect(0,0,500,35);
        g.fillRect(497,0,3,500);

        // padel
        g.setColor(Color.green);
        g.fillRect(playerX,playerY,y,z);

        // ball
        g.setColor(Color.yellow);
        g.fillOval(ballposX,ballposY,20,20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        time.start();
        repaint();

    }


    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_RIGHT ){
            {
                playerX+=20;
                 repaint();




            }

        }
//        else if (e.getKeyCode()== KeyEvent.VK_LEFT ){
//            if(playerX<=10){
//                playerX=10;
//            }
//            else {
//                play = true;
//
//                playerX-=20;
//                repaint();
//
//            }repaint
        }
    }

