package model;

public abstract class EffectFactory {
	protected int tickDuration;
	protected String Texture;

	public EffectFactory(int tickDuration) {
		super();
		this.tickDuration = tickDuration;
	}

	public int getTickDuration() {
		return this.tickDuration;
	}

	public abstract boolean applyTo(Character character);

	public abstract String getTexture();
}
