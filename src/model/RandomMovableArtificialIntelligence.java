package model;

public class RandomMovableArtificialIntelligence extends
		MovableArtificialIntelligence {
	public void execute(Movable movable) {
		final int STAY = 0;
		final int LEFT = 1;
		final int RIGHT = 2;
		final int UP = 3;
		final int DOWN = 4;

		int randomMove = (int) (Math.random() * 5);

		switch (randomMove) {
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
