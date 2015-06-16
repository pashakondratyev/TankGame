import java.awt.*;
import javax.swing.*;

public class Missile {
        int x = 0;
        int y = 0;
        int xa = 1;
        int ya = 1;
        private Game game;

        public Missile(Game game, int xSpawn, int ySpawn, double xdir, double ydir) {
            x = xSpawn;
            y = ySpawn;
            xa = xdir;
            ya = ydir;
            this.game= game;
        }

        void update() {
            int projx = (int)Math.round((x + xa))/31;
            int projy = (int)Math.round((y - ya))/31;
            if( !game.map.isTileSolid(projx, projy)) {
                x += xa;
                y -= ya;
            }
            else {
                x -= xa;
                y += ya;
            }


        }

        public void draw(Graphics2D g) {
            g.fillOval(x, y, 10, 10);
        }
    }
