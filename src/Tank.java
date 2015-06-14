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
    double dtheta = 5;
    double speed = 2;
    private Game game;
    private BufferedImage sprite, missile;

    Boolean MoveUp, MoveDown;

    public Tank(Game game) {
        this.game= game;
        MoveDown = false;
        MoveUp = false;
        try{
            sprite = ImageIO.read(new File("Assets" + File.separator + "PlayerOne.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public void update() {
        if (x + xa > 0 && x + xa < game.getWidth()-30 && MoveUp)
            x = x + xa;
        if (y + ya > 0 && y + ya < game.getHeight()-60 && MoveUp)
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

        tx.scale(.5,.5);
        tx.rotate(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        return op.filter(i, null);
    }
    public void keyReleased(KeyEvent e) {
        //if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT)

        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN)
            MoveUp = false;
            MoveDown = false;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            MoveLeft();
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            MoveRight();
        if (e.getKeyCode() == KeyEvent.VK_UP)
            MoveUp();
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            MoveDown();
    }

    public void MoveUp(){
        MoveUp = true;
    }
    public void MoveDown(){
        MoveDown = true;
    }
    public void MoveRight(){
        theta += dtheta;
        ya = Math.cos(theta);
        xa = -Math.sin(theta);
    }
    public void MoveLeft(){
        theta -= dtheta;
        ya = Math.cos(theta);
        xa = -Math.sin(theta);
    }
}


/*
if(angle < 90){
dx = Math.cos(angle);
dy = -Math.sin(angle)*dpm;
}else if(angle < 180){
dx = -Math.sin(angle-90)*dpm;
dy = -Math.cos(angle-90)*dpm;
}else if(angle < 270){
dx = -Math.cos(angle-180)*dpm;
dy = Math.sin(angle-180)*dpm;
}else{
dx = Math.sin(angle-270)*dpm;
dx = Math.cos(angle-270)*dpm;
}
 */