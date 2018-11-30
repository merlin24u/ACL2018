package model;

import model.movable.character.Character;
public interface IDamager {
	public int getDamages();
	public void attack(Character character);
}
