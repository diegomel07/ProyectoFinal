package Entity;

import java.awt.Rectangle;
import java.util.Random;

import Game.GamePanel;

public class NPC_Robot extends Entity{
	
	public NPC_Robot(GamePanel gp) {
		super(gp);

		solidArea = new Rectangle(0, 0, 30, 35);
		direction = "sur";
		speed = 2;
		getImage();
		setDialogue();
	}
	
	public void getImage() {
		norte0 = setup("/player/robot/Norte_01.png");
		norte1 = setup("/player/robot/Norte_02.png");
		norte2 = setup("/player/robot/Norte_03.png");
		sur0 = setup("/player/robot/Sur_01.png");
		sur1 = setup("/player/robot/Sur_02.png");
		sur2 = setup("/player/robot/Sur_03.png");
		oeste0 = setup("/player/robot/Oeste_01.png");
		oeste1 = setup("/player/robot/Oeste_02.png");
		oeste2 = setup("/player/robot/Oeste_03.png");
		este0 = setup("/player/robot/Este_01.png");
		este1 = setup("/player/robot/Este_02.png");
		este2 = setup("/player/robot/Este_03.png");
	}
	
	public void setDialogue() {
		dialogues[0] = "Hello, lad.";
		dialogues[1] = "So you've come to this land.";
		dialogues[2] = "I used to be a great wizard but now...\nI'm a bit too old for taking an\nadventure.";
		dialogues[3] = "Well, good luck on you.";
		
	}
	
	public void setAction() {
		
		actionLockCounter++;
		
		if (actionLockCounter == 120) {
			Random random = new Random();
			int i = random.nextInt(100)+1;
			
			if (i <= 25) {
				direction = "norte";
			}
			if (i > 25 && i <= 50) {
				direction = "sur";
			}
			if (i > 50 && i <= 75) {
				direction = "oeste";
			}
			if (i > 75) {
				direction = "este";
			}
			
			actionLockCounter = 0;
		}
		
	}
	
	public void speak() {
		super.speak();
	}
	
}
