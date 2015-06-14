import java.awt.*;
import java.util.*;
import java.io.*;

public class Map {
    int x, y;
    char[][] map;
    Tile[][] tiles;
    int mapHeight, mapWidth;
    Scanner sc;
    ArrayList<String> Maps = new ArrayList<String>();


    public Map(PlayerOne p, PlayerTwo q, Graphics2D g) {

        try {
            sc = new Scanner(new File("map.dat"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        mapWidth = Integer.parseInt(sc.nextLine());
        mapHeight = Integer.parseInt(sc.nextLine());

        map = new char[mapHeight][mapWidth];
        tiles = new Tile[mapHeight][mapWidth];

        int j = 0;
        while (sc.hasNext()) {
            String line = sc.nextLine();
            for (int i = 0; i < mapWidth; i++) {
                map[i][j] = line.charAt(i);
            }
            j++;
        }
    }
    //for (int i = 0; i < map.length())

    public void drawTiles(Graphics2D g){
        x = 0;
        y = 0;
        int count = 0;
        int sprite = 0;
        boolean solid = false;
        for(int i = 0; i < mapHeight; i++){
            x = 0;
            for(int j = 0; j < mapWidth; j++){
                int type = map[i][j];
                if(type == #){
                    g.drawImage(sprites[3], x, y, null);
                    solid = false;
                }
                else if(type == $){
                    g.drawImage(sprites[0], x, y, null);
                    solid = true;
                }
                x += tileSize; //move column
            }
            y += tileSize; //moves over row when 1 is finished (now it should start at 0,0)
        }
    }

}
