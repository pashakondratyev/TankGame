import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class TankGame extends JPanel implements Runnable, KeyListener{

    // game window size
    public static final int WIDTH = 720;
    public static final int HEIGHT = 480;


    private PlayerOne PlayerOne;
    private PlayerTwo PlayerTwo;
    private Thread thread;
    private Boolean bothAlive, menu;
    private int FPS = 60; // frames per second
    private long targetTime = 1000 / FPS;

    private MapSwitcher switcher;

    public TankGame(){
        super();
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    public void init(){

    }

    public void run(){
        init();

    }
}