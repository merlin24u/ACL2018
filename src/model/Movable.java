package model;

import java.awt.Point;

public class Movable implements IUpdate, ICoordinate {
	protected GroundCollisionHandler groundCollisionHandler;
	private int movingSpeedXMax;
	private int movingSpeedYMax;
	protected int currentSpeedX;
	protected int currentSpeedY;
	protected int movingTick;
	protected EDirection direction;
	protected Point position;

	public Movable(GroundCollisionHandler groundCollisionHandler,
			int movingSpeedXMax, int movingSpeedYMax, int movingTick, Point position) {
		super();
		this.groundCollisionHandler = groundCollisionHandler;
		this.movingSpeedXMax = movingSpeedXMax;
		this.movingSpeedYMax = movingSpeedYMax;
		this.currentSpeedX = 0;
		this.currentSpeedY = 0;
		this.movingTick = movingTick;
		this.position = position;
		this.direction = EDirection.SOUTH;
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
	
	public boolean canMove() {
		return movingTick == 0 || (Time.getInstance().getTick() % movingTick == 0);
	}
	
	public void updateDirection() {
		if(currentSpeedX > 0) {
			direction = EDirection.EST;
		}else if(currentSpeedX < 0) {
			direction = EDirection.WEST;
		}else if(currentSpeedY > 0) {
			direction = EDirection.SOUTH;
		}else if(currentSpeedY < 0) {
			direction = EDirection.NORTH;
		}
	}
	
	@Override
	public void update() {
		updateDirection();
		if(canMove())
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
