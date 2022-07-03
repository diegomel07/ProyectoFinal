package Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.GamePanel;
import Game.KeyHandler;

public class Player extends Entity{
	
	KeyHandler keyH;
	
	public final int screenX, screenY;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
	
		solidArea = new Rectangle(8, 16, 15, 18);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		worldX = gp.tileSize * 10;
		worldY = gp.tileSize * 8;
		
		speed = 4;
		direction = "este";
	}
	
	public void getPlayerImage() {
		try {			
			norte0 = ImageIO.read(getClass().getResourceAsStream("/player/norte_0.png"));
			norte1 = ImageIO.read(getClass().getResourceAsStream("/player/norte_1.png"));
			norte2 = ImageIO.read(getClass().getResourceAsStream("/player/norte_2.png"));
			sur0 = ImageIO.read(getClass().getResourceAsStream("/player/sur_0.png"));
			sur1 = ImageIO.read(getClass().getResourceAsStream("/player/sur_1.png"));
			sur2 = ImageIO.read(getClass().getResourceAsStream("/player/sur_2.png"));
			oeste0 = ImageIO.read(getClass().getResourceAsStream("/player/oeste_0.png"));
			oeste1 = ImageIO.read(getClass().getResourceAsStream("/player/oeste_1.png"));
			oeste2 = ImageIO.read(getClass().getResourceAsStream("/player/oeste_2.png"));
			este0 = ImageIO.read(getClass().getResourceAsStream("/player/este_0.png"));
			este1 = ImageIO.read(getClass().getResourceAsStream("/player/este_1.png"));
			este2 = ImageIO.read(getClass().getResourceAsStream("/player/este_2.png"));
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		norte0 = setup("/player/norte_0.png");
		norte1 = setup("/player/norte_1.png");
		norte2 = setup("/player/norte_2.png");
		sur0 = setup("/player/sur_0.png");
		sur1 = setup("/player/sur_1.png");
		sur2 = setup("/player/sur_2.png");
		oeste0 = setup("/player/oeste_0.png");
		oeste1 = setup("/player/oeste_1.png");
		oeste2 = setup("/player/oeste_2.png");
		este0 = setup("/player/este_0.png");
		este1 = setup("/player/este_1.png");
		este2 = setup("/player/este_2.png");
		
	}
	
	public void update() {
		
		if (keyH.upPressed == true || keyH.downPressed == true || 
				keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {
			
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
			
			// Check Tile Collision depends on map
			collisionOn = false;
			if (gp.currentTangMap == 0) {
				gp.cPrimerMapaChecker.checkTile(this);
			}
			else if(gp.currentTangMap == 1) {
				gp.cCostaChecker.checkTile(this);
			}
			else if (gp.currentTangMap == 2) {
				gp.cSelvaChecker.checkTile(this);
			}
			else if (gp.currentTangMap == 2.1f) {
				gp.cSelvaCheckerCasa.checkTile(this);
			}
			
			
			/*
			// Check object Collision
			int objectIndex = gp.cSelvaChecker.checkObject(this, true);
			pickUpObject(objectIndex);
			
			// Check NPC collision
			int npcIndex = gp.cSelvaChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			*/
			
			// Check event
			gp.eHandler.checkEvent();
			
			
			if (collisionOn == false && keyH.enterPressed == false) {
				switch(direction) {
				case "norte": worldY -= speed; break;
				case "sur": worldY += speed; break;
				case "oeste": worldX -= speed; break;
				case "este": worldX += speed; break;
				}
			}
			
			gp.keyH.enterPressed = false;
			
			spriteCounter++;
			if (spriteCounter > 6) {
				if (spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 3;
				} else if(spriteNum == 3) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		} else {
			spriteNum = 1;
		}
		
		
	}
	
	public void pickUpObject(int i) {
		
		if (i != 999) {
			
		}
		
	}
	
	public void interactNPC(int i) {
		if (i != 999) {
			if (gp.keyH.enterPressed == true) {
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}	
		}
		gp.keyH.enterPressed = false;
	}
	
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(direction) {
		case "norte":
			if (spriteNum == 1) {
				image = norte0;
			}
			if (spriteNum == 2) {
				image = norte1;
			}
			if (spriteNum == 3) {
				image = norte2;
			}
			break;
			
			
		case "sur":
			if (spriteNum == 1) {
				image = sur0;
			}
			if (spriteNum == 2) {
				image = sur1;
			}
			if (spriteNum == 3) {
				image = sur2;
			}
			break;
			
			
		case "oeste":
			if (spriteNum == 1) {
				image = oeste0;
			}
			if (spriteNum == 2) {
				image = oeste1;
			}
			if (spriteNum == 3) {
				image = oeste2;
			}
			break;
			
			
		case "este":
			if (spriteNum == 1) {
				image = este0;
			}
			if (spriteNum == 2) {
				image = este1;
			}
			if (spriteNum == 3) {
				image = este2;
			}
			break;
			
		}
		
		g2.drawImage(image, screenX, screenY, null);
		
		// DEBUG
		/*
		g2.setColor(Color.red);
		g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
		*/
		
	}
	
}
