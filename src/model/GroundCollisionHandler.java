package model;

import java.awt.Point;

import engine.Game;

public class GroundCollisionHandler {
	private PacmanGame game;
	public GroundCollisionHandler(PacmanGame game) {
		this.game = game;
	}
	public void moveOnPosition(Movable movable) {
		int nextPositionX = (int) (movable.getPosition().getX()+movable.getCurrentSpeedX());
		int nextPositionY = (int) (movable.getPosition().getY()+movable.getCurrentSpeedY());
		if (!game.isWall(nextPositionX, nextPositionY)) {
			movable.translate(movable.getCurrentSpeedX(), movable.getCurrentSpeedY());
		}
		
	}
}
