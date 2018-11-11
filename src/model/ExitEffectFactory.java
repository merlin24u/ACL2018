package model;

public class ExitEffectFactory extends EffectFactory {

	public ExitEffectFactory(int tickDuration) {
		super(tickDuration);
	}

	@Override
	public boolean applyTo(Character character) {
		if (character instanceof Pacman) {
			character.addEffect(new ExitEffect(character, tickDuration));
			return true;
		} else
			return false;
	}

	@Override
	public String getTexture() {
		return "door";
	}

}
