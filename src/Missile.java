import java.awt.*;
import javax.swing.*;

public class Missile {
        double x = 0;
        double y = 0;
        double xa = 0;
        double ya = 0;
        double speed = 2.5;
        private Game game;


        public Missile(Game game, double xSpawn, double ySpawn, double xdir, double ydir) {
            x = xSpawn;
            y = ySpawn;
            xa = xdir * speed;
            ya = ydir * speed;
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
            g.fillOval((int)x,(int)y, 10, 10);
        }
    }
