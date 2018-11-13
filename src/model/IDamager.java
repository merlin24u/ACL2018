package model;

public interface IDamager {
	public int getDamages();
	public default void attack(Character character) {
		character.applyDamages(getDamages());
	}
}
