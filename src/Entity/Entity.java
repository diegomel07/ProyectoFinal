package Entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	
	// entity position in the world
	public int worldX, worldY;
	public int speed;
	
	public BufferedImage norteStand, norte0, norte1, norte2, surStand, sur0, sur1, sur2, oesteStand, oeste0, oeste1, oeste2, esteStand, este0, este1, este2;   
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public Rectangle solidArea;
	public boolean collisionOn = false;
	
}
