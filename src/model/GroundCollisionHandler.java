package model;

import java.awt.Point;

import engine.Game;

public class GroundCollisionHandler {
	private Map map;
	public GroundCollisionHandler(Map map) {
		this.map = map;
	}
	public void handleMove(Movable movable) {
		int nextPositionX = (int) (movable.getPosition().getX()+movable.getCurrentSpeedX());
		int nextPositionY = (int) (movable.getPosition().getY()+movable.getCurrentSpeedY());
		if (nextPositionX >=0 && nextPositionX < map.getWidth() && nextPositionY >=0 && nextPositionY < map.getHeigh()) {
			if (map.getValue(nextPositionX, nextPositionY) != ECollisionType.WALL.getValue()) {
				movable.translate(movable.getCurrentSpeedX(), movable.getCurrentSpeedY());
			}
		}
	}
}
