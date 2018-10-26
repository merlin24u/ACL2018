package model;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class Character extends Movable {
	private int currentHP;
	private int maximumHP;
	private int defensePoints;
	private ArrayList<Effect> effects;
	private Color color;

	public Character(int currentHP, int maximumHP, int defensePoints,
			GroundCollisionHandler groundCollisionHandler, int movingSpeedXMax,
			int movingSpeedYMax, Point position, Color c) {
		super(groundCollisionHandler, movingSpeedXMax, movingSpeedYMax,
				position);
		this.currentHP = currentHP;
		this.maximumHP = maximumHP;
		this.defensePoints = defensePoints;
		this.color = c;
		this.effects = new ArrayList<Effect>();
	}

	public int getCurrentHp() {
		return this.currentHP;
	}

	public Color getColor() {
		return color;
	}

	public boolean isAlive() {
		return currentHP >= 0;
	}

	public void applyDamages(int damages) {
		this.currentHP -= damages;
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
}
