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
            SpriteWall = ImageIO.read(new File("Assets/Wall.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }


        mapWidth = 36;
        mapHeight = 24;
        map = new char[mapHeight][mapWidth];
        tiles = new Tile[mapHeight][mapWidth];

        String toSplit = "\\s+";
        for(int i = 0; i < mapHeight; i++){
            String line = sc.nextLine();
            char[] elements = line.toCharArray();
            System.out.print(elements[0]);
            for(int j = 0; j < mapWidth; j++){
                map[i][j] = elements[j];
            }
        }
    }


    public void drawTiles(Graphics2D grid) {
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                char symbol = map[i][j];
                if (symbol == '$') { //wall
                    grid.drawImage(SpriteWall, x, y, null);
                    solid = true;
                }
                else if (symbol == '#') { //sand
                    grid.drawImage(SpriteSand, x, y, null);
                    solid = false;
                }
                else {
                    grid.drawImage(SpriteSand, x, y, null);
                    solid = false;
                }

            }
        }
    }

    public char[][] getMap(){
        return map;
    }
}
       

	
       
    
