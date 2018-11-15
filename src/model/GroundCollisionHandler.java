package model;

import java.awt.Point;

import engine.Game;

public class GroundCollisionHandler {
	private Map map;

	public GroundCollisionHandler(Map map) {
		this.map = map;
	}

	public Map getMap() {
		return map;
	}

	public boolean canMoveOn(int x, int y) {
		if (x >= 0 && x < map.getWidth()
				&& y >= 0 && y < map.getHeigh()) {
			if (map.getValue(x, y) != ECollisionType.WALL
					.getValue()) {
				return true;
			}
		}
		return false;
	}

	public void handleMove(Movable movable) {
		int nextPositionX = (int) (movable.getPosition().getX() + movable
				.getCurrentSpeedX());
		int nextPositionY = (int) (movable.getPosition().getY() + movable
				.getCurrentSpeedY());
		if(canMoveOn(nextPositionX, nextPositionY))
			movable.translate(movable.getCurrentSpeedX(),
					movable.getCurrentSpeedY());

	}
}

