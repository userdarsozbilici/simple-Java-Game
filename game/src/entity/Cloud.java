package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Entity;

public class Cloud extends Entity {
	
	public int width;
	public int height;
	public BufferedImage image;
	
	public Cloud(int x, int y, int width, int height, int speed) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
		setImage();
	}
	
	private void setImage() {
		try {
	
			image = ImageIO.read(getClass().getResourceAsStream("/tiles/cloud.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
