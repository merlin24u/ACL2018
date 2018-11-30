package model.effect.factory;

import model.movable.character.Character;

public abstract class EffectFactory {
	protected int tickDuration;
	protected String Texture;
	protected String EffectTexture;

	public EffectFactory(int tickDuration) {
		super();
		this.tickDuration = tickDuration;
	}

	public int getTickDuration() {
		return this.tickDuration;
	}

	public abstract boolean applyTo(Character character);

	public abstract String getTexture();
	
	public abstract String getEffectTexture();

	public abstract void changeTexture();
}
