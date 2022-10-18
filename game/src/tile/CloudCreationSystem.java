package tile;

import java.awt.Graphics2D;

import entity.Cloud;
import main.GamePanel;

public class CloudCreationSystem {
	
	GamePanel gamePanel;
	Cloud[] clouds;
	Cloud[] cloudRow;
	private int cloudsIndex = 0;
	private long lastCreationTime;
	private boolean check = false;
	
	public CloudCreationSystem(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		clouds = new Cloud[50];
	}
	
	
	public void synchronizeToGeneralArray() {
		for (int i = 1; i < cloudRow.length; i++) {
			if (cloudRow[i] == null)
				continue;

			clouds[cloudsIndex] = cloudRow[i];
			if (cloudsIndex > 48)
				cloudsIndex = 0;
			
			cloudsIndex++;
		}
	}
	
	private int getRandomValue(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
	
	public Cloud[] createClouds() {
		Cloud[] temp = new Cloud[16];
		int location = 48;
		
		for (int i = 1; i < temp.length - 1; i++) {
			
			if(getRandomValue(1, 100) >= 95) {
				temp[i] = new Cloud(location, 599, gamePanel.tileSize, gamePanel.tileSize, 1);
				location += gamePanel.tileSize;
				continue;
			}
			
			location += gamePanel.tileSize;
		}
		return temp;
	}
	
	
	public void update() {
		
		if(!check) {
			cloudRow = createClouds();
			synchronizeToGeneralArray();
			lastCreationTime = System.currentTimeMillis();
			check = true;
		}
		
		if((System.currentTimeMillis() - lastCreationTime) >= getRandomValue(1000, 2000)) 
			check = false;
		
		for (int i = 0; i < clouds.length; i++) {
			if(clouds[i] == null)
				continue;
			
			clouds[i].y -= clouds[i].speed;
		}
	}
	
	public void draw(Graphics2D g2) {
		for (int i = 0; i < clouds.length; i++) {
			if(clouds[i] == null)
				continue;
			
			g2.drawImage(clouds[i].image, clouds[i].x, clouds[i].y, clouds[i].width, clouds[i].height, null);
			
		}
	}
}
