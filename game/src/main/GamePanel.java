package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

import entity.Sound;
import tile.CloudCreationSystem;

public class GamePanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	final int originalTileSize = 16;
	final int scale = 3;
	public final int tileSize = originalTileSize * scale; // 48x48 tile
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
	public boolean isStopped = false;
	private boolean isGameOver = false;
	public boolean isLevelCompleted = false;
	public boolean isRestarted = false;
	public int point = 0; 
	private boolean isStarting = true;
	public boolean levelCompletedSoundCheck = false;
	private boolean isIncreasedLevel = false;
	public int level = 1;
	final int FPS = 120;
	final long startingTime = System.currentTimeMillis();
	String gameState = "Start";
	String cursorLocation = "Left";
	String startMenuChoice = "Play";
	
	JFrame mainFrame;
	TileManager tileM = new TileManager(this, "/maps/map.txt");
	CloudCreationSystem cloudCreationSystem = new CloudCreationSystem(this);
	KeyboardHandler keyH = new KeyboardHandler();
	Thread gameThread;
	GameDifficulties gameDifficulties = new GameDifficulties();
	Sound sound = new Sound();
	public Player player = new Player(this, keyH, gameDifficulties);
	public TargetLineSystem targetLineSystem = new TargetLineSystem(this, keyH, player);
	public ShootSystem shoot = new ShootSystem(this, keyH, player, targetLineSystem, gameDifficulties);
	public EnemySystem enemySystem = new EnemySystem(this, gameDifficulties);
	public TargetSystem targetSystem = new TargetSystem(this, enemySystem);
	public EnemyCollisionChecker enemyCollisionChecker = new EnemyCollisionChecker(this, player, enemySystem);
	public TargetPointTextSystem targetPointTextSystem = new TargetPointTextSystem(this);
	public BulletCollisionChecker bulletCollisionChecker = new BulletCollisionChecker(this, shoot, enemySystem, targetPointTextSystem, gameDifficulties);
	public UI ui = new UI(this);
	public HealthSystem healthSystem = new HealthSystem(this);
	public GameSoundManager soundManager = new GameSoundManager(this);
	
	// public StartPage starPage = new StartPage(this);

	public GamePanel(JFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		//playBackgroundMusic();
		// playEffect(2);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		double drawInterval = 1000000000 / FPS; // 0.016666 seconds
		double nextDrawTime = System.nanoTime() + drawInterval;

		while (gameThread != null) {

			update();

			repaint(); // calling paintComponent in that way

			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime /= 1000000;

				if (remainingTime < 0)
					remainingTime = 0;

				Thread.sleep((long) remainingTime);
				nextDrawTime += drawInterval;

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void update() {
		
		if(gameState == "Game") {
			if(isStopped || keyH.pausePressed || level == 4) {
				gameOver();
				return;
			}
			
			if(isLevelCompleted) {
				completeLevel();
				
				if(keyH.enterPressed) {
					isIncreasedLevel = false;
					startNextLevel();
				}
				
				return;
			}
			cloudCreationSystem.update();	
			player.update();
			shoot.update();
			targetLineSystem.update();
			enemySystem.update();
			checkCollision();
			targetPointTextSystem.update();
		}
		
		else if(gameState == "Start") {
			//System.out.println(startMenuChoice);
			if(keyH.rightArrowPressed == true && cursorLocation == "Left") {
				cursorLocation = "Right";
				startMenuChoice = "Quit";
				soundManager.playCursorEffect();
			}
				
			
			else if(keyH.leftArrowPressed == true && cursorLocation == "Right") {
				cursorLocation = "Left";
				startMenuChoice = "Play";
				soundManager.playCursorEffect();
			}
			
			if(keyH.enterPressed) {
				if(startMenuChoice == "Play") {
					gameState = "Game";
					soundManager.playEnteranceSound();
					soundManager.playBackgroundMusic();
				}
				
				else if(startMenuChoice == "Quit") {
					System.exit(0);
					mainFrame.dispose();
					mainFrame.setVisible(false);
				}
				
			}
				
		}
		
		else if(gameState == "Intro") {
			if((System.currentTimeMillis() - startingTime) > 3000) {
				gameState = "Start";
			}
		}
		
		//targetSystem.update();;

	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		
		if(gameState == "Game") {
			if(isStarting) {
				healthSystem.setStartHeartsOrder(g2);
				isStarting = false;
			}

			tileM.draw(g2);
			cloudCreationSystem.draw(g2);
			player.draw(g2);
			shoot.draw(g2);
			targetLineSystem.draw(g2);
			enemySystem.draw(g2);
			ui.draw(g2);
			targetPointTextSystem.draw(g2);
			healthSystem.draw(g2);
			//targetSystem.draw(g2);
			//starPage.draw(g2);
			
			if(isGameOver)
				ui.drawGameOverComponents(g2);
			
			if(isLevelCompleted) {
				healthSystem.reset(g2);
				ui.drawLevelCompletitionComponents(g2);
			}
			
			if(isRestarted)
				healthSystem.reset(g2);
			
		}
		
		else if(gameState == "Start") {
			g2.drawImage(tileM.tile[6].image, 0, 0, 768, 576, null);
			
			if(cursorLocation == "Left")
				ui.drawCursor(g2, 250, 305);
			
			else if(cursorLocation == "Right")
				ui.drawCursor(g2, 455, 305);
		}
		
		else if(gameState == "Intro") {
			g2.drawImage(tileM.tile[8].image, 0, 0, 768, 576, null);
		}
		
		g2.dispose();
	}

	
	
	private void completeLevel() {
		
		soundManager.stopBackgroundMusic();
		soundManager.playLevelCompletedSound();
		
		enemySystem.reset();
		shoot.reset();
		targetPointTextSystem.reset();
		player.reset();
		targetLineSystem.reset();
		
	}
	
	private void startNextLevel() {
		
		if(!isIncreasedLevel) {
			level++;
			isIncreasedLevel = true;
			if(level == 4) {
				isLevelCompleted = false;
				isIncreasedLevel = true;
				return;
			}
				
		}
		
		isLevelCompleted = false;
		levelCompletedSoundCheck = false;
		gameDifficulties.increaseDifficulty(level);
		soundManager.playBackgroundMusic();
		point = 0;
	}
	
	private void restartGame() {
		
		if(keyH.enterPressed && isRestarted == false) {
			gameDifficulties.setDefault();
			soundManager.playBackgroundMusic();
			point = 0;
			level = 1;
			isGameOver = false;
			isRestarted = true;
			isGameOver = false;
			isStopped = false;
		}
	}
	
	private void gameOver() {
		
		soundManager.stopBackgroundMusic();
		
		if(!isGameOver && isRestarted == false) {
			if(level != 4)
				soundManager.playGameOverSound();
			isGameOver = true;
			enemySystem.reset();
			shoot.reset();
			targetPointTextSystem.reset();
			player.reset();
			targetLineSystem.reset();
		}
		
		restartGame();
	}
	
	private void checkCollision() {
		boolean isCollided = enemyCollisionChecker.checkCollision();
		bulletCollisionChecker.collisionScanner();
		
		if(isCollided) {
			healthSystem.reduceHealth();
			
			if(healthSystem.numberOfHearts == 0)	
				isStopped = true;
			
		}
			//System.out.println("true");
		
		else 
			isStopped = false;
			//System.out.println("false");
	}
}
