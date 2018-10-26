package model;

public abstract class Effect {
	protected Character character;
	private int tickDuration;

	public Effect(Character character, int tickDuration) {
		super();
		this.character = character;
		this.tickDuration = tickDuration;
	}

	public final void apply() {
		if (tickDuration-- > 0) {
			_apply();
		}
	};

	protected abstract void _apply();
}
