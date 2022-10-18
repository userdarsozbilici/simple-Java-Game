package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI implements Drawable{
	
	GamePanel gamePanel;
	
	Font arial_40;
	
	public UI(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		arial_40 = new Font("Arial", Font.PLAIN, 20);
	}
	
	public void drawLevelCompletitionComponents(Graphics2D g2) {
		
		if(gamePanel.level == 3) {
			drawCongragulationsComponent(g2);
			return;
		}
		
		g2.setColor(Color.white);
		g2.drawRoundRect(274, 208, 240, 120, 5, 5);
		g2.setFont(arial_40);
		g2.drawString("Level " + gamePanel.level + " Completed" , 314, 258);
		g2.drawString("Press Enter to Continue", 294, 298);
	}
	
	public void drawGameOverComponents(Graphics2D g2) {
	
		g2.setColor(Color.white);
		g2.drawRoundRect(244, 208, 280, 120, 5, 5);
		g2.setFont(arial_40);
		g2.drawString("Game Over", 324, 258);
		g2.drawString("Press Enter to Restart Game", 254, 298);
		
	}
	
	public void drawCongragulationsComponent(Graphics2D g2) {
		g2.setColor(Color.white);
		g2.drawRoundRect(184, 208, 400, 120, 5, 5);
		g2.setFont(arial_40);
		g2.drawString("Congragulations You Finished The Game", 204, 258);
		g2.drawString("Press Enter to Restart Game", 254, 298);
	}
	
	public void drawCursor(Graphics2D g2,int x, int y) {
		g2.drawImage(gamePanel.tileM.tile[7].image, x, y, 60, 50, null);
	}
	
	@Override
	public void draw(Graphics2D g2) {
		
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		g2.drawString("Point: " + gamePanel.point + "/" + gamePanel.gameDifficulties.targetScore , 60, 20);
		g2.drawString("Level " + gamePanel.level, 60, 40);

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
