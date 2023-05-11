//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Model extends JPanel implements ActionListener {
    private Dimension d;
    private final Font smallFont = new Font("Arial", 1, 14);
    private boolean inGame = false;
    private boolean dying = false;
    private final int BLOCK_SIZE = 24;
    private final int N_BLOCKS = 15;
    private final int SCREEN_SIZE = 360;
    private final int MAX_GHOSTS = 12;
    private final int PACMAN_SPEED = 3;
    private int N_GHOSTS = 6;
    private int lives;
    private int score;
    private int[] dx;
    private int[] dy;
    private int[] ghost_x;
    private int[] ghost_y;
    private int[] ghost_dx;
    private int[] ghost_dy;
    private int[] ghostSpeed;
    private Image heart;
    private Image ghost;
    private Image pacman;
    private Image up;
    private Image down;
    private Image left;
    private Image right;
    private int pacman_x;
    private int pacman_y;
    private int pacmand_x;
    private int pacmand_y;
    private int req_dx;
    private int req_dy;
    private final short[] levelData = new short[]{19, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22, 17, 16, 16, 16, 16, 24, 16, 16, 16, 16, 16, 16, 16, 16, 20, 25, 24, 24, 24, 28, 0, 17, 16, 16, 16, 16, 16, 16, 16, 20, 0, 0, 0, 0, 0, 0, 17, 16, 16, 16, 16, 16, 16, 16, 20, 19, 18, 18, 18, 18, 18, 16, 16, 16, 16, 24, 24, 24, 24, 20, 17, 16, 16, 16, 16, 16, 16, 16, 16, 20, 0, 0, 0, 0, 21, 17, 16, 16, 16, 16, 16, 16, 16, 16, 20, 0, 0, 0, 0, 21, 17, 16, 16, 16, 24, 16, 16, 16, 16, 20, 0, 0, 0, 0, 21, 17, 16, 16, 20, 0, 17, 16, 16, 16, 16, 18, 18, 18, 18, 20, 17, 24, 24, 28, 0, 25, 24, 24, 16, 16, 16, 16, 16, 16, 20, 21, 0, 0, 0, 0, 0, 0, 0, 17, 16, 16, 16, 16, 16, 20, 17, 18, 18, 22, 0, 19, 18, 18, 16, 16, 16, 16, 16, 16, 20, 17, 16, 16, 20, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 20, 17, 16, 16, 20, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 20, 25, 24, 24, 24, 26, 24, 24, 24, 24, 24, 24, 24, 24, 24, 28};
    private final int[] validSpeeds = new int[]{1, 2, 3, 4, 6, 8};
    private final int maxSpeed = 6;
    private int currentSpeed = 3;
    private short[] screenData;
    private Timer timer;

    public Model() {
        this.loadImages();
        this.initVariables();
//        this.addKeyListener(new TAdapter(this));
        this.setFocusable(true);
        this.initGame();
    }

    private void loadImages() {
        this.down = (new ImageIcon("/Users/anjanshrestha/Downloads/GitHub/Gamers/images/down.gif")).getImage();
        this.ghost = (new ImageIcon("/Users/anjanshrestha/Downloads/GitHub/Gamers/images/ghost.gif")).getImage();
        this.heart = (new ImageIcon("/Users/anjanshrestha/Downloads/GitHub/Gamers/images/heart.png")).getImage();
        this.left = (new ImageIcon("/Users/anjanshrestha/Downloads/GitHub/Gamers/images/left.gif")).getImage();
        this.pacman = (new ImageIcon("/Users/anjanshrestha/Downloads/GitHub/Gamers/images/pacman.png")).getImage();
        this.right = (new ImageIcon("/Users/anjanshrestha/Downloads/GitHub/Gamers/images/right.gif")).getImage();
        this.up = (new ImageIcon("/Users/anjanshrestha/Downloads/GitHub/Gamers/images/up.gif")).getImage();
    }

    private void initVariables() {
        this.screenData = new short[225];
        this.d = new Dimension(400, 500);
        this.ghost_x = new int[12];
        this.ghost_dx = new int[12];
        this.ghost_y = new int[12];
        this.ghost_dy = new int[12];
        this.ghostSpeed = new int[12];
        this.dx = new int[4];
        this.dy = new int[4];
        this.timer = new Timer(40, this);
        this.timer.start();
    }

    private void playGame(Graphics2D g2d) {
        if (this.dying) {
            this.death(g2d);
        } else {
            this.movePacman();
            this.drawPacman(g2d);
            this.moveGhosts(g2d);
            this.checkMaze();
        }

    }

    private void showIntroScreen(Graphics2D g2d) {
        String start = "Press SPACE to start";
        g2d.setColor(Color.yellow);
        g2d.drawString(start, 90, 150);
    }

    private void drawScore(Graphics2D g) {
        g.setFont(this.smallFont);
        g.setColor(new Color(5, 181, 79));
        String s = "Score: " + this.score;
        g.drawString(s, 276, 376);

        for(int i = 0; i < this.lives; ++i) {
            g.drawImage(this.heart, i * 28 + 8, 361, this);
        }

    }

    private void checkMaze() {
        int i = 0;

        boolean finished;
        for(finished = true; i < 225 && finished; ++i) {
            if (this.screenData[i] != 0) {
                finished = false;
            }
        }

        if (finished) {
            this.score += 50;
            if (this.N_GHOSTS < 12) {
                ++this.N_GHOSTS;
            }

            if (this.currentSpeed < 6) {
                ++this.currentSpeed;
            }

            this.initLevel();
        }

    }

    private void death(Graphics2D g1) {
        --this.lives;
        if (this.lives == 0) {
            this.inGame = false;
            String start = "Game Over!Play Again";
            g1.setColor(Color.red);
            g1.drawString(start, 180, 200);
        }

        this.continueLevel();
    }

    private void moveGhosts(Graphics2D g2d) {
        for(int i = 0; i < this.N_GHOSTS; ++i) {
            if (this.ghost_x[i] % 24 == 0 && this.ghost_y[i] % 24 == 0) {
                int pos = this.ghost_x[i] / 24 + 15 * (this.ghost_y[i] / 24);
                int count = 0;
                if ((this.screenData[pos] & 1) == 0 && this.ghost_dx[i] != 1) {
                    this.dx[count] = -1;
                    this.dy[count] = 0;
                    ++count;
                }

                if ((this.screenData[pos] & 2) == 0 && this.ghost_dy[i] != 1) {
                    this.dx[count] = 0;
                    this.dy[count] = -1;
                    ++count;
                }

                if ((this.screenData[pos] & 4) == 0 && this.ghost_dx[i] != -1) {
                    this.dx[count] = 1;
                    this.dy[count] = 0;
                    ++count;
                }

                if ((this.screenData[pos] & 8) == 0 && this.ghost_dy[i] != -1) {
                    this.dx[count] = 0;
                    this.dy[count] = 1;
                    ++count;
                }

                if (count == 0) {
                    if ((this.screenData[pos] & 15) == 15) {
                        this.ghost_dx[i] = 0;
                        this.ghost_dy[i] = 0;
                    } else {
                        this.ghost_dx[i] = -this.ghost_dx[i];
                        this.ghost_dy[i] = -this.ghost_dy[i];
                    }
                } else {
                    count = (int)(Math.random() * (double)count);
                    if (count > 3) {
                        count = 3;
                    }

                    this.ghost_dx[i] = this.dx[count];
                    this.ghost_dy[i] = this.dy[count];
                }
            }

            this.ghost_x[i] += this.ghost_dx[i] * this.ghostSpeed[i];
            this.ghost_y[i] += this.ghost_dy[i] * this.ghostSpeed[i];
            this.drawGhost(g2d, this.ghost_x[i] + 1, this.ghost_y[i] + 1);
            if (this.pacman_x > this.ghost_x[i] - 12 && this.pacman_x < this.ghost_x[i] + 12 && this.pacman_y > this.ghost_y[i] - 12 && this.pacman_y < this.ghost_y[i] + 12 && this.inGame) {
                this.dying = true;
            }
        }

    }

    private void drawGhost(Graphics2D g2d, int x, int y) {
        g2d.drawImage(this.ghost, x, y, this);
    }

    private void movePacman() {
        if (this.pacman_x % 24 == 0 && this.pacman_y % 24 == 0) {
            int pos = this.pacman_x / 24 + 15 * (this.pacman_y / 24);
            short ch = this.screenData[pos];
            if ((ch & 16) != 0) {
                this.screenData[pos] = (short)(ch & 15);
                ++this.score;
            }

            if ((this.req_dx != 0 || this.req_dy != 0) && (this.req_dx != -1 || this.req_dy != 0 || (ch & 1) == 0) && (this.req_dx != 1 || this.req_dy != 0 || (ch & 4) == 0) && (this.req_dx != 0 || this.req_dy != -1 || (ch & 2) == 0) && (this.req_dx != 0 || this.req_dy != 1 || (ch & 8) == 0)) {
                this.pacmand_x = this.req_dx;
                this.pacmand_y = this.req_dy;
            }

            if (this.pacmand_x == -1 && this.pacmand_y == 0 && (ch & 1) != 0 || this.pacmand_x == 1 && this.pacmand_y == 0 && (ch & 4) != 0 || this.pacmand_x == 0 && this.pacmand_y == -1 && (ch & 2) != 0 || this.pacmand_x == 0 && this.pacmand_y == 1 && (ch & 8) != 0) {
                this.pacmand_x = 0;
                this.pacmand_y = 0;
            }
        }

        this.pacman_x += 3 * this.pacmand_x;
        this.pacman_y += 3 * this.pacmand_y;
    }

    private void drawPacman(Graphics2D g2d) {
        if (this.req_dx == -1) {
            g2d.drawImage(this.left, this.pacman_x + 1, this.pacman_y + 1, this);
        } else if (this.req_dx == 1) {
            g2d.drawImage(this.right, this.pacman_x + 1, this.pacman_y + 1, this);
        } else if (this.req_dy == -1) {
            g2d.drawImage(this.up, this.pacman_x + 1, this.pacman_y + 1, this);
        } else {
            g2d.drawImage(this.down, this.pacman_x + 1, this.pacman_y + 1, this);
        }

    }

    private void drawMaze(Graphics2D g2d) {
        short i = 0;

        for(int y = 0; y < 360; y += 24) {
            for(int x = 0; x < 360; x += 24) {
                g2d.setColor(new Color(0, 72, 251));
                g2d.setStroke(new BasicStroke(5.0F));
                if (this.levelData[i] == 0) {
                    g2d.fillRect(x, y, 24, 24);
                }

                if ((this.screenData[i] & 1) != 0) {
                    g2d.drawLine(x, y, x, y + 24 - 1);
                }

                if ((this.screenData[i] & 2) != 0) {
                    g2d.drawLine(x, y, x + 24 - 1, y);
                }

                if ((this.screenData[i] & 4) != 0) {
                    g2d.drawLine(x + 24 - 1, y, x + 24 - 1, y + 24 - 1);
                }

                if ((this.screenData[i] & 8) != 0) {
                    g2d.drawLine(x, y + 24 - 1, x + 24 - 1, y + 24 - 1);
                }

                if ((this.screenData[i] & 16) != 0) {
                    g2d.setColor(new Color(255, 255, 255));
                    g2d.fillOval(x + 10, y + 10, 6, 6);
                }

                ++i;
            }
        }

    }

    private void initGame() {
        this.lives = 3;
        this.score = 0;
        this.initLevel();
        this.N_GHOSTS = 6;
        this.currentSpeed = 3;
    }

    private void initLevel() {
        for(int i = 0; i < 225; ++i) {
            this.screenData[i] = this.levelData[i];
        }

        this.continueLevel();
    }

    private void continueLevel() {
        int dx = 1;

        for(int i = 0; i < this.N_GHOSTS; ++i) {
            this.ghost_y[i] = 96;
            this.ghost_x[i] = 96;
            this.ghost_dy[i] = 0;
            this.ghost_dx[i] = dx;
            dx = -dx;
            int random = (int)(Math.random() * (double)(this.currentSpeed + 1));
            if (random > this.currentSpeed) {
                random = this.currentSpeed;
            }

            this.ghostSpeed[i] = this.validSpeeds[random];
        }

        this.pacman_x = 168;
        this.pacman_y = 264;
        this.pacmand_x = 0;
        this.pacmand_y = 0;
        this.req_dx = 0;
        this.req_dy = 0;
        this.dying = false;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, this.d.width, this.d.height);
        this.drawMaze(g2d);
        this.drawScore(g2d);
        if (this.inGame) {
            this.playGame(g2d);
        } else {
            this.showIntroScreen(g2d);
        }

        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }

    public void actionPerformed(ActionEvent e) {
        this.repaint();
    }
}
