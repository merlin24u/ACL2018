package model;

public class ExitEffectFactory extends EffectFactory {

	private String texture;

	public ExitEffectFactory(int tickDuration) {
		super(tickDuration);
		texture = "door_closed";
	}

	@Override
	public boolean applyTo(Character character) {
		if (character.isType("Player")) {
			character.addEffect(new ExitEffect(character, tickDuration));
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
}
