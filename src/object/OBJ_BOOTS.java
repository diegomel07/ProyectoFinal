package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Game.GamePanel;

public class OBJ_BOOTS extends SuperObject{
	
	GamePanel gp;
	
	public OBJ_BOOTS(GamePanel gp) {
		
		name = "Boots";
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
