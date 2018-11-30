package model.movable.collision;

import java.awt.Point;

public class CollisionUtility {
	public static int getManhattanDistance(Point a, Point b) {
		return Math.abs(a.x-b.x) + Math.abs(a.y-a.y);
	}
}
