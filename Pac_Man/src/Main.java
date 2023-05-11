//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.awt.Component;
import javax.swing.JFrame;

public class Main extends JFrame {
    public Main() {
        this.add(new Model());
    }

    public static void main(String[] args) {
        Main pac = new Main();
        pac.setVisible(true);
        pac.setTitle("Pacman");
        pac.setSize(500, 500);
        pac.setDefaultCloseOperation(3);
        pac.setLocationRelativeTo((Component)null);
    }
}
