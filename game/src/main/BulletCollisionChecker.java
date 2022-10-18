package main;

import entity.Bullet;
import entity.Enemy;
import entity.Sound;

public class BulletCollisionChecker {
	GamePanel gamepanel;
	ShootSystem shootSystem;
	EnemySystem enemySystem;
	TargetPointTextSystem targetPointTextSystem;
	GameDifficulties gameDifficulties;
	Sound sound = new Sound();
	
	public BulletCollisionChecker(GamePanel gamePanel, ShootSystem shootSystem, EnemySystem enemySystem, TargetPointTextSystem targetPointTextSystem, GameDifficulties gameDifficulties) {
		this.gamepanel = gamePanel;
		this.shootSystem = shootSystem;
		this.enemySystem = enemySystem;
		this.gameDifficulties = gameDifficulties;
		this.targetPointTextSystem = targetPointTextSystem;
	}
	
	private void playHitEffect() {
		sound.setFile(3);
		sound.play();
	}
	
	private boolean bulletHitChecker(Enemy enemy, Bullet bullet) {
		
		if(bullet.bulRectX1 <= enemy.enemyX2 && bullet.bulRectX2 >= enemy.enemyX2) {
			if(bullet.bulRectY3 >= enemy.enemyY && bullet.bulRectY3 <= (enemy.enemyY + enemy.enemyHeight)) {
				//System.out.println(bullet.bulRectY3 + " " + enemy.enemyY2);
				//System.out.println("1");
				return true;
			}	
		}
		
		else if(bullet.bulRectX1 <= enemy.enemyX && bullet.bulRectX2 >= enemy.enemyX) {
			if(bullet.bulRectY3 >= enemy.enemyY && bullet.bulRectY3 <= (enemy.enemyY + enemy.enemyHeight)) {
				//System.out.println(bullet.bulRectY3 + " " + enemy.enemyY2);
				//System.out.println("1");
				return true;
			}
		}
		
		else if(bullet.bulRectX1 >= enemy.enemyX && bullet.bulRectX2 <= enemy.enemyX2) {
			if(bullet.bulRectY3 >= enemy.enemyY && bullet.bulRectY3 <= (enemy.enemyY + enemy.enemyHeight)) {
				//System.out.println(bullet.bulRectY3 + " " + enemy.enemyY2);
				//System.out.println("1");
				return true;
			}
		}
		return false;
	}
	
	public void collisionScanner() {
		
		for (int i = 0; i < shootSystem.bullets.length; i++) {
			
			if(shootSystem.bullets[i] == null)
				continue;
			
			for (int j = 0; j < enemySystem.enemies.length; j++) {
				if(enemySystem.enemies[j] == null)
					continue;
				
				if(enemySystem.enemies[j].type == "enemy")
					continue;
				
				if(bulletHitChecker(enemySystem.enemies[j], shootSystem.bullets[i]) && enemySystem.enemies[j].type == "target") {
					//System.out.println("aaadsad");
					makeHit(i, j);
					break;
				}
			}
		}
	}
	
	private void makeHit(int i, int j) {
		gamepanel.point += enemySystem.enemies[j].targetPoint;
		targetPointTextSystem.createTargetPointText(enemySystem.enemies[j]);
		shootSystem.bullets[i] = null;
		enemySystem.enemies[j] = null;
		playHitEffect();
		if(gamepanel.point >= gameDifficulties.targetScore) {
			gamepanel.point = gameDifficulties.targetScore;
			gamepanel.isLevelCompleted = true;	
		}
	}
}
