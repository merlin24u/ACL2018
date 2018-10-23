package model;

public class RandomMovableArtificialIntelligence {
	public void execute(Movable movable) {
		final int  STAY = 0;
		final int LEFT = 1;
		final int RIGHT = 2;
		final int UP = 1;
		final int DOWN = 2;
		
		int randomX = (int)(Math.random()*3);
		int randomY = (int)(Math.random()*3);
		
		switch(randomX) {
		case LEFT:
			movable.moveLeft();
			break;
		case RIGHT:
			movable.moveRight();
			break;
		case STAY:
			break;
		}
		
		switch(randomY) {
		case UP:
			movable.moveUp();
			break;
		case DOWN:
			movable.moveDown();
			break;
		case STAY:
			break;
		}
	}
}
