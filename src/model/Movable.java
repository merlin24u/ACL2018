package model;

import java.awt.Point;

public class Movable implements IUpdate, ICoordinate{
private GroundCollisionHandler groundCollisionHandler;
private int movingSpeedXMax;
private int movingSpeedYMax;
protected int currentSpeedX;
protected int currentSpeedY;
protected Point position;


public Movable(GroundCollisionHandler groundCollisionHandler, int movingSpeedXMax, int movingSpeedYMax, Point position) {
	super();
	this.groundCollisionHandler = groundCollisionHandler;
	this.movingSpeedXMax = movingSpeedXMax;
	this.movingSpeedYMax = movingSpeedYMax;
	this.position = position;
	this.currentSpeedX = 0;
	this.currentSpeedY = 0;
}
public void moveUp() {
	this.currentSpeedY -= this.movingSpeedYMax;
}
public void moveDown() {
	this.currentSpeedY += this.movingSpeedYMax;
}
public void moveLeft() {
	this.currentSpeedX -= this.movingSpeedXMax;
}
public void moveRight() {
	this.currentSpeedX += this.movingSpeedXMax;
}
public int getCurrentSpeedX() {
	return this.currentSpeedX;
}
public int getCurrentSpeedY() {
	return this.currentSpeedY;
}
@Override
public void update() {
	groundCollisionHandler.moveOnPosition(this);
	this.currentSpeedX = 0;
	this.currentSpeedY = 0;
}
@Override
public Point getPosition() {
	return position;
}
@Override
public void setPosition(int x, int y) {
	this.position.setLocation(x, y);
}
@Override
public void translate(int x, int y) {
	this.position.translate(x, y);
}
}
