package model;

import java.awt.Point;

public interface ICoordinate {
	public Point getPosition();

	public void setPosition(int x, int y);

	public void translate(int distanceX, int distanceY);
}
