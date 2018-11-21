package model;

public class DamageEffectFactory extends EffectFactory {
	private int damages;

	public DamageEffectFactory(int damages, int tickDuration) {
		super(tickDuration);
		this.damages = damages;
	}

	@Override
	public boolean applyTo(Character character) {
		if (character instanceof Pacman) {
			character.addEffect(new DamageEffect(character, damages, tickDuration));
			return true;
		} else
			return false;
	}

	public int getDamages() {
		return damages;
	}

	@Override
	public String getTexture() {
		return "damage";
	}

	@Override
	public void changeTexture() {
		// TODO Auto-generated method stub
	}
}
