package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import entity.Enemy;
import entity.TargetPointText;

public class TargetPointTextSystem implements Drawable{
	GamePanel gamePanel;
	int targetPointTextIndex = 0;
	TargetPointText[] targetPointTexts;
	Font arial_40;
	
	public TargetPointTextSystem(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		targetPointTexts = new TargetPointText[30];
		arial_40 = new Font("Arial", Font.PLAIN, 25);
	}
	
	public void createTargetPointText(Enemy enemy) {
		
		targetPointTexts[targetPointTextIndex] = new TargetPointText(enemy.enemyX, enemy.enemyY, enemy.enemyWidth, enemy.enemyHeight, enemy.enemySpeed, enemy.targetPoint);
		
		if(targetPointTextIndex == 28)
			targetPointTextIndex = 0;
		
		targetPointTextIndex++;
		
	}
	
	@Override
	public void reset() {
		for (int i = 0; i < targetPointTexts.length; i++) {
			if(targetPointTexts[i] == null)
				continue;
			
			targetPointTexts[i] = null;
			targetPointTextIndex = 0;
		}
	}
	
	@Override
	public void update() {
		for (int i = 0; i < targetPointTexts.length; i++) {
			
			if(targetPointTexts[i] == null)
				continue;
			
			if(System.currentTimeMillis() - targetPointTexts[i].startTime > 1750) {
				targetPointTexts[i] = null;
				continue;
			}
			
			targetPointTexts[i].y -= targetPointTexts[i].speed;
			
		}
	}
	
	@Override
	public void draw(Graphics2D g2) {
		
		for (int i = 0; i < targetPointTexts.length; i++) {
			
			if(targetPointTexts[i] == null)
				continue;
			
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			g2.drawString("+" + targetPointTexts[i].point, targetPointTexts[i].x, targetPointTexts[i].y);
		}
		
	}
}