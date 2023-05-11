import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class  GamePanel extends JPanel implements Runnable,KeyListener {
        int originalTileSize =16;       // 16*16 tiles
        int scale =3;
        int tileSize = originalTileSize*scale;  // 48*48 tiles
        int maxScreenCol =16;
        int  maxScreenRow=12;
        int screenWidth = tileSize*maxScreenCol;  //768 pixels
        int screenHeight = tileSize*maxScreenRow;  //576 pixels

        Thread gameThread;
        KeyHandler keyHand = new KeyHandler();

//        set player's default position
    int  playerX = 100;
    int playerY = 100;
    int playerSpeed = 10;
    public GamePanel(){
            this.setPreferredSize(new Dimension(screenWidth,screenHeight));  // basically yesle game panel ko size change garne kam garxa
            this.setBackground(Color.black);
            //this.setDoubleBuffered(true);
                     // set Double Buffer()
                    //  if it is set to true then all the drawing from this component will be done
                   //   in an offscreen painting buffer.

        this.setFocusable(true);
        this.addKeyListener(this);
//        with this game panel can be focused to recei  ve the input key


    }
    public  void startGameThread(){
        gameThread= new Thread(this);  //??????
        gameThread.start();
    }
        @Override
        public void run() {// When we start thread we basically call run

            int FPS = 60;
            int neededTimeForUpdateInMilliSec = 10000/FPS;
            int NANO_TO_MILLI = 1000000;
            this.addKeyListener(this);

            while(gameThread != null){
//                System.out.println("hlo");
//                1. update : update information such as character position
//                2. draw : draw the screen with the updated information
                long startTime = System.nanoTime();
                repaint();
                long endTime = System.nanoTime(); // timer stop
                long passedTime = startTime - endTime;
                long _deltaTime = neededTimeForUpdateInMilliSec - passedTime/NANO_TO_MILLI;

                try {
                    Thread.sleep(_deltaTime);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }


        }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.green);
        g.fillRect(playerX,playerY,tileSize,tileSize);
        g.dispose();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k=e.getKeyCode();
        System.out.println(k);
        if(k==87)
        {
            playerY-=playerSpeed;
          playerY +=2;
            repaint();
        } else if (k==68) {
            playerX+=playerSpeed;
//            repaint();
        }
        else if (k == 65)
        {
            playerX-=playerSpeed;
//          repaint();
        } else if (k == 83) {
            playerY+=playerSpeed;
//            repaint();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
