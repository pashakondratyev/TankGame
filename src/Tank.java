import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class Tank {
    int x = 0;
    int xa = 0;
    int y = 330;
    int ya = 0;
    private Game game;
    private BufferedImage sprite, missile;

    public Tank(Game game) {
        this.game= game;
        File img = new File("Assets/PlayerOne.png");
        try{
            sprite = ImageIO.read(img);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public void move() {
        if (x + xa > 0 && x + xa < game.getWidth()-30)
            x = x + xa;
        if (y + ya > 0 && y + ya < game.getHeight()-60)
            y = y + ya;
    }

    public void draw(Graphics2D g) {
        g.drawImage(sprite, x, y, null);
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT)
            xa = 0;
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN)
            ya = 0;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            xa = -1;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            xa = 1;
        if (e.getKeyCode() == KeyEvent.VK_UP)
            ya = -1;
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            ya = 1;


    }
}

