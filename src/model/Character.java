package model;

import java.awt.Point;
import java.util.ArrayList;

public abstract class Character extends Movable implements IDestructible {
	private int currentHP;
	private int maximumHP;
	private int defensePoints;
	private ArrayList<Effect> effects;

	// a detruire
	private boolean toDestroy;

	public Character(int currentHP, int maximumHP, int defensePoints, GroundCollisionHandler groundCollisionHandler,
			int movingSpeedXMax, int movingSpeedYMax, int movingTick, Point position) {
		super(groundCollisionHandler, movingSpeedXMax, movingSpeedYMax, movingTick, position);
		this.currentHP = currentHP;
		this.maximumHP = maximumHP;
		this.defensePoints = defensePoints;
		this.effects = new ArrayList<Effect>();
	}

	public int getCurrentHp() {
		return this.currentHP;
	}

	public boolean isAlive() {
		return currentHP >= 0;
	}

	public void applyDamages(int damages) {
		this.currentHP -= damages;
		if (currentHP <= 0) {
			this.toDestroy = true;
		}
	}

	public void addEffect(Effect effect) {
		this.effects.add(effect);
	}

	@Override
	public void update() {
		applyEffects();
		super.update();
	}

	protected void applyEffects() {
		for (Effect e : effects) {
			e.apply();
		}
	}

	public int getEffectsSize() {
		return effects.size();
	}

	public Effect getEffect(int index) {
		return effects.get(index);
	}

	public boolean isType(String type) {
		return type.equals("Character");
	}

	@Override
	public boolean isToDestroy() {
		return toDestroy;
	}

	public abstract void onCollision(Character character);

	public abstract String getTexture();
}
