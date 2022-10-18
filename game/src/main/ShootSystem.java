package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import entity.Bullet;
import entity.Sound;
import entity.TargetLine;

public class ShootSystem implements Drawable{

	GamePanel gp;
	KeyboardHandler keyH;
	GameDifficulties gameDifficulties;
	Player player;
	Bullet[] bullets;
	TargetLineSystem targetLineSystem;
	TargetLine targetLine;
	Sound sound;

	boolean isShot = false;
	boolean isCurrentShot = false;
	int bulletIndex = 0;

	public ShootSystem(GamePanel gp, KeyboardHandler keyH, Player player, TargetLineSystem targetLineSystem, GameDifficulties gameDifficulties) {
		this.gp = gp;
		this.keyH = keyH;
		this.player = player;
		this.gameDifficulties = gameDifficulties;
		this.targetLineSystem = targetLineSystem;
		this.targetLine = targetLineSystem.getTargetLine();
		bullets = new Bullet[100];
	}

	public float calculateSpeed(TargetLine targetLine) {
		float delta = ((targetLine.y2 - targetLine.y1) / 3);
		// System.out.println(delta);
		float speed = (targetLine.x2 - targetLine.x1) / delta;

		if (Math.abs(speed) > 30)
			return 30;

		return speed;
	}

	public void createBullet() {

		float bulletSpeedX = (float) ((targetLine.x2 - targetLine.x1) / 5);
		float bulletSpeedY = (float) ((targetLine.y2 - targetLine.y1) / 5);

		if (keyH.moreSpeed) {

			bulletSpeedX *= 1.3;
			bulletSpeedY *= 1.3;

		}

		if (bulletIndex == 49)
			bulletIndex = 0;

		// System.out.println(bulletSpeedX);
		//System.out.println(bulletSpeedX + " " + bulletSpeedY);
		bullets[bulletIndex] = new Bullet(player.x + 13, player.y + gp.tileSize - 5, bulletSpeedX * gameDifficulties.bulletSpeedRetarderRate,
				bulletSpeedY * gameDifficulties.bulletSpeedRetarderRate);
		// bullets[bulletIndex] = new Bullet(targetLine.x2, targetLine.y2, bulletSpeedX,
		// (float) 2.0);
		bulletIndex++;
		keyH.spacebarPressed = false;
		gp.soundManager.playEffect(1);

	}
	
	@Override
	public void reset() {
		for (int i = 0; i < bullets.length; i++) {
			if(bullets[i] == null)
				continue;
			
			bullets[i] = null;
		}
		
		bulletIndex = 0;
	}
	
	@Override
	public void update() {

		if (keyH.spacebarPressed == true) {
			gp.isRestarted = false;
			createBullet();
			isShot = true;
		}

		if (isShot) {
			int i;

			for (i = 0; i < 100; i++) {
				if (bullets[i] == null)
					continue;

				if ((bullets[i].bulletX - 48) <= 3 || (705 - bullets[i].bulletX) <= 3)
					bullets[i].bulletSpeedX *= -1;

				bullets[i].progressBullet();

			}

			// System.out.println(bullet.bulletSpeedX + " " + bullet.bulletX);

		}
	}
	
	@Override
	public void draw(Graphics2D g2) {

		if (isShot) {
			int i;
			for (i = 0; i < 100; i++) {

				if (bullets[i] == null)
					continue;

				// System.out.println(bullet[i].bulletSpeedX + " " + bullet[i].bulletX);

				if (bullets[i].bulletY > 580) {
					bullets[i] = null;
					return;
				}

				g2.setColor(Color.white);
				g2.draw(new Ellipse2D.Float(bullets[i].bulletX, bullets[i].bulletY, bullets[i].bulletWidth, bullets[i].bulletHeight));
				// g2.fillOval(bullets[i].bulletX, bullets[i].bulletY, 20, 20);

			}
		}
	}
}
