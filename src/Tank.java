import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.*;
import java.awt.geom.AffineTransform;
import javax.imageio.*;
import java.io.*;
import java.io.File;
import java.lang.Math;


public class Tank {
    double x = 100;
    double xa = 0;
    double y = 330;
    double ya = 0;
    double theta = 0;
    private Game game;
    private BufferedImage sprite, missile;

    public Tank(Game game) {
        this.game= game;
        try{
            sprite = ImageIO.read(new File("Assets" + File.separator + "PlayerOne.png"));
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
        g.drawImage( rotate(theta, sprite), (int)Math.round(x), (int)Math.round(y), null);
    }

    public BufferedImage rotate( double degree , BufferedImage i) {
        double rotationRequired = Math.toRadians(degree);
        double locationX = i.getWidth() / 2;
        double locationY = i.getHeight() / 2;
        AffineTransform tx = new AffineTransform();

        tx.scale(.7,.7);
        tx.rotate(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        return op.filter(i, null);
    }
    public void keyReleased(KeyEvent e) {
        //if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT)
            //xa = 0;
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN)
            ya = 0;
            xa = 0;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            theta -= 10;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            theta += 10;
        if (e.getKeyCode() == KeyEvent.VK_UP)
            ya = Math.cos(Math.toRadians(theta));
            xa = Math.sin(Math.toRadians(theta));
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            ya = -Math.cos(Math.toRadians(theta));
            xa = -Math.sin(Math.toRadians(theta));
    }
}


