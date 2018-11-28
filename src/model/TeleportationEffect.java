package model;

import java.awt.Point;

public class TeleportationEffect extends Effect {
	private Point targetPosition;

	public TeleportationEffect(Character character, String texture, Point targetPosition, int tickDuration) {
		super(character, texture, tickDuration);
		this.targetPosition = targetPosition;
	}
	
	public Point getTargetPosition() {
		return targetPosition;
	}
	
	@Override
	public void _apply() {
		((Pacman) character).setPosition(targetPosition.x, targetPosition.y);
	}

}
