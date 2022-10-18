package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import entity.TargetLine;

public class TargetLineSystem implements Drawable{

	GamePanel gp;
	KeyboardHandler keyH;
	Player player;
	TargetLine targetLine;

	boolean isTarget = false;

	public TargetLineSystem(GamePanel gp, KeyboardHandler keyH, Player player) {
		this.gp = gp;
		this.keyH = keyH;
		this.player = player;
		targetLine = new TargetLine(player.x + 24, player.x + 24, player.y + 48, player.y + 75, 0);
	}

	private void syncronizeToPlayer() {
		float tempDistanceX = targetLine.x2 - targetLine.x1;
		float tempDistanceY = targetLine.y2 - targetLine.y1;
		targetLine.x1 = player.x + 24;
		targetLine.y1 = player.y + 48;
		targetLine.x2 = targetLine.x1 + tempDistanceX;
		targetLine.y2 = targetLine.y1 + tempDistanceY;
	}

	public TargetLine getTargetLine() {
		return targetLine;
	}
	
	@Override
	public void  reset() {
		syncronizeToPlayer();
		targetLine.x2 = targetLine.x1;
		targetLine.y2 = targetLine.y1 + 27;
	}
	
	@Override
	public void update() {

		if (keyH.downArrowPressed) {
			isTarget = true;
			syncronizeToPlayer();
		}

		if (keyH.downReleased)
			isTarget = false;

		if (isTarget) {

			if (keyH.leftArrowPressed == true && targetLine.x2 >= targetLine.x1) {
				// System.out.println("left b�y�k");
				targetLine.decreaseAngle("left");
			}

			if (keyH.rightArrowPressed == true && targetLine.x2 <= targetLine.x1) {
				// System.out.println("right k���k");
				targetLine.decreaseAngle("right");
			}

			if (keyH.leftArrowPressed == true && targetLine.x2 <= targetLine.x1) {
				// System.out.println("left k���k");
				targetLine.increaseAngle("left");
			}

			if (keyH.rightArrowPressed == true && targetLine.x2 >= targetLine.x1) {
				targetLine.increaseAngle("right");
				// System.out.println("right b�y�k");
			}
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		if (isTarget) {
			g2.setColor(Color.white);
			g2.draw(new Line2D.Float(targetLine.x1, targetLine.y1, targetLine.x2, targetLine.y2));
		}
	}
}
