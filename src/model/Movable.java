package model;

public class Movable implements Coordinate{
private int movingSpeedXMax;
private int movingSpeedYMax;
protected int currentSpeedX;
protected int currentSpeedY;
private GroundCollisionHandler groundCollisionHandler;

public Movable(GroundCollisionHandler groundCollisionHandler) {
	this(groundCollisionHandler, 0, 0, 1,1);
}
public Movable(GroundCollisionHandler groundCollisionHandler, int x, int y, int movingSpeedXMax, int movingSpeedYMax) {
	this.groundCollisionHandler = groundCollisionHandler;
	this.movingSpeedXMax = movingSpeedXMax;
	this.movingSpeedYMax = movingSpeedYMax;
	this.currentSpeedX = 0;
	this.currentSpeedY = 0;
	this.setPosition(x, y);
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
public void update() {
	groundCollisionHandler.moveOnPosition(this);
	this.currentSpeedX = 0;
	this.currentSpeedY = 0;
}
}
