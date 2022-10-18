package main;

import javax.swing.JFrame;


public class Main {
	public static void main(String[] args) {
		//Bullet bullet = new Bullet(5, 5, 2, 2);
		//System.out.println(bullet.bulletCenterX + " " + bullet.bulletCenterY +  " " + bullet.bulRectX1 + " " + bullet.bulRectX2 + " " + bullet.bulRectX3 + " " + bullet.bulRectX4 + " " + bullet.bulRectY1 + " " + bullet.bulRectY2 + " " + bullet.bulRectY3 + " " + bullet.bulRectY4);
		
		JFrame window = new JFrame("MyGame");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		GamePanel gamePanel = new GamePanel(window);
		window.add(gamePanel);
		window.pack(); // causes to fit window the preffered size with its subcomponent
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		gamePanel.startGameThread();

	}
}
