package model.effect.factory;

import model.effect.ExitEffect;
import model.movable.character.Character;

public class ExitEffectFactory extends EffectFactory {

	private String texture;

	public ExitEffectFactory(int tickDuration) {
		super(tickDuration);
		texture = "door_closed";
	}

	@Override
	public boolean applyTo(Character character) {
		if (character.isType("Player")) {
			character.addEffect(new ExitEffect(character, getEffectTexture(), tickDuration));
			return true;
		} else
			return false;
	}

	@Override
	public String getTexture() {
		return texture;
	}

	public void changeTexture() {
		texture = "door";

	}

	@Override
	public String getEffectTexture() {
		// TODO Auto-generated method stub
		return null;
	}
}
