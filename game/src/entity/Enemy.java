package entity;

public class Enemy {
	public int enemyX;
	public int enemyX2;
	public int enemyY;
	public int enemyY2;
	public int enemyHeight;
	public int enemyWidth;
	public int enemySpeed;
	public String type;
	public int targetPoint;

	public Enemy(int x, int y, int width, int height, int speed, String type) {
		this.enemyX = x;
		this.enemyY = y;
		this.enemyHeight = height;
		this.enemyWidth = width;
		this.enemyX2 = x + this.enemyWidth;
		this.enemyY2 = y + this.enemyHeight;
		this.enemySpeed = speed;
		this.type = type;
		this.targetPoint = calculateTargetPoint();
	}
	
	private int calculateTargetPoint() {
		return (int) Math.floor((this.enemyWidth / 7));
	}
}
