package main;

import entity.Sound;

public class GameSoundManager {
	Sound sound;
	GamePanel gamePanel;
	
	public GameSoundManager(GamePanel gamePanel) {
		this.sound = new Sound();
		this.gamePanel = gamePanel;
	}
	
	public void playBackgroundMusic() {
			
		sound.setFile(0);		
		sound.play();
		sound.loop();
	
	}
	
	public void playGameOverSound() {
		
		sound.setFile(5);
		sound.play();
		
	}
	
	public void playCursorEffect() {
		sound.setFile(9);
		sound.play();
	}
	
	public void playEnteranceSound() {
		sound.setFile(10);
		sound.play();
	}
	
	public void playLevelCompletedSound() {
		
		if(!gamePanel.levelCompletedSoundCheck) {
			sound.setFile(4);
			sound.play();
			gamePanel.levelCompletedSoundCheck = true;
		}
		
	}

	public void stopBackgroundMusic() {
		
			sound.stop(0);
			
	}

	public void playEffect(int i) {
		
		if(gamePanel.isStopped)
			return;
		
		sound.setFile(i);
		sound.play();

	}
	

}
