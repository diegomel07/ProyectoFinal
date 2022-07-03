package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Game.GamePanel;
import Game.UtilityTool;

public class TileManager {
	
	GamePanel gp;
	public Tile[] tile = null;;
	public int mapTileNum[][];
	public int mapSizeCol, mapSizeRow;
	public boolean collision;
	
	public TileManager(GamePanel gp, String path, ArrayList<String> renderTiles, int col, int row, boolean collision) {
		this.gp = gp;
		this.mapSizeCol = col;
		this.mapSizeRow = row;
		this.collision = collision;
		
		tile = new Tile[renderTiles.size()];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage(renderTiles);
		loadMap(path);
	}
	
	public void setup(int index, String imagePath, boolean collision) {
		
		UtilityTool uTool = new UtilityTool();
		
		try {
			
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResource((imagePath)));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadMap(String filePath) {
		
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while (col < mapSizeCol && row < mapSizeRow) {
				
				String line = br.readLine();
				
				while (col < mapSizeCol) {
					String numbers[] = line.split("	");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				if (col == mapSizeCol) {
					col = 0;
					row++;
				}
							
			}
			br.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void getTileImage(ArrayList<String> renderTiles) {
		
		for (int i = 0; i < renderTiles.size(); i++) {
			
			if (renderTiles.get(i).equals("/tiles/vacio.png")) {
				setup(i, renderTiles.get(i), false);
			} else {
				setup(i, renderTiles.get(i), collision);
			}
			
		}
	}
	
	public void draw(Graphics2D g2) {
		
		int worldCol = 0;
		int worldRow = 0;
		
		
		while (worldCol < mapSizeCol && worldRow < mapSizeRow) {
			
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
						
			if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
					worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
					worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
					worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
				
				if (tileNum < tile.length) {
					g2.drawImage(tile[tileNum].image, screenX, screenY, null);
				}
			}
			
			worldCol++;
			
			
			if (worldCol == mapSizeCol) {
				worldCol = 0;
				worldRow++;	
			}
			
		}
		
	}
	
}

