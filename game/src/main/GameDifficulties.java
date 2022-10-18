package main;

public class GameDifficulties {
	
	public long enemyCreationTime = 1750;
	public int enemyCreationProbabilityValue = 4;
	public int targetCreationProbabilityValue = 7;
	public int EnemyAndTargetSpeed = 1;
	public int playerSpeed = 3;
	public float bulletSpeedRetarderRate = 1;
	public int targetScore = 100;
	public int lastReducedChecker = 1000;
	
	public void increaseDifficulty(int level) {
		enemyCreationTime -= 700;
		enemyCreationProbabilityValue += 2;
		targetCreationProbabilityValue += 1;
		bulletSpeedRetarderRate -= 0.2;
		targetScore += 50;
		lastReducedChecker = 800;
		
		if(level == 3) {
			//targetCreationProbabilityValue -= 1; 
			enemyCreationProbabilityValue -= 2;
			EnemyAndTargetSpeed += 1;
			enemyCreationTime += 800;
			lastReducedChecker = 300;
		}
	}
	
	public void setDefault() {
		enemyCreationTime = 1750;
		enemyCreationProbabilityValue = 4;
		targetCreationProbabilityValue = 7;
		EnemyAndTargetSpeed = 1;
		playerSpeed = 3;
		bulletSpeedRetarderRate = 1;
		targetScore = 100;
		lastReducedChecker = 1000;
	}
}
