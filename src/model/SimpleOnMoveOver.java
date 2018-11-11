package model;

import java.awt.Point;

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
