import javax.swing.JFrame;
public class Window {
    public static void Main(String[] args){
        JFrame game = new JFrame("TankGame");
        game.setContentPane(new TankGame());
        game.setVisible(true);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setResizable(false);
        game.pack();
    }
}