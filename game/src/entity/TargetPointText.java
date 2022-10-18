package entity;

public class TargetPointText {
	
	public int x;
	public int y;
	public int wdth;
	public int height;
	public int speed;
	public String point;
	public long startTime;

	public TargetPointText(int x, int y, int width, int height, int speed, int point) {
		this.x = x;
		this.y = y;
		this.wdth = width;
		this.height = height;
		this.speed = speed;
		this.point = Integer.toString(point);
		this.startTime = System.currentTimeMillis();
	}
}
