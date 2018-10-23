package model;

import java.awt.Point;

import engine.Game;

public class GroundCollisionHandler {
	private PacmanGame game;
	public GroundCollisionHandler(PacmanGame game) {
		this.game = game;
	}
	public void handleMove(Movable movable) {
		int nextPositionX = (int) (movable.getPosition().getX()+movable.getCurrentSpeedX());
		int nextPositionY = (int) (movable.getPosition().getY()+movable.getCurrentSpeedY());
		if (nextPositionX >=0 && nextPositionX < game.getMaxW() && nextPositionY >=0 && nextPositionY < game.getMaxH()) {
			if (!game.isWall(nextPositionX, nextPositionY)) {
				movable.translate(movable.getCurrentSpeedX(), movable.getCurrentSpeedY());
			}
		}
		
	}
}
