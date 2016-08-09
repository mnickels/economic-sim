package model.map;

import java.util.Random;

public class SimplexNoise {

	Random randomNumGenerator;
	int length;
	int width;
	double randNum;
	double board[][] = new double[length][width];
	
	public SimplexNoise() {
		randomNumGenerator = new Random();
		for(int i = 0; i < length * width; i++){
			randNum = randomNumGenerator.nextDouble();
		}
		SimplexNoiseArray(length, width, randNum);
	}
	
	public void SimplexNoiseArray(int length, int width, double randNum){
		for(int i = 0; i < length; i++){
			for(int j = 0; j < width; j++){
				board[i][j] = randNum;
			}
		}
	}

	public double getValue(int x, int y){
		double value = board[x][y];
		return value;
		
	}
}
