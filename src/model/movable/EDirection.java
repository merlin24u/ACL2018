package model.movable;

import java.awt.Point;
import java.util.Vector;

public enum EDirection {
	NORTH(0, new Point(0,-1)),
    SOUTH(1, new Point(0,1)),
    EST(2, new Point(1,0)),
    WEST(3, new Point(-1,0));
	
	private int value;
	private Point direction;
	EDirection(int value, Point direction) {
		this.value = value;
		this.direction = direction;
	}
	public int getValue() {
		return this.value;
	}
	public Point getDirection() {
		return direction;
	}
}
