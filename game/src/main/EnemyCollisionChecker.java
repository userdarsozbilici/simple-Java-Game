package main;

import entity.Enemy;

public class EnemyCollisionChecker {
	GamePanel gp;
	Player player;
	EnemySystem enemySystem;
	Enemy[] enemyArray;
	Enemy[] enemiesInZone;
	
	public EnemyCollisionChecker(GamePanel gp, Player player, EnemySystem enemySystem) {
		this.gp = gp;
		this.player = player;
		this.enemySystem = enemySystem;
		this.enemyArray = enemySystem.enemies;
	}
	
	private Enemy[] getEnemiesInZone(Enemy[] enemies) {
		
		Enemy[] temp = new Enemy[enemies.length];
		int tempIndex = 0;
		for (int i = 0; i < enemies.length; i++) {
			if(enemies[i] == null)
				continue;
			
			if((enemies[i].enemyY - player.y) < 50) {
				//System.out.println(tempIndex);
				temp[tempIndex] = enemies[i];
				tempIndex++;
			}
		}
		return temp;
	}
	
	public boolean checkCollision() {
		enemiesInZone = getEnemiesInZone(enemyArray);
		
		for (int i = 0; i < enemiesInZone.length; i++) {
			
			if(enemiesInZone[i] == null)
				continue;
			
			if(enemiesInZone[i].enemyX < player.playerRectX1 && enemiesInZone[i].enemyX + enemiesInZone[i].enemyWidth >= player.playerRectX1) {
				if(enemiesInZone[i].enemyY <= player.playerRectY2 && enemiesInZone[i].enemyY >= 5) {
					//System.out.println("1");
					//System.out.println(player.playerRectX1 + " " + player.playerRectX2 + " " + player.playerRectY2);
					//System.out.println(enemiesInZone[i].enemyX + " " + enemiesInZone[i].enemyX2 + " " + enemiesInZone[i].enemyY + " " + enemiesInZone[i].enemyWidth);
					enemiesInZone = null;
					return true;
				}
			}
			
			else if(enemiesInZone[i].enemyX >= player.playerRectX1 && enemiesInZone[i].enemyX + enemiesInZone[i].enemyWidth <= player.playerRectX2) {
				if(enemiesInZone[i].enemyY <= player.playerRectY2 && enemiesInZone[i].enemyY >= 5) {
					//System.out.println("2");
					//System.out.println(player.playerRectX1 + " " + player.playerRectX2 + " " + player.playerRectY2);
					//System.out.println(enemiesInZone[i].enemyX + " " + enemiesInZone[i].enemyX2 + " " + enemiesInZone[i].enemyY + " " + enemiesInZone[i].enemyWidth);
					enemiesInZone = null;
					return true;
				}
			}
			
			else if(enemiesInZone[i].enemyX <= player.playerRectX2 && enemiesInZone[i].enemyX + enemiesInZone[i].enemyWidth > player.playerRectX2) {
				if(enemiesInZone[i].enemyY <= player.playerRectY2 && enemiesInZone[i].enemyY >= 5) {
					//System.out.println("3");
					//System.out.println(player.playerRectX1 + " " + player.playerRectX2 + " " + player.playerRectY2);
					//System.out.println(enemiesInZone[i].enemyX + " " + enemiesInZone[i].enemyX2 + " " + enemiesInZone[i].enemyY + " " + enemiesInZone[i].enemyWidth);
					enemiesInZone = null;
					return true;
				}
			}
		}
		
		enemiesInZone = null;
		return false;
	}
	
}
