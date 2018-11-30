package model.movable.collision;

public enum ECollisionType {
	NONE(0), WALL(1);

	private final int value;

	private ECollisionType(final int newValue) {
		value = newValue;
	}

	public int getValue() {
		return value;
	}
}
