package entity;

public class Bullet {
	public float bulletX;
	public float bulletY;
	public float bulletWidth = 20;
	public float bulletHeight = 20;
	public float bulletSpeedX;
	public float bulletSpeedY;
	public float bulletCenterX;
	public float bulletCenterY;
	public float bulRectX1, bulRectX2, bulRectX3, bulRectX4, bulRectY1, bulRectY2, bulRectY3, bulRectY4;
	
	public Bullet(float x, float y, float speedX , float speedY) {
		this.bulletX = x;
		this.bulletY = y;
		this.bulletSpeedX = speedX;
		this.bulletSpeedY = speedY;
		setInnerRectValues();
	}
	
	public void progressBullet() {
		this.bulletY += this.bulletSpeedY;
		this.bulletX += this.bulletSpeedX;
		setInnerRectValues();
	}
	
	private void setInnerRectValues() {
		this.bulletCenterX = this.bulletX + (bulletWidth / 2);
		this.bulletCenterY = this.bulletY + (bulletHeight / 2);
		float amount = (float) (10 / Math.sqrt(2));
		this.bulRectX1 = bulletCenterX - amount;
		this.bulRectY1 = bulletCenterY - amount;
		this.bulRectX2 = bulletCenterX + amount;
		this.bulRectY2 = bulletCenterY - amount;
		this.bulRectX3 = bulletCenterX - amount;
		this.bulRectY3 = bulletCenterY + amount;
		this.bulRectX4 = bulletCenterX + amount;
		this.bulRectY4 = bulletCenterY + amount;
	}
	
}	
