package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_OldeMan extends Entity{

	public NPC_OldeMan(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		
		getImage();
		setDialogue();
	}
	
	public void getImage() {
		
		up1 = setup("/npc/old_up1");
		up2 = setup("/npc/old_up2");
		down1 = setup("/npc/old_down1");
		down2 = setup("/npc/old_down2");
		left1 = setup("/npc/old_left1");
		left2 = setup("/npc/old_left2");
		right1 = setup("/npc/old_right1");
		right2 = setup("/npc/old_right2");
		
	}
	
	public void setDialogue() {
		
		dialogues[0] = "Hello , Ice.";
		dialogues[1] = "So you're come to this island to \nfind the treasure?";
		dialogues[2] = "I used to be a great wizard but now... \nI'm a bit too old for taking an adventure.";
		dialogues[3] = "Well, good luck on you.";
	}
	
	public void setAction() {
		
		actionLockCounter ++;
		
		
		if(actionLockCounter == 120) {
			
			Random random = new Random();
			int i = random.nextInt(100)+1; // pick up a number from 1 to 100
			
			if(i <= 25) {
				direction = "up";
			}
			if(i > 25 && i <= 50) {
				direction = "down";
			}
			if(i > 50 && i <= 75) {
				direction = "left";
			}
			if(i > 75 && i <= 100) {
				direction = "right";
			}
			
			actionLockCounter = 0;
			
		}
		
	}
	public void speak() {
		
		// Do this character specific stuff
		
		super.speak();

	}
	
}
