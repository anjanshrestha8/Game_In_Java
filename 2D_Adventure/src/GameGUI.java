import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameGUI implements KeyListener {
    JFrame frame = new JFrame("Just a Game");
    JPanel ground = new JPanel();
    JPanel player = new JPanel();
    JPanel ball = new JPanel();

    boolean isLeftKeyPressed,isRightKeyPressed; //Keyboder press or not

//    Player Speed (Fast Af)
    float speed = 5f;

//  Player positionin X and Y coordinate
    int playerPosX = 340;
    int playerPosY = 550;


    GameGUI(){
        frame.setLayout(null);
        frame.setSize(700,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        frame.add(ball);
        frame.add(ground);
        frame.add(player);
    }

//  This is a Ground panel code. Ground do nothing just like me...
    public void groundPannel(){
        ground.setBounds(0,600,700,100);
        ground.setBackground(Color.green);
    }

//    This is a Player code i.e. just the size and player position.
    public void playerScript(){
//      player.setBounds(340,550,50,50);
        player.setSize(50,50);
        player.setBackground(Color.red);
        player.setLocation(playerPosX,playerPosY);
        player.addKeyListener(this);
        player.setFocusable(true); //it means that the component is capable of receiving keyboard focus.
    }

    //Updating player Position
    public void updatePlayerPosition() {
        if (isLeftKeyPressed) {
            playerPosX -= speed;
        }
        if (isRightKeyPressed) {
            playerPosX += speed;
        }
        player.setLocation(playerPosX, playerPosY);
    }

//    Key Listener code goes burrrrrrrrrrrrr...............
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                isLeftKeyPressed = true;
                break;
            case KeyEvent.VK_RIGHT:
                isRightKeyPressed = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                isLeftKeyPressed = false;
                break;
            case KeyEvent.VK_RIGHT:
                isRightKeyPressed = false;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


//    Here goes player burrrrrrrrrrrrrrr............
    public void playerMovement() {
        while (true) {
            // Update player position based on input
            if (isLeftKeyPressed) {
                playerPosX -= speed;
            }
            if (isRightKeyPressed) {
                playerPosX += speed;
            }
            player.setLocation(playerPosX, playerPosY); //reset player position

            // Sleep for a short time to limit the frame rate
            //1 sec = 1000 millisecond, So (1000/10 == 100)
            //100 frame per sec(Smooth player bhai Smooth)
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
