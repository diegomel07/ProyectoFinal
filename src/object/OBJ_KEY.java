package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Game.GamePanel;

public class OBJ_KEY extends SuperObject{
	
	GamePanel gp;
	
	public OBJ_KEY(GamePanel gp) {
		
		name = "Key";
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
