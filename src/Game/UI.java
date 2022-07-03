package Game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import object.OBJ_KEY;

public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	Font dungeonfont;
	//Font arial_20, arial_80B;
	BufferedImage gif;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public String currentDialogue = "";
	public int commandNum = 0;
	
	public UI(GamePanel gp) {
		this.gp = gp;

		try {
			InputStream is = getClass().getResourceAsStream("/fonts/monogram-extended.ttf");
			dungeonfont = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//arial_20 = new Font("Arial", Font.PLAIN, 20);
		//arial_80B = new Font("Arial", Font.BOLD, 80);
		
	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		g2.setFont(dungeonfont);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(Color.white);
		
		// TITLE STATE
		if (gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		// PLAY STATE
		if (gp.gameState == gp.playState) {
			
		} 
		// PAUSE STATE
		if (gp.gameState == gp.pauseState){
			drawPausedScreen();
		}
		// DIALOG STATE
		if (gp.gameState == gp.dialogueState) {
			drawDialogScreen();
		}
	}
	
	public void drawTitleScreen() {
		
		g2.setColor(Color.black);
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		// TITLE NAME
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80f));
		String text = "Proyecto Final POO";
		int x = getXforCenteredText(text);
		int y = gp.tileSize*3;
		
		// SHADOW
		shadowText(text, x, y);
		
		// MAIN COLOR
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		// GIF RE PRO
		x = gp.screenWidth/2 - (gp.tileSize*5)/2;
		y += gp.tileSize*2;
		BufferedImage reiChikita;
		try {
			reiChikita = ImageIO.read(getClass().getResourceAsStream("/npc/reichikita.png"));
			g2.drawImage(reiChikita, x, y, gp.tileSize*5, gp.tileSize*5, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// MENU
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48f));
		
		text = "NEW GAME";		
		x = getXforCenteredText(text);
		y += gp.tileSize*6;
		shadowText(text, x, y);
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		if (commandNum == 0) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		text = "LOAD GAME";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		shadowText(text, x, y);
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		if (commandNum == 1) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		text = "QUIT";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		shadowText(text, x, y);
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		if (commandNum == 2) {
			g2.drawString(">", x-gp.tileSize, y);
		}
	}
	
	public void drawPausedScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
	}
	
	public void drawDialogScreen() {
		
		// WINDOW
		int x = gp.tileSize * 2;
		int y = gp.tileSize / 2;
		int width = gp.screenWidth - (gp.tileSize*4);
		int height = gp.tileSize*4;
		drawSubWindow(x, y ,width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
		x += gp.tileSize;
		y += gp.tileSize;
		
		for (String line : currentDialogue.split("\n")){
			g2.drawString(line, x, y);
			y += 30;
		}
		
	}
	
	public void drawSubWindow(int x, int y, int width, int height) {
		
		Color c = new Color(0, 0, 0, 210);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}
	
	public int getXforCenteredText(String text) {
		int lenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - lenght/2;
		return x;
	}
	
	public void shadowText(String text, int x, int y) {
		g2.setColor(Color.gray);
		g2.drawString(text, x+3, y+3);
	}
	
}
