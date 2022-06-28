package Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.GamePanel;
import Game.KeyHandler;

public class Player extends Entity{
	
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX, screenY;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle(5, 10, 22, 22);
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 2;
		direction = "este";
	}
	
	public void getPlayerImage() {
		try {
			
			/*
			norteStand = ImageIO.read(getClass().getResourceAsStream("/player/robot/Norte_00.png"));
			norte0 = ImageIO.read(getClass().getResourceAsStream("/player/robot/Norte_01.png"));
			norte1 = ImageIO.read(getClass().getResourceAsStream("/player/robot/Norte_02.png"));
			norte2 = ImageIO.read(getClass().getResourceAsStream("/player/robot/Norte_03.png"));
			surStand = ImageIO.read(getClass().getResourceAsStream("/player/robot/Sur_00.png"));
			sur0 = ImageIO.read(getClass().getResourceAsStream("/player/robot/Sur_01.png"));
			sur1 = ImageIO.read(getClass().getResourceAsStream("/player/robot/Sur_02.png"));
			sur2 = ImageIO.read(getClass().getResourceAsStream("/player/robot/Sur_03.png"));
			oesteStand = ImageIO.read(getClass().getResourceAsStream("/player/robot/Oeste_00.png"));
			oeste0 = ImageIO.read(getClass().getResourceAsStream("/player/robot/Oeste_01.png"));
			oeste1 = ImageIO.read(getClass().getResourceAsStream("/player/robot/Oeste_02.png"));
			oeste2 = ImageIO.read(getClass().getResourceAsStream("/player/robot/Oeste_03.png"));
			esteStand = ImageIO.read(getClass().getResourceAsStream("/player/robot/Este_00.png"));
			este0 = ImageIO.read(getClass().getResourceAsStream("/player/robot/Este_01.png"));
			este1 = ImageIO.read(getClass().getResourceAsStream("/player/robot/Este_02.png"));
			este2 = ImageIO.read(getClass().getResourceAsStream("/player/robot/Este_03.png"));
			*/
			
			
			norteStand = ImageIO.read(getClass().getResourceAsStream("/player/stand_norte.png"));
			norte0 = ImageIO.read(getClass().getResourceAsStream("/player/norte_0.png"));
			norte1 = ImageIO.read(getClass().getResourceAsStream("/player/norte_1.png"));
			norte2 = ImageIO.read(getClass().getResourceAsStream("/player/norte_2.png"));
			surStand = ImageIO.read(getClass().getResourceAsStream("/player/stand_sur.png"));
			sur0 = ImageIO.read(getClass().getResourceAsStream("/player/sur_0.png"));
			sur1 = ImageIO.read(getClass().getResourceAsStream("/player/sur_1.png"));
			sur2 = ImageIO.read(getClass().getResourceAsStream("/player/sur_2.png"));
			oesteStand = ImageIO.read(getClass().getResourceAsStream("/player/stand_oeste.png"));
			oeste0 = ImageIO.read(getClass().getResourceAsStream("/player/oeste_0.png"));
			oeste1 = ImageIO.read(getClass().getResourceAsStream("/player/oeste_1.png"));
			oeste2 = ImageIO.read(getClass().getResourceAsStream("/player/oeste_2.png"));
			esteStand = ImageIO.read(getClass().getResourceAsStream("/player/stand_este.png"));
			este0 = ImageIO.read(getClass().getResourceAsStream("/player/este_0.png"));
			este1 = ImageIO.read(getClass().getResourceAsStream("/player/este_1.png"));
			este2 = ImageIO.read(getClass().getResourceAsStream("/player/este_2.png"));
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		
		if (keyH.upPressed == true || keyH.downPressed == true || 
				keyH.leftPressed == true || keyH.rightPressed == true) {
			if (keyH.upPressed == true) {
				direction = "norte";
			}
			else if (keyH.downPressed == true) {
				direction = "sur";
			}
			else if (keyH.leftPressed == true) {
				direction = "oeste";				
			}
			else if (keyH.rightPressed == true) {
				direction = "este";
			}
			
			// Check Tile Collision
			collisionOn = false;
			gp.cChecker.checkTile(this);	
			
			if (collisionOn == false) {
				switch(direction) {
				case "norte": worldY -= speed; break;
				case "sur": worldY += speed; break;
				case "oeste": worldX -= speed; break;
				case "este": worldX += speed; break;
				}
			}
			
			spriteCounter++;
			if (spriteCounter > 10) {
				if (spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 3;
				} else if (spriteNum == 3) {
					spriteNum = 4;
				} else if (spriteNum == 4) {
					spriteNum = 2;
				}
				spriteCounter = 0;
			}
		} else {
			spriteNum = 1;
		}
		
		
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(direction) {
		case "norte":
			if (spriteNum == 1) {
				image = norteStand;
			}
			if (spriteNum == 2) {
				image = norte0;
			}
			if (spriteNum == 3) {
				image = norte1;
			}
			if (spriteNum == 4) {
				image = norte2;
			}
			break;
			
			
		case "sur":
			if (spriteNum == 1) {
				image = surStand;
			}
			if (spriteNum == 2) {
				image = sur0;
			}
			if (spriteNum == 3) {
				image = sur1;
			}
			if (spriteNum == 4) {
				image = sur2;
			}
			break;
			
			
		case "oeste":
			if (spriteNum == 1) {
				image = oesteStand;
			}
			if (spriteNum == 2) {
				image = oeste0;
			}
			if (spriteNum == 3) {
				image = oeste1;
			}
			if (spriteNum == 4) {
				image = oeste2;
			}
			break;
			
			
		case "este":
			if (spriteNum == 1) {
				image = esteStand;
			}
			if (spriteNum == 2) {
				image = este0;
			}
			if (spriteNum == 3) {
				image = este1;
			}
			if (spriteNum == 4) {
				image = este2;
			}
			break;
			
			
		}
		
		g2.drawImage(image, screenX, screenY, gp.tileSize, 20*gp.scale, null);
		
	}
	
}
