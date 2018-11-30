package model.movable.collision;

import model.Map;
import model.movable.Movable;

public class GroundCollisionHandler {
	private Map map;
	private ECollisionType[] collisions;

	public GroundCollisionHandler(Map map, ECollisionType[] collisions) {
		this.map = map;
		this.collisions = collisions;
	}

	public Map getMap() {
		return map;
	}
	
	public ECollisionType[] getCollisions() {
		return collisions;
	}

	public boolean canMoveOn(int x, int y) {
		if (x >= 0 && x < map.getWidth() && y >= 0 && y < map.getHeigh()) {
			for(ECollisionType ect: collisions) {
				int collision = map.getValue(x, y);
				if(collision == ect.getValue()) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	public void handleMove(Movable movable) {
		int nextPositionX = (int) (movable.getPosition().getX() + movable.getCurrentSpeedX());
		int nextPositionY = (int) (movable.getPosition().getY() + movable.getCurrentSpeedY());
		if (canMoveOn(nextPositionX, nextPositionY))
			movable.translate(movable.getCurrentSpeedX(), movable.getCurrentSpeedY());

	}

	public void changeMap(Map m) {
		map = m;
	}
}
