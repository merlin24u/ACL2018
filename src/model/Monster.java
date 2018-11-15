package model;

import java.awt.Color;
import java.awt.Point;

public class Monster extends Character implements IDamager {
	private DamageEffectFactory damageEffectFactory;
	private MovableArtificialIntelligence movableArtificialIntelligence;

	public Monster(DamageEffectFactory damageEffectFactory, MovableArtificialIntelligence movableArtificialIntelligence,
			int currentHP, int maximumHP, int defensePoints,
			GroundCollisionHandler groundCollisionHandler, int movingSpeedXMax,
			int movingSpeedYMax, Point position) {
		super(currentHP, maximumHP, defensePoints, groundCollisionHandler,
				movingSpeedXMax, movingSpeedYMax, position, Color.red);
		this.movableArtificialIntelligence = movableArtificialIntelligence;
		this.damageEffectFactory = damageEffectFactory;
	}

	@Override
	public void update() {
		applyEffects();
		movableArtificialIntelligence.execute(this);
		groundCollisionHandler.handleMove(this);
		resetCurrentSpeed();
	}

	@Override
	public void onCollision(Character character) {
		if(character instanceof Pacman) {
			attack(character);
		}
	}

	@Override
	public int getDamages() {
		return damageEffectFactory.getDamages();
	}
	
	@Override
	public void attack(Character character) {
		damageEffectFactory.applyTo(character);
	}
}
