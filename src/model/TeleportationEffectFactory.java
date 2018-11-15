package model;

import java.awt.Point;

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
			character.addEffect(new TeleportationEffect(character, targetPosition, tickDuration));
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
}
