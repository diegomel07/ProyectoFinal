package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;

import Entity.Entity;
import Entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// SCREEN SETTINGS
	final int originalTileSize = 16;
	public final int scale = 2;
	
	public int tileSize = originalTileSize * scale;
	public int maxScreenCol = 20;
	public int maxScreenRow = 15;
	public int screenWidth = tileSize * maxScreenCol;
	public int screenHeight = tileSize * maxScreenRow;
	
	// WORLD SETTINGS
	public final int maxWorldCol = 32;
	public final int maxWorldRow = 32;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	// MAP SETTINGS
	public float currentIntangMap = 0;
	public float currentTangMap = 0;
	
	
	int FPS = 60;
	
	
	// PRIMER MAPA
	private TileManager tileMPrimerMapaTang = new TileManager(this, "/maps/primero/ubicacion1_tang.txt", tilePrimerMapaTang(), 16, 10, true);
	private TileManager tileMPrimerMapaIntang = new TileManager(this, "/maps/primero/ubicacion1_intang.txt", tilePrimerMapaIntang(), 16, 10, false);
	public CollisionChecker cPrimerMapaChecker = new CollisionChecker(this, tileMPrimerMapaTang);
	
	// MAPA COSTA
	private TileManager tileMCostaTang = new TileManager(this, "/maps/costa/costaTang.txt", tileCostaTang(), 15, 30, true);
	private TileManager tileMCostaIntang = new TileManager(this, "/maps/costa/costaINTang.txt", tileCostaIntang(), 15, 30, true);
	public CollisionChecker cCostaChecker = new CollisionChecker(this, tileMCostaTang);
	
	
	// MAPA SELVA
	private TileManager tileMSelvaTang = new TileManager(this, "/maps/selva/selvaTang.txt", tileSelvaTang(), 30, 30, true);
	private TileManager tileMSelvaIntang = new TileManager(this, "/maps/selva/selvaIntang.txt", tileSelvaIntang(), 30, 30, false);
	private TileManager tileMSelvaCasa1Tang = new TileManager(this, "/maps/selva/casaSelva1Tan.txt", tileSelvaCasa1Tang(), 10, 10, true);
	private TileManager tileMSelvaCasa1Intang = new TileManager(this, "/maps/selva/casaSelva1Intan.txt", tileSelvaCasa1Intang(), 10, 10, false);
	
	public CollisionChecker cSelvaChecker = new CollisionChecker(this, tileMSelvaTang);
	public CollisionChecker cSelvaCheckerCasa = new CollisionChecker(this, tileMSelvaCasa1Tang);
	
	public KeyHandler keyH = new KeyHandler(this);
	public Sound music = new Sound();	
	public Sound se = new Sound();
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public EventHandler eHandler = new EventHandler(this);
	Thread gameThread;
	
	// ENTITY AND OBJECT
	public Player player = new Player(this, keyH);
	public SuperObject obj[] = new SuperObject[10];
	public Entity npc[] = new Entity[10];
	
	// GAME STATE
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public ArrayList<String> tileSelvaTang(){
		ArrayList<String> renderTiles = new ArrayList<>();
		
		Collections.addAll(renderTiles,
				"/tiles/selva/arbolito1.png",
				"/tiles/selva/arbolito2.png",
				"/tiles/selva/arbolGrande1.png",
				"/tiles/selva/arbolGrande2.png",
				"/tiles/selva/arbolGrande3.png",
				"/tiles/selva/arbolGrande4.png",
				"/tiles/selva/arbolGrande5.png",
				"/tiles/selva/arbolGrande6.png",
				"/tiles/selva/casaEsqIzq1.png",
				"/tiles/selva/casaTechoArr.png",
				"/tiles/selva/casaEsqDer1.png",
				"/tiles/selva/casaEsqIzq2.png",
				"/tiles/selva/casaTechoMedio.png",
				"/tiles/selva/casaEsqDer2.png",
				"/tiles/selva/casaEsqIzq3.png",
				"/tiles/selva/casaTechoBajo.png",
				"/tiles/selva/casaEsqDer3.png",
				"/tiles/selva/casaEsqIzq4.png",
				"/tiles/selva/casaVentana.png",
				"/tiles/selva/casaPaloRaro.png",
				"/tiles/selva/casaPuerta.png",
				"/tiles/selva/casaEsqDer4.png",
				"/tiles/selva/casaEsqIzq5.png",
				"/tiles/selva/casaAba.png",
				"/tiles/selva/casaEsqDer5.png",
				"/tiles/vacio.png"
				);
		return renderTiles;
	}
	
	public ArrayList<String> tileSelvaIntang(){
		ArrayList<String> renderTiles = new ArrayList<>();
		
		Collections.addAll(renderTiles,
				"/tiles/selva/camino.png",
				"/tiles/selva/caminoIzq.png",
				"/tiles/selva/caminoDer.png",
				"/tiles/selva/caminoAbajo.png",
				"/tiles/selva/caminoArriba.png",
				"/tiles/selva/caminoEsqIzqAba.png",
				"/tiles/selva/caminoEsqDerArr.png",
				"/tiles/selva/caminoEsqDerAba.png",
				"/tiles/selva/caminoEsqIzqArr.png",
				"/tiles/selva/tierra.png",
				"/tiles/selva/pastoArriba.png",
				"/tiles/selva/pastoAbajo.png",
				"/tiles/selva/pastoIzq.png",
				"/tiles/selva/pastoDer.png",
				"/tiles/selva/pastoEsqIzqArr.png",
				"/tiles/selva/pastoEsqDerArr.png",
				"/tiles/selva/pastoEsqDerAba.png",
				"/tiles/selva/pastoEsqIzqAba.png",
				"/tiles/selva/pastoCurIzqArr.png",
				"/tiles/selva/pastoCurDerArr.png",
				"/tiles/selva/pastoCurIzqAba.png",
				"/tiles/selva/pastoCurDerAba.png",
				"/tiles/selva/pasto1.png",
				"/tiles/vacio.png"
				);
		
		return renderTiles;
	}
	
	public ArrayList<String> tileSelvaCasa1Intang(){
		ArrayList<String> renderTiles = new ArrayList<String>();
		
		Collections.addAll(renderTiles,
				"/tiles/selva/casa1/piso.png",
				"/tiles/selva/casa1/salida.png",
				"/tiles/selva/casa1/paredIntMedio.png",
				"/tiles/selva/casa1/pared.png"
				);
		
		return renderTiles;
	}
	
	public ArrayList<String> tileSelvaCasa1Tang(){
		ArrayList<String> renderTiles = new ArrayList<>();
		
		Collections.addAll(renderTiles,
				"/tiles/selva/casa1/pared.png",
				"/tiles/selva/casa1/paredIntArr.png",
				"/tiles/selva/casa1/paredIntAba.png",
				"/tiles/selva/casa1/armario1.png",
				"/tiles/selva/casa1/armario2.png",
				"/tiles/selva/casa1/mesita1.png",
				"/tiles/selva/casa1/mesita2.png",
				"/tiles/selva/casa1/mesa1.png",
				"/tiles/selva/casa1/mesa2.png",
				"/tiles/selva/casa1/barril.png",
				"/tiles/selva/casa1/maseta.png",
				"/tiles/selva/casa1/ventana.png",
				"/tiles/vacio.png"				
				);
		
		return renderTiles;
	}
	
	public ArrayList<String> tilePrimerMapaTang(){
		ArrayList<String> renderTiles = new ArrayList<>();
		
		Collections.addAll(renderTiles, 
				"/tiles/vacio.png",
				"/tiles/primerMapa/16.png",
				"/tiles/primerMapa/34.png",
				"/tiles/primerMapa/29.png",
				"/tiles/primerMapa/30.png",
				"/tiles/primerMapa/31.png",
				"/tiles/primerMapa/32.png",
				"/tiles/primerMapa/33.png",
				"/tiles/primerMapa/17.png",
				"/tiles/primerMapa/25.png",
				"/tiles/primerMapa/26.png",
				"/tiles/primerMapa/35.png",
				"/tiles/primerMapa/36.png",
				"/tiles/primerMapa/37.png",
				"/tiles/primerMapa/38.png",
				"/tiles/primerMapa/39.png",
				"/tiles/primerMapa/40.png",
				"/tiles/primerMapa/24.png",
				"/tiles/primerMapa/27.png",
				"/tiles/primerMapa/28.png",
				"/tiles/primerMapa/19.png",
				"/tiles/primerMapa/47.png",
				"/tiles/primerMapa/52.png",
				"/tiles/primerMapa/48.png",
				"/tiles/primerMapa/49.png",
				"/tiles/primerMapa/50.png",
				"/tiles/primerMapa/51.png",
				"/tiles/primerMapa/21.png",
				"/tiles/primerMapa/22.png",
				"/tiles/primerMapa/41.png",
				"/tiles/primerMapa/42.png",
				"/tiles/primerMapa/43.png",
				"/tiles/primerMapa/44.png",
				"/tiles/primerMapa/45.png",
				"/tiles/primerMapa/46.png"
				);
		
		return renderTiles;
	}
	
	public ArrayList<String> tilePrimerMapaIntang(){
		ArrayList<String> renderTiles = new ArrayList<>();
		
		Collections.addAll(renderTiles,
				"/tiles/primerMapa/1.png",
				"/tiles/primerMapa/17.png",
				"/tiles/primerMapa/2.png",
				"/tiles/primerMapa/3.png",
				"/tiles/primerMapa/7.png",
				"/tiles/primerMapa/4.png",
				"/tiles/primerMapa/15.png",
				"/tiles/primerMapa/5.png",
				"/tiles/primerMapa/12.png",
				"/tiles/primerMapa/10.png",
				"/tiles/primerMapa/8.png",
				"/tiles/primerMapa/11.png"
				);
		
		return renderTiles;
	}

	public ArrayList<String> tileCostaTang(){
		ArrayList<String> renderTiles = new ArrayList<>();
		
		Collections.addAll(renderTiles,
				"/tiles/vacio.png",
				"/tiles/costa/agua.png",
				"/tiles/costa/aguaBorde.png",
				"/tiles/costa/casa1.png",
				"/tiles/costa/casa2.png",
				"/tiles/costa/casa4.png",
				"/tiles/costa/casa5.png",
				"/tiles/costa/casa6.png",
				"/tiles/costa/casa7.png",
				"/tiles/costa/casa8.png",
				"/tiles/costa/casa9.png",
				"/tiles/costa/casa10.png",
				"/tiles/costa/casa11.png",
				"/tiles/costa/casa12.png",
				"/tiles/costa/casa13.png",
				"/tiles/costa/casa15.png",
				"/tiles/costa/casa16.png",
				"/tiles/costa/casa17.png",
				"/tiles/costa/casa18.png",
				"/tiles/costa/casa20.png",
				"/tiles/costa/piedraGrande1.png",
				"/tiles/costa/piedraGrande2.png",
				"/tiles/costa/piedraGrande3.png",
				"/tiles/costa/piedraGrande4.png",
				"/tiles/costa/piedraGrande5.png",
				"/tiles/costa/piedraGrande6.png",
				"/tiles/costa/piedra.png",
				"/tiles/costa/huesos.png"
				);
		
		return renderTiles;
	}

	public ArrayList<String> tileCostaIntang(){
		ArrayList<String> renderTiles = new ArrayList<>();
		
		Collections.addAll(renderTiles,
				"/tiles/costa/arena.png"
				);
		
		return renderTiles;
	}
	
	public void setupGame() {
		
		aSetter.setObject();
		aSetter.setNPC();
		//playMusic(0);
		gameState = titleState;
		
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		

		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
 		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			
			if (delta >= 1) {
				update();
				repaint();
				delta--;
			}
						
		}
	}
	
	public void update() {
		
		if (gameState == playState) {
			
			// PLAYER
			player.update();
			
			//NPC
			for (int i = 0; i < npc.length; i++) {
				if (npc[i] != null) {
					npc[i].update();
				}
			}
		}
		if (gameState == pauseState) {
			
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		// TITLE SCREEN
		if (gameState == titleState) {
			ui.draw(g2);
		}
		
		// OTHERS
		else {
			
			// TILE	
			
			// Primer Mapa
			if (currentIntangMap == 0) {
				tileMPrimerMapaIntang.draw(g2);
			}
			// Costa
			else if (currentIntangMap == 1) {
				tileMCostaIntang.draw(g2);
			}
			else if (currentIntangMap == 2) {
				tileMSelvaIntang.draw(g2);
			}
			else if (currentIntangMap == 2.1f) {
				tileMSelvaCasa1Intang.draw(g2);
			}
			
			/*
			// OBJECT
			for (int i = 0; i < obj.length; i++) {
				if (obj[i] != null) {
					obj[i].draw(g2, this);
				}
			} 
			
			// NPC
			for (int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					npc[i].draw(g2);
				}
			}
			*/
			
			// TILES WITH COLLISION
			// PRIMER MAPA
			if (currentTangMap == 0) {
				tileMPrimerMapaTang.draw(g2);
			}
			// COSTA
			if (currentTangMap == 1) {
				tileMCostaTang.draw(g2);
			}
			if (currentTangMap == 2) {
				tileMSelvaTang.draw(g2);
			}
			else if(currentTangMap == 2.1f) {
				tileMSelvaCasa1Tang.draw(g2);
			}
			
			// PLAYER
			player.draw(g2);
			
			// UI
			ui.draw(g2);
		}
		
		g2.dispose();
		
	}
	
	public void playMusic(int i) {
		music.setFile(i);
		music.reduceVolume();
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		music.stop();
	}
	
	public void playSE(int i) {
		se.setFile(i);
		se.play();
	}
	
}
