package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Game.GamePanel;

public class OBJ_DOOR extends SuperObject{
	
	GamePanel gp;
	
	public OBJ_DOOR(GamePanel gp) {
		
		name = "Door";
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		collision = true;
		
	}
}
