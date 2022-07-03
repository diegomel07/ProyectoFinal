package Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Game.GamePanel;
import Game.UtilityTool;

public abstract class Entity {
	
	GamePanel gp;
	
	// entity position in the world
	public int worldX, worldY, pastWorldX, pastWorldY;
	public int speed;
	
	public BufferedImage norte0, norte1, norte2, sur0, sur1, sur2, oeste0, oeste1, oeste2, este0, este1, este2;   
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	public int actionLockCounter = 0;
	String dialogues[] = new String[20];
	int dialogueIndex = 0;
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setAction() {}
	public void speak() {
		if (dialogues[dialogueIndex] == null) {
			dialogueIndex = 0; 
		}
		gp.ui.currentDialogue = dialogues[dialogueIndex];
		dialogueIndex++;
		
		switch(gp.player.direction) {
		case "norte":
			direction = "sur";
			break;
		case "sur":
			direction = "norte";
			break;
		case "este":
			direction = "oeste";
			break;
		case "oeste":
			direction = "este";
			break;
			
		}
	}
	
	public void update() {
		setAction();
		collisionOn = false;
		//gp.cSelvaChecker.checkTile(this);
		//gp.cSelvaChecker.checkPlayer(this);
		//gp.cSelvaChecker.checkObject(this,false);
		
		if (collisionOn == false) {
			switch(direction) {
			case "norte": worldY -= speed; break;
			case "sur": worldY += speed; break;
			case "oeste": worldX -= speed; break;
			case "este": worldX += speed; break;
			}
		}
		
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
		} else {
			spriteNum = 1;
		}
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
				worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
				worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
				worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
		
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
	
	public BufferedImage setup(String imagePath) {
		
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath));
			image = uTool.scaleImage(image, gp.tileSize, 20*gp.scale);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return image;
		
	}
	
}
