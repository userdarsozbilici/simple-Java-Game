package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Heart;
import entity.Sound;

public class HealthSystem implements Drawable {
	GamePanel gamePanel;
	Sound sound = new Sound();
	private BufferedImage heartImage;
	private Heart[] hearts;
	private long lastReducedTime;
	public int numberOfHearts = 3;
	private int heartIndex = 0;
	
	public HealthSystem(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		hearts = new Heart[3];
		setHeartImage();
	}
	
	public void setHeartImage() {
		try {
			heartImage = ImageIO.read(getClass().getResourceAsStream("/ui/heart.png")); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setStartHeartsOrder(Graphics2D g2) {
		int location = 620;
		for (int i = 0; i < hearts.length; i++) {
			hearts[i] = new Heart(location, 10, 30, 30);
			g2.drawImage(heartImage, hearts[i].x, hearts[i].y, hearts[i].width, hearts[i].height, null);
			location += 30;
		}
	}
	
	public void reset(Graphics2D g2){
		
		setStartHeartsOrder(g2);
		numberOfHearts = 3;
		heartIndex = 0;
		
	}
	
	public void reduceHealth() {
		if((System.currentTimeMillis() - lastReducedTime) < gamePanel.gameDifficulties.lastReducedChecker)
			return;
		
		numberOfHearts--;
		hearts[heartIndex] = null;
		playLosingHeartSound();
		lastReducedTime = System.currentTimeMillis();
		heartIndex++;
	}
	
	@Override
	public void draw(Graphics2D g2) {
		for (int i = 0; i < hearts.length; i++) {
			if(hearts[i] == null)
				continue;
			
			g2.drawImage(heartImage, hearts[i].x, hearts[i].y, hearts[i].width, hearts[i].height, null);
		}
	}
	
	private void playLosingHeartSound() {
		
		sound.setFile(6);
		sound.play();
		
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
