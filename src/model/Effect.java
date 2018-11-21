package model;

public abstract class Effect {
	protected Character character;
	private int tickDuration;

	public Effect(Character character, int tickDuration) {
		super();
		this.character = character;
		this.tickDuration = tickDuration;
	}

	public Character getCharacter() {
		return character;
	}

	public int getTickDuration() {
		return tickDuration;
	}

	public final boolean apply() {
		if (tickDuration-- > 0) {
			_apply();
			return true;
		} else
			return false;
	};

	protected abstract void _apply();
}
