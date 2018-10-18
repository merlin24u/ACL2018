package model;

import java.awt.Point;

public interface Coordinate {
Point position = new Point();

public default void translate(int distanceX, int distanceY) {
	position.translate(distanceX, distanceY);
}
public default void setPosition(int x, int y) {
	position.setLocation(x, y);
}
public default Point getPosition() {
	return position;
}
}
