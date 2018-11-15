package model;

import java.awt.Point;

public class Movable implements IUpdate, ICoordinate {
	protected GroundCollisionHandler groundCollisionHandler;
	private int movingSpeedXMax;
	private int movingSpeedYMax;
	protected int currentSpeedX;
	protected int currentSpeedY;
	protected Point position;

	public Movable(GroundCollisionHandler groundCollisionHandler,
			int movingSpeedXMax, int movingSpeedYMax, Point position) {
		super();
		this.groundCollisionHandler = groundCollisionHandler;
		this.movingSpeedXMax = movingSpeedXMax;
		this.movingSpeedYMax = movingSpeedYMax;
		this.position = position;
		this.currentSpeedX = 0;
		this.currentSpeedY = 0;
	}
	
	public void move(int x, int y) {
		int movingX = x - (int)position.getX();
		if(movingX > 0) {
			this.moveRight();
		}else if(movingX < 0) {
			this.moveLeft();
		}
		int movingY = y - (int)position.getY();
		if(movingY > 0) {
			this.moveDown();
		}else if(movingY < 0) {
			this.moveUp();
		}
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

	public void resetCurrentSpeed() {
		this.currentSpeedX = 0;
		this.currentSpeedY = 0;
	}

	@Override
	public void update() {
		groundCollisionHandler.handleMove(this);
		resetCurrentSpeed();
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
