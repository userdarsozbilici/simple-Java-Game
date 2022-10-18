package main;

import java.awt.Color;
import java.awt.Graphics2D;
import entity.Enemy;

public class EnemySystem implements Drawable{
	GamePanel gp;
	GameDifficulties gameDifficulties;
	Enemy[] enemies;
	Enemy[] enemyRow;
	Enemy[] currentRow;
	Enemy enemy;
	private long enemyTimer;
	public boolean check = false;
	private int enemyIndex = 0;
	int counterEnemy = 0;

	public EnemySystem(GamePanel gp, GameDifficulties gameDifficulties) {
		this.gp = gp;
		this.gameDifficulties = gameDifficulties;
		enemies = new Enemy[70];
		enemyRow = new Enemy[15];
		//enemy = new Enemy(360, 520, gp.tileSize, gp.tileSize, 1, "enemy");
	}

	private void synchronizeToGeneralArray(Enemy[] enemyRow) {
		for (int i = 1; i < enemyRow.length; i++) {
			if (enemyRow[i] == null)
				continue;

			enemies[enemyIndex] = enemyRow[i];
			if (enemyIndex > 68)
				enemyIndex = 0;
			enemyIndex++;
		}
	}

	private int getRandomValue(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
	
	private Enemy[] adjustSpaces(Enemy[] enemyRow) {

		for (int i = 1; i < enemyRow.length-1; i++) {
			if (i == enemyRow.length - 1)
				continue;
			Enemy currentElement = enemyRow[i];

			if (currentElement == null)
				continue;

			if (enemyRow[i + 1] != null) {
				if (currentElement.enemyX > enemyRow[i + 1].enemyX)
					currentElement.enemyX -= (currentElement.enemyX - enemyRow[i + 1].enemyX + 3);

				//if (enemyRow[i - 1] != null && currentElement.enemyX < enemyRow[i - 1].enemyX + gp.tileSize)
					//currentElement.enemyWidth -= (enemyRow[i - 1].enemyX - currentElement.enemyX + 3);
			}

			if (enemyRow[i - 1] != null) {
				if (currentElement.enemyX < enemyRow[i - 1].enemyX + gp.tileSize)
					currentElement.enemyX += (enemyRow[i - 1].enemyX - currentElement.enemyX + 3);

				//if (enemyRow[i + 1] != null && currentElement.enemyX > enemyRow[i + 1].enemyX)
					//currentElement.enemyWidth -= (enemyRow[i + 1].enemyX - currentElement.enemyX + 3);
			}
		}

		for (int i = 1; i < enemyRow.length-1; i++) {
			if (i == enemyRow.length - 1)
				continue;
			Enemy currentElement = enemyRow[i];

			if (currentElement == null)
				continue;

			if (enemyRow[i - 1] != null) {
				// System.out.println("clear");
				if (currentElement.enemyX < enemyRow[i - 1].enemyX + gp.tileSize)
					enemyRow[i] = null;
			}

			if (enemyRow[i + 1] != null) {
				// System.out.println("clear");
				if (currentElement.enemyX > enemyRow[i + 1].enemyX || currentElement.enemyX + gp.tileSize > enemyRow[i + 1].enemyX)
					enemyRow[i] = null;
			}
		}
		return enemyRow;
	}

	public Enemy[] createEnemyRow() {
		Enemy[] temp = new Enemy[15];

		int location = gp.tileSize;

		for (int i = 1; i < temp.length-1; i++) {

			boolean toBeCreated;

			if ((Math.random() * 10 % 10) <= gameDifficulties.enemyCreationProbabilityValue)
				toBeCreated = true;
			else
				toBeCreated = false;

			if (toBeCreated) {
				// System.out.println("aaa" + " " + i);
				int tempX;
				int tempWidth;
				tempWidth = getRandomValue(35, gp.tileSize);
				tempX = getRandomValue(location, location + gp.tileSize);
				//System.out.println("X " + tempX);
				
				if(getRandomValue(0, 10) >= gameDifficulties.targetCreationProbabilityValue) {
					int tempTargetWidth = getRandomValue(30, 45);
					temp[i] = new Enemy(tempX, 599, tempTargetWidth, tempTargetWidth, gameDifficulties.EnemyAndTargetSpeed, "target");
				}

				else {
					temp[i] = new Enemy(tempX, 599, tempWidth, tempWidth, gameDifficulties.EnemyAndTargetSpeed, "enemy");
				}
					
			}

			//System.out.println(location);
			location += gp.tileSize;
			
		}

		temp = adjustSpaces(temp);
		currentRow = temp;
		return temp;
	}
	
	@Override
	public void reset() {
		
		for (int i = 0; i < enemies.length; i++) {
			if(enemies[i] == null)
				continue;
			
			enemies[i] = null;
			
			enemyIndex = 0;
			check = false;
		}
		
	}
	
	@Override
	public void update() {

		if (!check) {
			enemyRow = createEnemyRow();
			//System.out.println("enemycount " + " " + counterEnemy);
			synchronizeToGeneralArray(enemyRow);
			enemyTimer = System.currentTimeMillis();
			check = true;
		}

		if (System.currentTimeMillis() - enemyTimer >= gameDifficulties.enemyCreationTime)
			check = false;

		for (int i = 0; i < enemies.length; i++) {
			if (enemies[i] == null)
				continue;
			enemies[i].enemyY -= enemies[i].enemySpeed;
		}
	}
	
	@Override
	public void draw(Graphics2D g2) {
		for (int i = 0; i < enemies.length; i++) {

			if (enemies[i] == null)
				continue;
			if(enemies[i].type == "target") {
				g2.setColor(Color.red);
				g2.drawImage(gp.tileM.tile[2].image ,enemies[i].enemyX, enemies[i].enemyY, enemies[i].enemyWidth, enemies[i].enemyHeight, null);
			}
			else if(enemies[i].type == "enemy") {
				g2.setColor(Color.yellow);
				g2.drawImage(gp.tileM.tile[4].image, enemies[i].enemyX, enemies[i].enemyY, enemies[i].enemyWidth, enemies[i].enemyHeight, null);
			}
		}
	}
}