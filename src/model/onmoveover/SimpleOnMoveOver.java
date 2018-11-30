package model.onmoveover;

import java.awt.Point;

import model.Map;
import model.movable.character.Character;

public class SimpleOnMoveOver extends OnMoveOver {

	public SimpleOnMoveOver(Map map, Point position, boolean isVisible, boolean isActivated,
			boolean isPersistingAfterActivation) {
		super(map, position, isVisible, isActivated, isPersistingAfterActivation);
	}

	@Override
	protected boolean hasRequirements(Character character) {
		return true;
	}
}
