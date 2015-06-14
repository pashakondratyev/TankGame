import javax.swing.JFrame;
import java.awt.*;

public class Window{
    public static void main(String[] args){
        JFrame game = new JFrame("TankGame");
        game.setContentPane(new Game());
        game.setVisible(true);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setResizable(false);
        game.pack();

    }
}
