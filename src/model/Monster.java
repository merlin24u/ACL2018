package model;

import java.awt.Color;
import java.awt.Point;

public class Monster extends Character implements IDamager {
	private int damages;
	private MovableArtificialIntelligence movableArtificialIntelligence;

	public Monster(MovableArtificialIntelligence movableArtificialIntelligence,
			int currentHP, int maximumHP, int defensePoints,
			GroundCollisionHandler groundCollisionHandler, int movingSpeedXMax,
			int movingSpeedYMax, Point position) {
		super(currentHP, maximumHP, defensePoints, groundCollisionHandler,
				movingSpeedXMax, movingSpeedYMax, position, Color.red);
		this.movableArtificialIntelligence = movableArtificialIntelligence;
		this.damages = 1;
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
		if (character instanceof Pacman) {
			attack(character);
		}
	}

	@Override
	public int getDamages() {
		return damages;
	}

	public void attack(Character character) {
		character.applyDamages(getDamages());
	}

	@Override
	public String getTexture() {
		return "monster";
	}
}
