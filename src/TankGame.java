import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.awt.event.KeyListener;

public class TankGame extends JPanel implements Runnable, KeyListener{

    // game window size
    public static final int WIDTH = 720;
    public static final int HEIGHT = 480;


    private PlayerOne PlayerOne;
    private PlayerTwo PlayerTwo;
    private Thread thread;
    private Boolean isRunning, bothAlive, menu;
    private int FPS = 60; // frames per second
    private long targetTime = 1000 / FPS;

    private BufferedImage image;
    private Graphics2D g;

    private MapSwitcher switcher;

    public TankGame(){
        super();
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    public void init(){
        PlayerOne = new PlayerOne();
        PlayerTwo = new PlayerTwo();
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        isRunning = true;

        BufferedImage bufferedPlayerOne;
        BufferedImage bufferedPlayerTwo;

    }

    public void run(){
        init();

    }
    public void keyTyped(KeyEvent key) {
    }

    public void keyPressed(KeyEvent key) {
        //switcher.keyPressed(key.getKeyCode());
    }
    public void keyReleased(KeyEvent key) {
        //switcher.keyReleased(key.getKeyCode());
    }
}