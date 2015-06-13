import java.awt.*;
import java.util.*;

public class Map {
    int x,y;
    char[][] map;
    
    try {	    
	    Scanner sc = new Scanner(new File("map.dat"));
	    int j=0;

	    while (sc.hasNext()) {
		String line = sc.nextLine();
		for (int i=0; i < maxX; i++) {
		    map[i][j] = line.charAt(i);
		}
		j++;
	    }
	}
	catch (Exception e) {}	
    }

	for (int i = 0; i < map.length())
}
