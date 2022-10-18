package main;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Entity{
	GamePanel gp;
	KeyboardHandler keyH;
	GameDifficulties gameDifficulties;
	public BufferedImage image = null;
	public int playerRectX1, playerRectX2;
	public int playerRectY1, playerRectY2;
	
	public Player(GamePanel gp, KeyboardHandler keyH, GameDifficulties gameDifficulties) {
		this.gp = gp;
		this.keyH = keyH;
		this.gameDifficulties = gameDifficulties;
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		x = 384;
		y = 5;
		speed = gameDifficulties.playerSpeed;
		direction = "right";
	}
	
	private void setPlayerRect() {
		playerRectX1 = this.x + 15;
		playerRectX2 = this.x + gp.tileSize - 15;
		playerRectY1 = this.y;
		playerRectY2 = this.y + gp.tileSize;
	}
	
	public void getPlayerImage() {
		try {
			
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/chickenLeft.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/chickenRight.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void reset() {
		
		setDefaultValues();
		setPlayerRect();
	}
	
	public void update() {
		if(keyH.leftPressed == true || keyH.rightPressed == true || keyH.downPressed == true) {
				
			if (keyH.leftPressed) {
				if(x == 48)
					return;
				
				direction = "left";
				x -= speed;
				setPlayerRect();
				
			}
			
			else if(keyH.rightPressed) {
				if(x + 2 * gp.tileSize == gp.screenWidth)
					return;
				
				direction = "right";
				x += speed;
				setPlayerRect();
				
			}
			else if (keyH.downPressed) {
				direction = "down";
			}
			
		}	
	}
	
	public void draw(Graphics2D g2) {
		
		switch(direction) {	
		case "down":
			if(image == left1)
				image = left1;
			else if(image == right1)
				image = right1;
			break;
			
		case "left":
			image = left1;
			break;
			
		case "right":
			image = right1;
			break;
		}
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
		//g2.setColor(Color.black);
		//g2.drawRect(playerRectX1, playerRectY1, playerRectX2 - playerRectX1, playerRectY2 - playerRectY1);
	}
} 
