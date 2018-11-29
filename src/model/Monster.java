package model;

import java.awt.Point;

public class Monster extends Character implements IDamager {
	private DamageEffectFactory damageEffectFactory;
	private MovableArtificialIntelligence movableArtificialIntelligence;
	private boolean alreadyOn;
	private String texture;
	private boolean isAttacking;

	public Monster(DamageEffectFactory damageEffectFactory, MovableArtificialIntelligence movableArtificialIntelligence,
			int currentHP, int maximumHP, int defensePoints, GroundCollisionHandler groundCollisionHandler,
			int movingSpeedXMax, int movingSpeedYMax, int movingTick, Point position, String texture) {
		super(currentHP, maximumHP, defensePoints, groundCollisionHandler, movingSpeedXMax, movingSpeedYMax, movingTick,
				position);
		this.movableArtificialIntelligence = movableArtificialIntelligence;
		this.damageEffectFactory = damageEffectFactory;
		this.alreadyOn = false;
		this.texture = texture;
		this.isAttacking = false;
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
		updateDirection();
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
		isAttacking = true;
		damageEffectFactory.applyTo(character);
	}

	public void setAlreadyOn(boolean b) {
		alreadyOn = b;
		isAttacking = false;
	}
	
	public boolean isAttacking() {
		return isAttacking;
	}

	@Override
	public String getTexture() {
		return texture;
	}
}
