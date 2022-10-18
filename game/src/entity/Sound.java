package entity;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	Clip clip;
	Clip[] clips = new Clip[11];
	URL soundUrl[] = new URL[11];

	public Sound() {
		soundUrl[0] = getClass().getResource("/sounds/backgroundMusic1.wav");
		soundUrl[1] = getClass().getResource("/sounds/shootSound.wav");
		soundUrl[3] = getClass().getResource("/sounds/gettingPointSound.wav");
		soundUrl[4] = getClass().getResource("/sounds/levelCompletitionSound.wav");
		soundUrl[5] = getClass().getResource("/sounds/gameOverSound.wav");
		soundUrl[6] = getClass().getResource("/sounds/losingHealthSound.wav");
		soundUrl[7] = getClass().getResource("/sounds/backgroundMusic2.wav");
		soundUrl[8] = getClass().getResource("/sounds/backgroundMusic3.wav");
		soundUrl[9] = getClass().getResource("/sounds/cursorSound.wav");
		soundUrl[10] = getClass().getResource("/sounds/playSound.wav");
	}

	public void setFile(int i) {
		try {

			AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrl[i]);
			clip = AudioSystem.getClip();
			clips[i] = clip;
			clip.open(ais);

		} catch (Exception e) {

		}
	}

	public void play() {

		clip.start();

	}

	public void loop() {

		clip.loop(Clip.LOOP_CONTINUOUSLY);

	}

	public void stop(int i) {
		if(clips[i] == null)
			return;
		
		clips[i].stop();

	}
}
