package main;

import java.awt.Color;
import java.awt.Graphics2D;

import entity.Enemy;
import entity.Target;

public class TargetSystem {
	
	EnemySystem enemySystem;
	GamePanel gp;
	Target[] targets;
	Target[] targetRow;
	int targetIndex = 0;
	int targetCounter = 0;
	
	public TargetSystem(GamePanel gp, EnemySystem enemySystem) {
		this.gp = gp;
		this.enemySystem = enemySystem;
		targets = new Target[80];
		targetRow = new Target[15];
	}
	
	private int getRandomValue(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
	
	private void synchronizeToGeneralArray(Target[] targetRow) {
		//System.out.println("aaaaadsa");
		for (int i = 1; i < targetRow.length; i++) {
			if (targetRow[i] == null)
				continue;
			
			targets[targetIndex] = targetRow[i];
			if (targetIndex > 68)
				targetIndex = 0;
			targetIndex++;
		}
	}
	
	private Target[] adjustSpaces(Target[] targetRow, Enemy[] currentRow) {
		
		for (int i = 1; i < targetRow.length; i++) {
			
			if(targetRow[i] == null)
				continue;
			
			int tarX1 = targetRow[i].targetX;
			int tarX2 = targetRow[i].targetX + targetRow[i].targetWidth;
			
			for (int j = 1; j < currentRow.length-1; j++) {
				
				if(currentRow[j] == null)
					continue;
				//System.out.println(j);
				int curX1 = currentRow[j].enemyX;
				int curX2 = currentRow[j].enemyX + currentRow[j].enemyWidth;
				
				if(tarX1 <= curX1 && tarX2 >= curX1) {
					//System.out.println("if");
					targetRow[i].targetX = 0;
					targetRow[i].targetY = 0;
					break;
				}
				
				if(tarX1 <= curX2 && tarX2 >= curX2) {
					//System.out.println("if");
					targetRow[i].targetX = 0;
					targetRow[i].targetY = 0;
					break;
				}
				
				if(tarX1 > curX1 && tarX2 < curX2) {
					//System.out.println("if");
					targetRow[i].targetX = 0;
					targetRow[i].targetY = 0;
					break;
				}
				
				if(tarX1 < curX1 && tarX2 > curX2) {
					//System.out.println("if");
					targetRow[i].targetX = 0;
					targetRow[i].targetY = 0;
					break;
				}
			}
		}
		return targetRow;
	}
	
	public Target[] createTargetRow() {
		Enemy[] currentRow = enemySystem.currentRow;
		Target[] temp = new Target[15];
		int location = gp.tileSize;
		
		for(int i = 1; i < currentRow.length; i++) {
			//int counter = 0;
			if(currentRow[i] == null) {
				//System.out.println("adsada");
				if(getRandomValue(0, 10) >= 7) {
					int tempX = getRandomValue(location, location + gp.tileSize - 10);
					//System.out.println("tempx " + tempX + "i " + i);
					int tempHeight = getRandomValue(20, 35);
					
					if(temp[i-1] != null) {
						location += gp.tileSize;
						continue;
					}
						
					temp[i] = new Target(tempX, 600, tempHeight, tempHeight, 1);
					location += gp.tileSize;
					//counter++;
				}
				
				else {
					location += gp.tileSize;
					continue;
				}
								
				}
			
			else
				continue;
			
			location += gp.tileSize;
			//System.out.println(location);
		}
		temp = adjustSpaces(temp, currentRow);
		return temp;
	}
	
	public void update() {
		if(!enemySystem.check) {
			targetRow = createTargetRow();
			targetCounter++;
			System.out.println("targetcount " + " " + targetCounter);
			synchronizeToGeneralArray(targetRow);
		}
		
		for (int i = 0; i < targets.length; i++) {
			if (targets[i] == null)
				continue;
			targets[i].targetY -= targets[i].targetSpeed;
		}
	}
	
	public void draw(Graphics2D g2) {
		for (int i = 0; i < targets.length; i++) {
			
			if (targets[i] == null) {
				
				continue;
			}
				
			else {
				//System.out.println("else");
				g2.setColor(Color.red);
				g2.drawRect(targets[i].targetX, targets[i].targetY, targets[i].targetWidth, targets[i].targetHeight);
			}
			
		}
	}	
}	
