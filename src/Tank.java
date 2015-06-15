import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.*;
import java.awt.geom.AffineTransform;
import javax.imageio.*;
import java.io.*;
import java.io.File;
import java.lang.Math;


public class Tank {
    double x = 0;
    double xa = 0;
    double y = 50;
    double ya = 0;
    double a = 0;
    double speed = 1.5;
    int tmpangle = 0;
    private Game game;
    private BufferedImage sprite, missile;
    boolean moveforward, movebackward;

    public Tank(Game game) {
        this.game= game;
        try{
            sprite = ImageIO.read(new File("Assets" + File.separator + "PlayerOne.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public void setA(int angle){
        a = Math.toRadians(angle);
    }

    public void update() {
        if (tmpangle > 360)
            tmpangle = 0;
        else if (tmpangle < 0)
            tmpangle = 360;

        setA(tmpangle);

        if (moveforward)
            moveforward();
        else if (movebackward)
            movebackward();
    }


    public void moveforward() {
        x += Math.sin(a) * speed;
        y -= Math.cos(a) * speed;
    }

    public void movebackward() {
        x -= Math.sin(a) * speed;
        y += Math.cos(a) * speed;
    }
    public void draw(Graphics2D g) {
        g.drawImage( rotate(a, sprite), (int)Math.round(x), (int)Math.round(y), null);
    }

    public BufferedImage rotate( double degree , BufferedImage i) {
        double rotationRequired = degree;
        double locationX = i.getWidth() / 2;
        double locationY = i.getHeight() / 2;
        AffineTransform tx = new AffineTransform();

        tx.scale(.5,.5);
        tx.rotate(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        return op.filter(i, null);
    }
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            tmpangle += 0;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            tmpangle += 0;
        if (e.getKeyCode() == KeyEvent.VK_UP)
            moveforward = false;
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            movebackward = false;

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            tmpangle -= 15;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            tmpangle += 15;
        if (e.getKeyCode() == KeyEvent.VK_UP)
            moveforward = true;
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            movebackward = true;
    }
}

