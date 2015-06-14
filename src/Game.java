import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


@SuppressWarnings("serial")
public class Game extends JPanel {

    int x = 0;
    int y = 0;


   Missile m = new Missile(this);
    Tank t = new Tank(this);

    public Game(){
    addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {
            }


            public void keyReleased(KeyEvent e) {
                t.keyReleased(e);
            }


            public void keyPressed(KeyEvent e) {
                t.keyPressed(e);
                //if e.getKeyCode() == KeyEvent.VK_SPACE
            }
        });
        setFocusable(true);
    }

    private void move() {
        m.move();
        t.move();
    }


    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        m.paint(g2d);
        t.paint(g2d);
    }

    public void gameOver() {
        if (t.x == m.x && t.y == m.y) {
            JOptionPane.showMessageDialog(this, "GG", "GG", JOptionPane.YES_NO_OPTION);
            System.exit(ABORT);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("TankGame");
        Game game = new Game();
        frame.add(game);
        frame.setSize(720, 480);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
            game.move();
            game.repaint();
            game.gameOver();
            Thread.sleep(10);
        }
    }
}


