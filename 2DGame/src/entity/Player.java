package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler; 

public class Player extends Entity{
	
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		setDefaultValues();
		getPlayerImage();
		
	}
	public void setDefaultValues() {
		
		worldX = 1000;
		worldY = 1000;
		speed = 4;
		direction = "sur";
	}
	
	public void getPlayerImage() {
		
		try {
			
			este0 = ImageIO.read(getClass().getResourceAsStream("/player/este_0.png"));
			este1 = ImageIO.read(getClass().getResourceAsStream("/player/este_1.png"));
			este2 = ImageIO.read(getClass().getResourceAsStream("/player/este_2.png"));
			norte0 = ImageIO.read(getClass().getResourceAsStream("/player/norte_0.png"));
			norte1 = ImageIO.read(getClass().getResourceAsStream("/player/norte_1.png"));
			norte2 = ImageIO.read(getClass().getResourceAsStream("/player/norte_2.png"));
			oeste0 = ImageIO.read(getClass().getResourceAsStream("/player/oeste_0.png"));
			oeste1 = ImageIO.read(getClass().getResourceAsStream("/player/oeste_1.png"));
			oeste2 = ImageIO.read(getClass().getResourceAsStream("/player/oeste_2.png"));
			sur0 = ImageIO.read(getClass().getResourceAsStream("/player/sur_0.png"));
			sur1 = ImageIO.read(getClass().getResourceAsStream("/player/sur_1.png"));
			sur2 = ImageIO.read(getClass().getResourceAsStream("/player/sur_3.png"));
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	public void update() {
		
		if(keyH.upPressed == true || keyH.downPressed == true || 
				keyH.leftPressed == true || keyH.rightPressed == true) {
			if(keyH.upPressed == true) {
				direction = "norte";
				worldY -= speed;
			}
			else if(keyH.downPressed == true) {
				direction = "sur";
				worldY += speed;
			}
			else if(keyH.leftPressed == true) {
				direction = "oeste";
				worldX -= speed;
			}
			else if(keyH.rightPressed == true) {
				direction = "este";
				worldX += speed;
			}
			
			spriteCounter++;
			if(spriteCounter > 13) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				else if(spriteNum ==0) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		} else if(keyH.upPressed == false || keyH.downPressed == false || 
				keyH.leftPressed == false || keyH.rightPressed == false) {
			spriteNum = 0;
		}

		
	}
	public void draw(Graphics2D g2) {
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image = null;
		
		switch(direction) {
		case "norte":
			if(spriteNum == 0) {
				image = norte0;
			}
			if(spriteNum == 1) {
				image = norte1;
			}
			if(spriteNum == 2) {
				image = norte2;
			}
			break;
		case "sur":
			if(spriteNum == 0) {
				image = sur0;
			}
			if(spriteNum == 1) {
				image = sur1;
			}
			if(spriteNum == 2) {
				image = sur2;
			}
			break;
		case "oeste":
			if(spriteNum == 0) {
				image = oeste0;
			}
			if(spriteNum == 1) {
				image = oeste1;
			}
			if(spriteNum == 2) {
				image = oeste2;
			}
			break;
		case "este":
			if(spriteNum == 0) {
				image = este0;
			}
			if(spriteNum == 1) {
				image = este1;
			}
			if(spriteNum == 2) {
				image = este2;
			}
			break;
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize, 24*2, null);
		
	}

}














