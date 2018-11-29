package model;

import java.awt.Point;

public class Monster extends Character implements IDamager {
	private DamageEffectFactory damageEffectFactory;
	private MovableArtificialIntelligence movableArtificialIntelligence;
	private boolean alreadyOn;

	public Monster(DamageEffectFactory damageEffectFactory, MovableArtificialIntelligence movableArtificialIntelligence,
			int currentHP, int maximumHP, int defensePoints, GroundCollisionHandler groundCollisionHandler,
			int movingSpeedXMax, int movingSpeedYMax, int movingTick, Point position) {
		super(currentHP, maximumHP, defensePoints, groundCollisionHandler, movingSpeedXMax, movingSpeedYMax, movingTick,
				position);
		this.movableArtificialIntelligence = movableArtificialIntelligence;
		this.damageEffectFactory = damageEffectFactory;
		alreadyOn = false;
	}

	public boolean isType(String type) {
		if (type.equals("Monster"))
			return true;
		else
			return super.isType(type);
	}

	@Override
	public void update() {
		applyEffects();
		if (movingTick == 0 || Time.getInstance().getTick() % movingTick == 0) {
			movableArtificialIntelligence.execute(this);
		}
		groundCollisionHandler.handleMove(this);
		resetCurrentSpeed();
	}

	@Override
	public void onCollision(Character character) {
		if (!alreadyOn) {
			attack(character);
			alreadyOn = true;
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

	public void setAlreadyOn(boolean b) {
		alreadyOn = b;
	}

	@Override
	public String getTexture() {
		return "monster";
	}
}
