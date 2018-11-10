package model;

public class ExitEffectFactory extends EffectFactory {

	public ExitEffectFactory(int tickDuration) {
		super(tickDuration);
	}

	@Override
	public void applyTo(Character character) {
		if (character instanceof Pacman)
			character.addEffect(new ExitEffect(character, tickDuration));
	}

	@Override
	public String getTexture() {
		return "res/door.png";
	}

}
