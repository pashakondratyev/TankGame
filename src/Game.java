import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;


@SuppressWarnings("serial")
public class Game extends JPanel implements Runnable, KeyListener{

    public static final int WIDTH = 720;
    public static final int HEIGHT = 480;

    int x = 0;
    int y = 0;
    Boolean isRunning;
    Graphics2D g;
    BufferedImage image;
    Thread thread;
    private int FPS = 60; // frames per second
    private long targetTime = 1000 / FPS;

    Missile m;
    Tank t;

    public Game(){
        super();

        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    private void move() {
        //m.move();
        t.move();
    }


    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    public void draw(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        m.draw(g);
        t.draw(g);
    }

    public void gameOver() {
        if (t.x == m.x && t.y == m.y) {
            JOptionPane.showMessageDialog(this, "GG", "GG", JOptionPane.YES_NO_OPTION);
            System.exit(ABORT);
        }
    }

    public void run(){
        init();
        long startTime;
        long elapsedTime;
        long waitTime;
        while(isRunning){
            startTime = System.nanoTime();
            move();
            draw(g);
            gameOver();
            drawToScreen();

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
    public void init(){
        isRunning = true;
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        t = new Tank(this);
        m = new Missile(this);
    }

    private void drawToScreen(){
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
        g2.dispose();
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        t.keyReleased(e);
    }


    public void keyPressed(KeyEvent e) {
        t.keyPressed(e);
        //if e.getKeyCode() == KeyEvent.VK_SPACE
    }

}


