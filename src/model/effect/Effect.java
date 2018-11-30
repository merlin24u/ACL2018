package model.effect;

import model.movable.character.Character;

public abstract class Effect {
	protected Character character;
	private int tickDuration;
	private String texture;

	public Effect(Character character, String texture, int tickDuration) {
		super();
		this.character = character;
		this.texture = texture;
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
		} else {
			return false;
		}
	};

	protected abstract void _apply();
	
	public String getTexture() {
		return texture;
	}
}
