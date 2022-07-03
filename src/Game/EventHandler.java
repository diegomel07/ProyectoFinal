package Game;

import java.awt.Rectangle;

public class EventHandler {
	
	GamePanel gp;
	Rectangle eventRect;
	int eventRectDefaultX, eventRectDefaultY;
	
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new Rectangle();
		eventRect.x = 0;
		eventRect.y = 0;
		eventRect.width = 48;
		eventRect.height = 48;
		eventRectDefaultX = eventRect.x;
		eventRectDefaultY = eventRect.y;
		
		
	}
	
	public void checkEvent() {
		
		// EVENTOS PRIMER MAPA
		if (gp.currentIntangMap == 0) {
			if (hit(16, 4, "este")) {
				teleport(1, 13, 3, "oeste");
			}
		}
		
		// EVENTOS COSTA
		if (gp.currentIntangMap == 1) {
			if (hit(15, 3, "este")) {
				teleport(0, 15, 4, "oeste");
			}
			if (hit(9, 29, "sur")) {
				teleport(2, 3, 27, "norte");
			}
		}
		
		// EVENTOS SELVA
		if (gp.currentIntangMap == 2) {
			if (hit(7,8, "sur") == true) {
				teleport(1, 12, 9, "sur");
			}
			
			if (hit(2, 29, "sur")) {
				teleport(1, 9, 27, "norte");			
			}
			
			// Entra a la casa
			if (hit(12,9, "norte") == true) {
				teleport(2.1f, 7, 8, "norte");
			}
		
		}	
		
		// Sale de la casa
		if (gp.currentIntangMap == 2.1f) {
			if (hit(7, 9, "sur")) {
				teleport(2,12, 9, "norte");
			}
		}
		
	}
	
	public boolean hit(int eventCol, int eventRow, String reqDirection) {
		
		boolean hit = false;
		
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		eventRect.x = eventCol * gp.tileSize + eventRect.x;
		eventRect.y = eventRow * gp.tileSize + eventRect.y;
		
		if (gp.player.solidArea.intersects(eventRect)) {
			if (gp.player.direction.equals(reqDirection) || reqDirection.equals("any")) {
				hit = true;
			}
		}
		
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		eventRect.x = eventRectDefaultX;
		eventRect.y = eventRectDefaultY;
		
		return hit;
	}
	
	public void teleport(float map, int x, int y, String direction) {
		gp.currentIntangMap = map;
		gp.currentTangMap = map;
		gp.player.worldX = gp.tileSize*x;
		gp.player.worldY = gp.tileSize*y;
		gp.player.direction = direction;
	}
	
}
