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

    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    private void update() {
    }


    public void run(){
        init();
        long startTime;
        long elapsedTime;
        long waitTime;

        while(isRunning) {

            startTime = System.nanoTime();
            draw();
            update();
            drawToScreen();
            //checkDeaths();
            elapsedTime = System.nanoTime() - startTime;
            waitTime = targetTime - elapsedTime / 1000000;
            try {
                if (waitTime < 0)
                    waitTime = 0;
                Thread.sleep(waitTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void draw(){
        switcher.draw(g);
        if (! (menu)) {
            //player.draw(g);
        }
    }

    private void drawToScreen() {
        Graphics g = getGraphics();
        g.drawImage(image, 0, 0, HEIGHT, WIDTH, null);
        g.dispose();
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