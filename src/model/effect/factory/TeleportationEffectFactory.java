package model.effect.factory;

import java.awt.Point;

import model.effect.TeleportationEffect;
import model.movable.character.Pacman;
import model.movable.character.Character;

public class TeleportationEffectFactory extends EffectFactory {
	private Point targetPosition;
	public TeleportationEffectFactory(Point targetPosition, int tickDuration) {
		super(tickDuration);
		this.targetPosition = targetPosition;
	}
	
	public Point getTargetPosition() {
		return targetPosition;
	}
	
	@Override
	public boolean applyTo(Character character) {
		if (character instanceof Pacman) {
			character.addEffect(new TeleportationEffect(character, getEffectTexture(), targetPosition, tickDuration));
			return true;
		} else
			return false;
	}

	@Override
	public String getTexture() {
		return "Teleportation";
	}

	@Override
	public void changeTexture() {
		// TODO Auto-generated method stub
	}

	@Override
	public String getEffectTexture() {
		return "teleportationEffect";
	}
}
