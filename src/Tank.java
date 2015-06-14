import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.*;
import java.awt.geom.AffineTransform;
import javax.imageio.*;
import java.io.*;
import java.io.File;
import java.lang.Math;


public class Tank {
    int x = 0;
    int xa = 0;
    int y = 330;
    int ya = 0;
    double theta = 0;
    private Game game;
    private BufferedImage sprite, missile;

    public Tank(Game game) {
        this.game= game;
        try{
            sprite = ImageIO.read(new File("Assets/PlayerOne.png"));
        }
        catch (Exception e){
            System.out.println("AY");
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
        double rotationRequired = Math.toRadians(theta);
        double locationX = sprite.getWidth() / 2;
        double locationY = sprite.getHeight() / 2;

        AffineTransform tx = new AffineTransform();
        AffineTransformOp op = new AffineTransformOp(tx.getRotateInstance(rotationRequired, locationX, locationY)
                , AffineTransformOp.TYPE_BILINEAR);
        g.drawImage(op.filter(sprite, null), x, y, null);
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT)
            xa = 0;
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN)
            ya = 0;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            theta -= .01;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            theta += .01;
        if (e.getKeyCode() == KeyEvent.VK_UP)
            xa = (int) Math.cos(Math.toRadians(theta));
            ya = (int) Math.sin(Math.toRadians(theta));
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            xa = -(int) Math.cos(Math.toRadians(theta));
            ya = -(int) Math.sin(Math.toRadians(theta));
    }
}
/*
direction.x = (float) Math.cos(Math.toRadians(rotation));
direction.y = (float) Math.sin(Math.toRadians(rotation));
if (direction.length() > 0) {
    direction = direction.normalise();
}
 */

