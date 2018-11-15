package model;

import java.awt.Point;

public class TeleportationEffect extends Effect {
	private Point targetPosition;

	public TeleportationEffect(Character character, Point targetPosition, int tickDuration) {
		super(character, tickDuration);
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
