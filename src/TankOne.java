import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;


public class TankOne {
    double x;
    double y;
    double a = 0;
    double speed = 2;
    int tmpangle = 0;
    private Game game;
    private Map map;
    private BufferedImage sprite, fireSprite;
    boolean moveforward, movebackward, turnLeft, turnRight;
    private int shots = 5;

    public TankOne(Game game, Map map) {
        this.game= game;
        this.map = map;

        x = map.getPlayer1x();
        y = map.getPlayer1y();
        try{
            sprite = ImageIO.read(this.getClass().getResourceAsStream("Assets" +File.separator+"PlayerOne.png"));
            fireSprite = ImageIO.read(this.getClass().getResourceAsStream("Assets" + File.separator + "Explosion.png"));
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
        if (turnRight)
            turnRight();
        if (turnLeft)
            turnLeft();
        if (moveforward)
            moveforward();
        else if (movebackward)
            movebackward();
    }

    public void moveforward() {
        int projx = (int)Math.round((x + Math.sin(a)))/31;
        int projy = (int)Math.round((y - Math.cos(a)))/31;
        if( !map.isTileSolid(projx, projy)){
            x += Math.sin(a) * speed;
            y -= Math.cos(a) * speed;
        }
    }

    public void movebackward() {
        int projx = (int)Math.round((x - Math.sin(a)))/31;
        int projy = (int)Math.round((y + Math.cos(a)))/31;
        if( !map.isTileSolid(projx, projy)) {
            x -= Math.sin(a) * speed;
            y += Math.cos(a) * speed;
        }
    }
    public void draw(Graphics2D g) {
        g.drawImage( rotate(a, sprite), (int)Math.round(x), (int)Math.round(y), null);
        if(game.checkTankOne()) {
            g.drawImage(rotate(a, fireSprite), (int) Math.round(x), (int) Math.round(y), null);
        }
    }

    public BufferedImage rotate( double degree , BufferedImage i) {
        double rotationRequired = degree;
        double locationX = i.getWidth() / 2;
        double locationY = i.getHeight() / 2;
        AffineTransform tx = new AffineTransform();

        tx.scale(.25,.25);
        tx.rotate(

                rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        return op.filter(i, null);
    }
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            turnLeft = false;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            turnRight = false;
        if (e.getKeyCode() == KeyEvent.VK_UP)
            moveforward = false;
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            movebackward = false;

    }

    private void turnLeft(){
        tmpangle -= 3.6;
    }

    private void turnRight(){
        tmpangle += 3.6;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            turnLeft = true;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            turnRight = true;
        if (e.getKeyCode() == KeyEvent.VK_UP)
            moveforward = true;
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            movebackward = true;
        if(e.getKeyCode() == KeyEvent.VK_M) {
            game.m.add(new Missile(this.game, this.x, this.y , a));
        }
    }
}

