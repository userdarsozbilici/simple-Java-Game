package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entity.Sound;

public class KeyboardHandler implements KeyListener {

	public boolean downPressed, leftPressed, rightPressed, spacebarPressed, leftReleased, rightReleased;
	public boolean leftArrowPressed, rightArrowPressed, downArrowPressed, downReleased;
	public boolean pausePressed = false;
	public boolean moreSpeed = false;
	public boolean enterPressed = false;
	Sound sound = new Sound();

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();

		if (code == KeyEvent.VK_A) {
			leftPressed = true;
			downPressed = false;
			leftReleased = false;
		}

		if (code == KeyEvent.VK_D) {
			rightPressed = true;
			downPressed = false;
			rightReleased = false;
		}

		if (code == KeyEvent.VK_RIGHT) {
			rightArrowPressed = true;
		}

		if (code == KeyEvent.VK_E) {
			if (moreSpeed)
				moreSpeed = false;

			else if (!moreSpeed)
				moreSpeed = true;

			System.out.println((int) (Math.random() * 10 % 10));
		}

		if (code == KeyEvent.VK_LEFT) {
			leftArrowPressed = true;
		}

		if (code == KeyEvent.VK_DOWN) {
			downArrowPressed = true;
			downReleased = false;
		}

		if (code == KeyEvent.VK_SPACE) {
			spacebarPressed = true;
		}
		
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		
		if (code == KeyEvent.VK_Q) {
			if(pausePressed)
				pausePressed = false;
			
			else 
				pausePressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		int code = e.getKeyCode();

		if (code == KeyEvent.VK_A) {
			leftPressed = false;
			downPressed = true;
			leftReleased = true;
		}

		if (code == KeyEvent.VK_DOWN) {
			downArrowPressed = false;
			downReleased = true;
		}

		if (code == KeyEvent.VK_RIGHT) {
			rightArrowPressed = false;
		}

		if (code == KeyEvent.VK_LEFT) {
			leftArrowPressed = false;
		}

		if (code == KeyEvent.VK_D) {
			rightPressed = false;
			downPressed = true;
			rightReleased = true;
		}
		
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = false;
		}
		
	}

}
