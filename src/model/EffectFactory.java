package model;

public abstract class EffectFactory {
	protected int tickDuration;

	public EffectFactory(int tickDuration) {
		super();
		this.tickDuration = tickDuration;
	}
	
	public int getTickDuration() {
		return this.tickDuration;
	}
	
	public abstract void applyTo(Character character);
}
