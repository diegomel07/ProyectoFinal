package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Game.GamePanel;

public class OBJ_CHEST extends SuperObject{
	
	GamePanel gp;
	
	public OBJ_CHEST(GamePanel gp) {
		
		name = "Chest";
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
