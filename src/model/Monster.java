package model;

import java.awt.Point;

public class Monster extends Character{
	private MovableArtificialIntelligence movableArtificialIntelligence;
	
	public Monster(MovableArtificialIntelligence movableArtificialIntelligence, int currentHP, int maximumHP, int defensePoints, GroundCollisionHandler groundCollisionHandler,int movingSpeedXMax, int movingSpeedYMax, Point position) {
		super(currentHP, maximumHP, defensePoints, groundCollisionHandler, movingSpeedXMax, movingSpeedYMax, position);
		this.movableArtificialIntelligence = movableArtificialIntelligence;
	}
	
	@Override
	public void update() {
		movableArtificialIntelligence.execute(this);
		groundCollisionHandler.handleMove(this);
	}

}
