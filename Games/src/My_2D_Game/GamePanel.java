package My_2D_Game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{
        int originalTileSize =16;       // 16*16 tiles
        int scale =3;
        int tileSize = originalTileSize*scale;  // 48*48 tiles
        int maxScreenCol =16;
        int maxScreenrow=12;
        int screenWidth = tileSize*maxScreenCol;  //768 pixels
        int screenHeight = tileSize*maxScreenrow;  //576 pixels
    public GamePanel(){
            this.setPreferredSize(new Dimension(screenWidth,screenHeight));  // basically yesle game panel ko size change garne kam garxa
            this.setBackground(Color.black);
            this.setDoubleBuffered(true);

            // set Double Buffer()
            //  if it is set to true then all the drawing from this component will be done
           //   in an offscreen painting buffer.
    }
}
