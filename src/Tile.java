import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.imageio.*;

public class Tile {
    private boolean type = false;
    private double cx, cy, size;

    public Tile(int x, int y, boolean t){
        type = t;
        cx = (double)(x +16 );
        cy = (double)(y +16 );
    }

    public double getX() { return cx; }
    public double getY() { return cy; }
    public boolean isSolid() { return type; }



    public String toString() {
        return "" + cx + " " + cy;
    }
}
