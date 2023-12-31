package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import object.OBJ_Heart;
import object.OBJ_Key;
import object.SuperObject;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font maruMonica , purisaB , Mali;
	BufferedImage heart_full , heart_half, heart_blank;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public String cureentDialogue = "";
	public int commandNum = 0;
	public int titleScreenState = 0; // 0: the first screen , 1: the second screen

	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		// Font

		try {
			InputStream is = getClass().getResourceAsStream("/font/TA16BIT-Regular.ttf");
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/font/NineThin.ttf");
			purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/font/Mali-Bold.ttf");
			Mali = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		// CREATE HUD OBJECT
		SuperObject heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
		

	}
	
	public void showMessage(String text) {
		
		
		// Showmessage 
		message = text;
		messageOn = true;
	}
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		
//		g2.setFont(maruMonica);
//		g2.setFont(purisaB);
		g2.setFont(Mali);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(Color.white);
		
		// TITLE STATE
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		
		// Player State
		if(gp.gameState == gp.playState) {
			drawPlayerLife();
		}
		//Pauser State
		if (gp.gameState == gp.pauseState) {
			drawPlayerLife();
			drawPauserScreen();
		}
		//Dialogue State
		if(gp.gameState == gp.dialogueState) {
			drawPlayerLife();
			drawDialogueScreen();
		}

	}
	
	public void drawPlayerLife() {
		
//		gp.player.life = 3;
		
		int x = gp.tileSize/2;
		int y = gp.tileSize/2;
		int i = 0;
		
		
		// Draw Blank Heart
		while(i < gp.player.maxLife/2) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x += gp.tileSize;
		}
		
		// Reset
		 x = gp.tileSize/2;
		 y = gp.tileSize/2;
		 i = 0;
		 
		 //Draw Current life
		 while(i < gp.player.life) {
			 g2.drawImage(heart_half, x, y, null);
			 i++;
			 if(i < gp.player.life) {
				 g2.drawImage(heart_full, x, y, null);
			 }
			 i++;
			 x += gp.tileSize;
		 }
		
		
	}
	public void drawTitleScreen() {
		
		if(titleScreenState == 0) {
			
			g2.setColor(new Color(0,0,0));
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
			
			// TITLE NAME
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,75F));
			String text = "Collegiate Realm";
			int x = getXforCenteredText(text);
			int y = gp.tileSize*3;
			
			//Shadow
			g2.setColor(Color.gray);
			g2.drawString(text,x+5,y+5);
			
			// Main Color
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			
			// Ice Display Image
			x = gp.screenWidth/2 - (gp.tileSize*2)/2;
			y += gp.tileSize*2;
			g2.drawImage(gp.player.down1, x , y , gp.tileSize*2, gp.tileSize*2, null);
			
			// Menu
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
			
			text = "NEW GAME";
			x = getXforCenteredText(text);
			y += gp.tileSize*4;
			g2.drawString(text, x, y);
			if(commandNum == 0) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "LOAD GAME";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 1) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "QUIT";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 2) {
				g2.drawString(">", x-gp.tileSize, y);
			}
		}
		else if(titleScreenState == 1) {
			
			// CLASS SECLECTION SCREEN
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(42F));
			
			String text = "Select your class!";
			int x = getXforCenteredText(text);
			int y = gp.tileSize*3;
			g2.drawString(text, x, y);
			
			text = "Fighter";
			x = getXforCenteredText(text);
			y += gp.tileSize*3;
			g2.drawString(text, x, y);
			if(commandNum == 0) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Thief";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 1) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Sorcerer";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 2) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Back";
			x = getXforCenteredText(text);
			y += gp.tileSize*2;
			g2.drawString(text, x, y);
			if(commandNum == 3) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
		}
	}
	public void drawPauserScreen() {
		
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80));
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
	
	}
	public void drawDialogueScreen() {
		
		//Window
		int x = gp.tileSize*2 ;
		int y = gp.tileSize/2 ;
		int width = gp.screenWidth - (gp.tileSize*4);
		int height = gp.tileSize*4;
		drawSubWindow(x, y, width, height);
		
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,22F));
		x += gp.tileSize;
		y += gp.tileSize;
		
		for(String line : cureentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y += 40;
		}
	}
	public void drawSubWindow(int x , int y , int width , int height) {
		
		Color c = new Color(0,0,0,210);
		g2.setColor(c);
		g2.fillRoundRect(x , y , width , height , 35 , 35);
		
		c = new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
		
	}
	public int getXforCenteredText(String text) {
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
}
