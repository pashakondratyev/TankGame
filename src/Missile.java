import java.awt.*;
import javax.swing.*;

public class Missile {
        double x = 0;
        double y = 0;
        double xa = 0;
        double ya = 0;
        double speed = 2;
        private Game game;


        public Missile(Game game, double xSpawn, double ySpawn, double xdir, double ydir) {
            x = xSpawn + xdir * 32;
            y = ySpawn - ydir  * 32 ;
            xa = xdir * speed;
            ya = ydir * speed;
            this.game= game;
        }

        void update() {
            int projx = (int) Math.round((x + xa)) / 32;
            int projy = (int) Math.round((y - ya)) / 32;
            double cx = game.map.getTileMap()[projy][projx].getX();
            double cy = game.map.getTileMap()[projy][projx].getY();

            x += xa;
            y -= ya;
            if (!game.map.isTileSolid(projx, projy)) {
                x += xa;
                y -= ya;
            }
            if (game.map.isTileSolid(projx, projy)) {
                    if (Math.abs(xa) < Math.abs(cx - x)){
                        xa = -(xa * (cx - x)) / x;
                        ya = Math.abs(ya * (cy - y)) / y;
                    }
                    else if (Math.abs(ya) < Math.abs(cy - y)){
                        ya = -(ya * (cy - y)) / y;
                        xa = Math.abs(xa * (cx - x)) / x;
                    }
                    else if (x < cx) {//left
                        x += xa;
                        y += ya;
                        System.out.println("1");
                    }
                    else if (x > cx) {//right
                        x -= xa;
                        y -= ya;
                        System.out.println("2");
                    }
                    else if (y <= cy ) {
                        x += xa;
                        y += ya;
                        System.out.println("3");
                    }
                    else if (y >= cy ) {
                        x += xa;
                        y += ya;
                        System.out.println("4");
                    }

            }
        }



        public void draw(Graphics2D g) {
            g.fillOval((int)(x),(int)(y), 10, 10);
        }
    }
