package model;

import java.awt.Color;
import java.awt.Point;

public class Monster extends Character implements IDamager {
	private int damages;
	private MovableArtificialIntelligence movableArtificialIntelligence;

	public Monster(MovableArtificialIntelligence movableArtificialIntelligence,
			int currentHP, int maximumHP, int defensePoints,
			GroundCollisionHandler groundCollisionHandler, int movingSpeedXMax,
			int movingSpeedYMax, int movingTick, Point position) {
		super(currentHP, maximumHP, defensePoints, groundCollisionHandler,
				movingSpeedXMax, movingSpeedYMax, movingTick, position, Color.red);
		this.movableArtificialIntelligence = movableArtificialIntelligence;
		this.damages = 1;
	}
	
	public boolean isType(String type) {
		if(type.equals("Monster"))
			return true;
		else
			return super.isType(type);
	}

	@Override
	public void update() {
		applyEffects();
		if(movingTick == 0 || Time.getInstance().getTick() %movingTick ==0) {
			movableArtificialIntelligence.execute(this);
		}
		groundCollisionHandler.handleMove(this);
		resetCurrentSpeed();
	}

	@Override
	public void onCollision(Character character) {
		if(character.isType("Player")) {
			attack(character);
		}
	}

	@Override
	public int getDamages() {
		return damages;
	}

}
