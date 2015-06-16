import java.awt.*;
import javax.swing.*;

public class Missile {
        double x = 0;
        double y = 0;
        double xa = 0;
        double ya = 0;
        double speed = 4;
        int bounces;
        private Game game;


        public Missile(Game game, double xSpawn, double ySpawn, double angle) {
            x = xSpawn + 36*Math.sin(angle);// + 32*Math.sin(angle);
            y = ySpawn - 36*Math.cos(angle) +8;// - 32*Math.cos(angle);
            xa = Math.sin(angle)* speed;
            ya = Math.cos(angle)* speed;
            this.game= game;
        }

        void update() {
            int projx = (int) (x + xa) / 32;
            int projy = (int) (y - ya) / 32;
            if (!game.map.isTileSolid(projx, projy)) {
                x += xa;
                y -= ya;
            }
            if (game.map.isTileSolid(projx, projy)) {
                bounces ++;
                if( game.map.isTileSolid(projx, (int)y/32) ){
                    xa = -xa;
                }
                if( game.map.isTileSolid((int)x/32, projy)){
                    ya = -ya;
                }
                x += xa;
                y -= ya;
            }
        }

        public int getBounces(){
            return bounces;
        }


        public void draw(Graphics2D g) {
            g.fillOval((int)(x),(int)(y), 10, 10);
        }
    }
