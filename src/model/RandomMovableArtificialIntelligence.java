package model;

import java.util.Random;

public class RandomMovableArtificialIntelligence extends MovableArtificialIntelligence {
	private Random random;

	public RandomMovableArtificialIntelligence(Map map) {
		super(map);
		this.random = new Random();
	}

	public RandomMovableArtificialIntelligence(Map map, int randomSeed) {
		super(map);
		this.random = new Random(randomSeed);
	}

	public void execute(Movable movable) {
		final int STAY = 0;
		final int LEFT = 1;
		final int RIGHT = 2;
		final int UP = 3;
		final int DOWN = 4;

		switch (random.nextInt(5)) {
		case UP:
			movable.moveUp();
			break;
		case DOWN:
			movable.moveDown();
			break;
		case LEFT:
			movable.moveLeft();
			break;
		case RIGHT:
			movable.moveRight();
			break;
		case STAY:
			break;
		}

	}
}
