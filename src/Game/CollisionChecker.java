package Game;

import Entity.Entity;
import tile.TileManager;

public class CollisionChecker {
	
	GamePanel gp;
	TileManager tileM;

	public CollisionChecker(GamePanel gp, TileManager tileM) {
		this.gp = gp;
		this.tileM = tileM;
	}
	
	public void checkTile(Entity entity) {
		
		int entityLeftWorldX = (int) (entity.worldX + entity.solidArea.x);
		int entityRightWorldX = (int) (entity.worldX + entity.solidArea.x + entity.solidArea.width);
		int entityTopWorldY = (int) (entity.worldY + entity.solidArea.y);
		int entityBottomWorldY = (int) (entity.worldY + entity.solidArea.y + entity.solidArea.height);
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(entity.direction) {
		case "norte":
			entityTopRow = (int) ((entityTopWorldY - entity.speed) / gp.tileSize);
			tileNum1 = tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = tileM.mapTileNum[entityRightCol][entityTopRow];
			
			if(tileM.tile[tileNum1].collision == true || tileM.tile[tileNum2].collision == true) {
				System.out.println("Hola?");
				entity.collisionOn = true;
			}
			
			break;
		case "sur":
			entityBottomRow = (int) ((entityBottomWorldY + entity.speed) / gp.tileSize);
			tileNum1 = tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = tileM.mapTileNum[entityRightCol][entityBottomRow];
			
			if(tileM.tile[tileNum1].collision == true || tileM.tile[tileNum2].collision == true) {
				System.out.println("Hola?");
				entity.collisionOn = true;
			}
			
			break;
		case "oeste":
			entityLeftCol = (int) ((entityLeftWorldX - entity.speed) / gp.tileSize);
			tileNum1 = tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = tileM.mapTileNum[entityRightCol][entityTopRow];
			
			if(tileM.tile[tileNum1].collision == true || tileM.tile[tileNum2].collision == true) {
				System.out.println("Hola?");
				entity.collisionOn = true;
			}
			
			break;
		case "este":
			entityRightCol = (int) ((entityRightWorldX + entity.speed) / gp.tileSize);
			tileNum1 = tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = tileM.mapTileNum[entityRightCol][entityBottomRow];
			
			if(tileM.tile[tileNum1].collision == true || tileM.tile[tileNum2].collision == true) {
				System.out.println("Hola?");
				entity.collisionOn = true;
			}
			
			break;
		}
		
	}
	
	public int checkObject(Entity entity, boolean player) {
		
		int index = 999;
		
		for (int i = 0; i < gp.obj.length; i++) {
			if(gp.obj[i] != null) {
				// Get entity's solid area positon
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				// Get the object's solid area position 
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
				
				switch(entity.direction) {
				case "norte":
					entity.solidArea.y -= entity.speed;
					if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if (player == true) {
							index = i;
						}
					}
					break;
					
				case "sur":
					entity.solidArea.y += entity.speed;
					if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if (player == true) {
							index = i;
						}
					}
					break;
					
				case "oeste":
					entity.solidArea.x -= entity.speed;
					if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if (player == true) {
							index = i;
						}
					}
					break;
					
				case "este":
					entity.solidArea.x += entity.speed;
					if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if (player == true) {
							index = i;
						}
					}
					break;
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
			}
		}
		
		return index;
	}
	
	// NPC COLLISION
	public int checkEntity(Entity entity, Entity[] target) {
		int index = 999;
		
		for (int i = 0; i < target.length; i++) {
			if(target[i] != null) {
				// Get entity's solid area positon
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				// Get the object's solid area position 
				target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
				target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;
				
				switch(entity.direction) {
				case "norte":
					entity.solidArea.y -= entity.speed;
					if (entity.solidArea.intersects(target[i].solidArea)) {
						entity.collisionOn = true;
						index = i;
					}
					break;
					
				case "sur":
					entity.solidArea.y += entity.speed;
					if (entity.solidArea.intersects(target[i].solidArea)) {
						entity.collisionOn = true;
						index = i;
					}
					break;
					
				case "oeste":
					entity.solidArea.x -= entity.speed;
					if (entity.solidArea.intersects(target[i].solidArea)) {
						entity.collisionOn = true;
						index = i;
					}
					break;
					
				case "este":
					entity.solidArea.x += entity.speed;
					if (entity.solidArea.intersects(target[i].solidArea)) {
						entity.collisionOn = true;
						index = i;
					}
					break;
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				target[i].solidArea.x = target[i].solidAreaDefaultX;
				target[i].solidArea.y = target[i].solidAreaDefaultY;
			}
		}
		
		return index;
	}
	
	public void checkPlayer(Entity entity) {
		if(gp.player != null) {
			// Get entity's solid area positon
			entity.solidArea.x = entity.worldX + entity.solidArea.x;
			entity.solidArea.y = entity.worldY + entity.solidArea.y;
			
			// Get the object's solid area position 
			gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
			gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
			
			switch(entity.direction) {
			case "norte":
				entity.solidArea.y -= entity.speed;
				if (entity.solidArea.intersects(gp.player.solidArea)) {
					entity.collisionOn = true;
				
				}
				break;
				
			case "sur":
				entity.solidArea.y += entity.speed;
				if (entity.solidArea.intersects(gp.player.solidArea)) {
					entity.collisionOn = true;

				}
				break;
				
			case "oeste":
				entity.solidArea.x -= entity.speed;
				if (entity.solidArea.intersects(gp.player.solidArea)) {
					entity.collisionOn = true;
				}
				break;
				
			case "este":
				entity.solidArea.x += entity.speed;
				if (entity.solidArea.intersects(gp.player.solidArea)) {
					entity.collisionOn = true;
				}
				break;
			}
			entity.solidArea.x = entity.solidAreaDefaultX;
			entity.solidArea.y = entity.solidAreaDefaultY;
			gp.player.solidArea.x = gp.player.solidAreaDefaultX;
			gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		}
	}
	
}
