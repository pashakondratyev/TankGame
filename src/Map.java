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
    private int Player1x, Player1y;
    private int Player2x, Player2y;

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
                    tiles[i][j] = new Tile( i, j, true);
                }
                else if (symbol == '#') { //sand
                    grid.drawImage(SpriteSand, sx, sy, null);
                    tiles[i][j] = new Tile( i, j, false);
                }
                else if (symbol == '1'){ //Player one
                    grid.drawImage(SpriteSand, sx, sy, null);
                    tiles[i][j] = new Tile( i, j, false);
                    Player1x = j * 32;
                    Player1y = i * 32;
                }
                else if (symbol == '2') { //Player one
                    grid.drawImage(SpriteSand, sx, sy, null);
                    tiles[i][j] = new Tile(i, j, false);
                    Player2x = j * 32;
                    Player2y = i * 32;
                }
            }
        }
    }
    public int getPlayer1x(){
        return Player1x;
    }
    public int getPlayer1y(){
        return Player1y;
    }
    public int getPlayer2x(){
        return Player2x;
    }
    public int getPlayer2y(){
        return Player2y;
    }
    public Tile[][] getTileMap(){
        return tiles;
    }

    public Boolean isTileSolid( int x, int y){
        return tiles[y][x].isSolid();
    }

    public char[][] getMap(){
        return map;
    }
}
       

	
       
    
