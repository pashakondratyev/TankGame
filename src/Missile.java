import java.awt.*;
import javax.swing.*;

public class Missile {
        int x = 0;
        int y = 0;
        int xa = 1;
        int ya = 1;
        private Game game;

        public Missile(Game game, int xSpawn, int ySpawn) {
            x = xSpawn;
            y = ySpawn;
            this.game= game;
        }

        void update() {
            if (x + xa < 0)
                xa = 1;
            if (x + xa > game.getWidth() - 30)
                xa = -1;
            if (y + ya < 0)
                ya = 1;
            if (y + ya > game.getHeight() - 30)
                ya = -1;

            x = x + xa;
            y = y + ya;
        }

        public void draw(Graphics2D g) {
            g.fillOval(x, y, 10, 10);
        }
    }
