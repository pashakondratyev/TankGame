import java.awt.*;
import javax.swing.*;

public class Missile {
        double x = 0;
        double y = 0;
        double xa = 0;
        double ya = 0;
        double speed = 3;
        int bounces;
        private Game game;


        public Missile(Game game, double xSpawn, double ySpawn, double xdir, double ydir) {
            x = xSpawn + xdir * 32;
            y = ySpawn - ydir  * 32 ;
            xa = xdir * speed;
            ya = ydir * speed;
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
