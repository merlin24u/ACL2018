package model;

import java.awt.Point;

public class Character extends Movable{
	private int currentHP;
	private int maximumHP;
	private int defensePoints;
	

	public Character(int currentHP, int maximumHP, int defensePoints, GroundCollisionHandler groundCollisionHandler, int movingSpeedXMax, int movingSpeedYMax, Point position) {
		super(groundCollisionHandler, movingSpeedXMax, movingSpeedYMax, position);
		this.currentHP = currentHP;
		this.maximumHP = maximumHP;
		this.defensePoints = defensePoints;
	}
	
	public int getCurrentHp() {
		return this.currentHP;
	}
	
	public boolean isAlive() {
		return currentHP>=0;
	}
	public void applyDamages(int damages) {
		this.currentHP -= damages;
	}
}
