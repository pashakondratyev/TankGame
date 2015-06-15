import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.imageio.*;
import java.io.File;

public class Map{
    private Scanner sc;
    private int tileSize;
    private int mapWidth;
    private int mapHeight;
    private char[][] map;
    private Tile[][] tiles;
    private int x;
    private int y;
    boolean nextLevel;
    boolean solid = false;
    private BufferedImage SpriteSand, SpriteWall;

    public Map(){

        FileInputStream fis;
        ObjectInputStream ois;
        try {
            sc = new Scanner(new File("Map.dat"));
            //fis = new FileInputStream("Map.dat");
            //ois = new ObjectInputStream(fis);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        try{
            SpriteSand = ImageIO.read(new File("Assets/Sand.png"));
            SpriteSand = scaleDown(SpriteSand);
            SpriteWall = ImageIO.read(new File("Assets/Wall.png"));
            SpriteWall = scaleDown(SpriteWall);
        }
        catch (IOException e){
            e.printStackTrace();
        }



        mapWidth = 32;
        mapHeight = 20;
        map = new char[mapHeight][mapWidth];
        tiles = new Tile[mapHeight][mapWidth];

        String toSplit = "\\s+";
        for(int i = 0; i < mapHeight; i++){
            String line = sc.nextLine();
            char[] elements = line.toCharArray();
            for(int j = 0; j < mapWidth; j++){
                map[i][j] = elements[j];
            }
        }
    }

    public BufferedImage scaleDown(BufferedImage i) {
        AffineTransform tx = new AffineTransform();
        tx.scale(.25,.25);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        return op.filter(i, null);
    }

    public void drawTiles(Graphics2D grid) {
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                char symbol = map[i][j];
                int sx = j * 32;
                int sy = i * 32;
                if (symbol == '$') { //wall
                    grid.drawImage(SpriteWall, sx, sy, null);
                    solid = true;
                }
                else if (symbol == '#') { //sand
                    grid.drawImage(SpriteSand, sx, sy, null);
                    solid = false;
                }
                else {
                    grid.drawImage(SpriteSand, sx, sy, null);
                    solid = false;
                }

            }
        }
    }

    public char[][] getMap(){
        return map;
    }
}
       

	
       
    
